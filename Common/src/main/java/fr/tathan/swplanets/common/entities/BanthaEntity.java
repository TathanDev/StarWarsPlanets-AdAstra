//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fr.tathan.swplanets.common.entities;

import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Dynamic;
import fr.tathan.swplanets.common.entities.ai.BanthaAi;
import fr.tathan.swplanets.common.registry.EntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.Saddleable;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class BanthaEntity extends AbstractHorse implements PlayerRideableJumping, Saddleable {
    public static final Ingredient TEMPTATION_ITEM;
    public static final float BABY_SCALE = 0.45F;
    public static final int DASH_COOLDOWN_TICKS = 55;
    public static final int MAX_HEAD_Y_ROT = 30;
    private static final float RUNNING_SPEED_BONUS = 0.1F;
    private static final float DASH_VERTICAL_MOMENTUM = 1.4285F;
    private static final float DASH_HORIZONTAL_MOMENTUM = 22.2222F;
    private static final int DASH_MINIMUM_DURATION_TICKS = 5;
    private static final int SITDOWN_DURATION_TICKS = 40;
    private static final int STANDUP_DURATION_TICKS = 52;
    private static final int IDLE_MINIMAL_DURATION_TICKS = 80;
    private static final float SITTING_HEIGHT_DIFFERENCE = 1.43F;
    public static final EntityDataAccessor<Boolean> DASH;
    public static final EntityDataAccessor<Long> LAST_POSE_CHANGE_TICK;
    public final AnimationState sitAnimationState = new AnimationState();
    public final AnimationState sitPoseAnimationState = new AnimationState();
    public final AnimationState sitUpAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState dashAnimationState = new AnimationState();
    private static final EntityDimensions SITTING_DIMENSIONS;
    private int dashCooldown = 0;
    private int idleAnimationTimeout = 0;

    public BanthaEntity(EntityType<? extends BanthaEntity> entityType, Level level) {
        super(entityType, level);
        this.setMaxUpStep(1.5F);
        this.moveControl = new BanthaMoveControl();
        this.lookControl = new BanthaLookControl();
        GroundPathNavigation groundPathNavigation = (GroundPathNavigation)this.getNavigation();
        groundPathNavigation.setCanFloat(true);
        groundPathNavigation.setCanWalkOverFences(true);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putLong("LastPoseTick", (Long)this.entityData.get(LAST_POSE_CHANGE_TICK));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        long l = compound.getLong("LastPoseTick");
        if (l < 0L) {
            this.setPose(Pose.SITTING);
        }

        this.resetLastPoseChangeTick(l);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
        BanthaAi.initMemories(this, level.getRandom());
        this.resetLastPoseChangeTickToFullStand(level.getLevel().getGameTime());
        return super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag);
    }

    @Override
    protected Brain.Provider<BanthaEntity> brainProvider() {
        return BanthaAi.brainProvider();
    }

    @Override
    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return BanthaAi.makeBrain(this.brainProvider().makeBrain(dynamic));
    }

    public static AttributeSupplier.Builder addAttributes() {
        return createBaseHorseAttributes().add(Attributes.MAX_HEALTH, 32.0).add(Attributes.MOVEMENT_SPEED, 0.01).add(Attributes.JUMP_STRENGTH, 0.41999998688697815);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DASH, false);
        this.entityData.define(LAST_POSE_CHANGE_TICK, 0L);
    }

    @Override
    protected void registerGoals() {
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return pose == Pose.SITTING ? SITTING_DIMENSIONS.scale(this.getScale()) : super.getDimensions(pose);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return dimensions.height - 0.1F * this.getScale();
    }

    @Override
    protected void customServerAiStep() {
        this.level().getProfiler().push("banthaBrain");
        Brain<BanthaEntity> brain = (Brain<BanthaEntity>) this.getBrain();
        brain.tick((ServerLevel)this.level(), this);
        this.level().getProfiler().pop();
        this.level().getProfiler().push("banthaActivityUpdate");
        BanthaAi.updateActivity(this);
        this.level().getProfiler().pop();
        super.customServerAiStep();
    }


    @Override
    public void tick() {
        super.tick();
        if (this.isDashing() && this.dashCooldown < 50 && (this.onGround() || this.getBlockStateOn().liquid() || this.isPassenger())) {
            this.setDashing(false);
        }

        if (this.dashCooldown > 0) {
            --this.dashCooldown;
            if (this.dashCooldown == 0) {
                this.level().playSound((Player)null, this.blockPosition(), SoundEvents.CAMEL_DASH_READY, SoundSource.NEUTRAL, 1.0F, 1.0F);
            }
        }

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }

        if (this.refuseToMove()) {
            this.clampHeadRotationToBody(this, 30.0F);
        }

        if (this.isBanthaSitting() && this.isInWater()) {
            this.standUpInstantly();
        }

    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if (this.isBanthaVisuallySitting()) {
            this.sitUpAnimationState.stop();
            this.dashAnimationState.stop();
            if (this.isVisuallySittingDown()) {
                this.sitAnimationState.startIfStopped(this.tickCount);
                this.sitPoseAnimationState.stop();
            } else {
                this.sitAnimationState.stop();
                this.sitPoseAnimationState.startIfStopped(this.tickCount);
            }
        } else {
            this.sitAnimationState.stop();
            this.sitPoseAnimationState.stop();
            this.dashAnimationState.animateWhen(this.isDashing(), this.tickCount);
            this.sitUpAnimationState.animateWhen(this.isInPoseTransition() && this.getPoseTime() >= 0L, this.tickCount);
        }

    }

    @Override
    protected void updateWalkAnimation(float partialTick) {
        float f;
        if (this.getPose() == Pose.STANDING && !this.dashAnimationState.isStarted()) {
            f = Math.min(partialTick * 6.0F, 1.0F);
        } else {
            f = 0.0F;
        }

        this.walkAnimation.update(f, 0.2F);
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.refuseToMove() && this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.0, 1.0, 0.0));
            travelVector = travelVector.multiply(0.0, 1.0, 0.0);
        }

        super.travel(travelVector);
    }

    @Override
    protected void tickRidden(Player player, Vec3 travelVector) {
        super.tickRidden(player, travelVector);
        if (player.zza > 0.0F && this.isBanthaSitting() && !this.isInPoseTransition()) {
            this.standUp();
        }

    }

    public boolean refuseToMove() {
        return this.isBanthaSitting() || this.isInPoseTransition();
    }

    @Override
    protected float getRiddenSpeed(Player player) {
        float f = player.isSprinting() && this.getJumpCooldown() == 0 ? 0.1F : 0.0F;
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED) + f;
    }

    @Override
    protected Vec2 getRiddenRotation(LivingEntity entity) {
        return this.refuseToMove() ? new Vec2(this.getXRot(), this.getYRot()) : super.getRiddenRotation(entity);
    }

    @Override
    protected Vec3 getRiddenInput(Player player, Vec3 travelVector) {
        return this.refuseToMove() ? Vec3.ZERO : super.getRiddenInput(player, travelVector);
    }

    @Override
    public boolean canJump() {
        return !this.refuseToMove() && super.canJump();
    }

    @Override
    public void onPlayerJump(int jumpPower) {
        if (this.isSaddled() && this.dashCooldown <= 0 && this.onGround()) {
            super.onPlayerJump(jumpPower);
        }
    }

    @Override
    public boolean canSprint() {
        return true;
    }

    @Override
    protected void executeRidersJump(float playerJumpPendingScale, Vec3 travelVector) {
        double d = this.getAttributeValue(Attributes.JUMP_STRENGTH) * (double)this.getBlockJumpFactor() + (double)this.getJumpBoostPower();
        this.addDeltaMovement(this.getLookAngle().multiply(1.0, 0.0, 1.0).normalize().scale((double)(22.2222F * playerJumpPendingScale) * this.getAttributeValue(Attributes.MOVEMENT_SPEED) * (double)this.getBlockSpeedFactor()).add(0.0, (double)(1.4285F * playerJumpPendingScale) * d, 0.0));
        this.dashCooldown = 55;
        this.setDashing(true);
        this.hasImpulse = true;
    }

    public boolean isDashing() {
        return (Boolean)this.entityData.get(DASH);
    }

    public void setDashing(boolean dashing) {
        this.entityData.set(DASH, dashing);
    }

    @Override
    public void handleStartJump(int jumpPower) {
        this.playSound(SoundEvents.CAMEL_DASH, 1.0F, this.getVoicePitch());
        this.setDashing(true);
    }

    @Override
    public void handleStopJump() {
    }

    @Override
    public int getJumpCooldown() {
        return this.dashCooldown;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.CAMEL_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.CAMEL_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.CAMEL_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.CAMEL_STEP, 1.0F, 1.0F);

    }

    @Override
    protected void positionRider(Entity passenger, MoveFunction callback) {
        passenger.position().add(0, 1.6, 0);
        super.positionRider(passenger, callback);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return TEMPTATION_ITEM.test(stack);
    }

    @Override
    protected void setOffspringAttributes(AgeableMob parent, AbstractHorse child) {
        super.setOffspringAttributes(parent, child);
        this.setOffspringAttribute(parent, child, Attributes.MOVEMENT_SPEED, (double)0.0, (double)0.5);
    }

    private void setOffspringAttribute(AgeableMob otherParent, AbstractHorse child, Attribute attribute, double min, double max) {
        double d = createOffspringAttribute(this.getAttributeBaseValue(attribute), otherParent.getAttributeBaseValue(attribute), min, max, this.random);
        child.getAttribute(attribute).setBaseValue(d);
    }



    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (player.isSecondaryUseActive() && !this.isBaby()) {
            this.openCustomInventoryScreen(player);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            InteractionResult interactionResult = itemStack.interactLivingEntity(player, this, hand);
            if (interactionResult.consumesAction()) {
                return interactionResult;
            } else if (this.isFood(itemStack)) {
                return this.fedFood(player, itemStack);
            } else {
                if (this.getPassengers().size() < 2 && !this.isBaby()) {
                    this.doPlayerRide(player);
                }

                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
        }
    }

    @Override
    protected void onLeashDistance(float distance) {
        if (distance > 6.0F && this.isBanthaSitting() && !this.isInPoseTransition() && this.canBanthaChangePose()) {
            this.standUp();
        }

    }

    public boolean canBanthaChangePose() {
        //TODO need better check
        return true;
    }

    @Override
    protected boolean handleEating(Player player, ItemStack stack) {
        if (!this.isFood(stack)) {
            return false;
        } else {
            boolean bl = this.getHealth() < this.getMaxHealth();
            if (bl) {
                this.heal(2.0F);
            }

            boolean bl2 = this.isTamed() && this.getAge() == 0 && this.canFallInLove();
            if (bl2) {
                this.setInLove(player);
            }

            boolean bl3 = this.isBaby();
            if (bl3) {
                this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0), this.getRandomY() + 0.5, this.getRandomZ(1.0), 0.0, 0.0, 0.0);
                if (!this.level().isClientSide) {
                    this.ageUp(10);
                }
            }

            if (!bl && !bl2 && !bl3) {
                return false;
            } else {
                if (!this.isSilent()) {
                    SoundEvent soundEvent = this.getEatingSound();
                    if (soundEvent != null) {
                        this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), soundEvent, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
                    }
                }

                this.gameEvent(GameEvent.EAT);
                return true;
            }
        }
    }


    @Override
    protected boolean canPerformRearing() {
        return false;
    }


    @Nullable
    public BanthaEntity getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return EntityRegistry.BANTHA.get().create(level);
    }

    @Nullable
    protected SoundEvent getEatingSound() {
        return SoundEvents.CAMEL_EAT;
    }

    @Override
    protected void actuallyHurt(DamageSource damageSource, float damageAmount) {
        this.standUpInstantly();
        super.actuallyHurt(damageSource, damageAmount);
    }

//    @Override
//    protected Vector3f getPassengerAttachmentPoint(Entity entity, EntityDimensions dimensions, float scale) {
//        int i = Math.max(this.getPassengers().indexOf(entity), 0);
//        boolean bl = i == 0;
//        float f = 0.5F;
//        float g = (float)(this.isRemoved() ? 0.009999999776482582 : this.getBodyAnchorAnimationYOffset(bl, 0.0F, dimensions, scale));
//        if (this.getPassengers().size() > 1) {
//            if (!bl) {
//                f = -0.7F;
//            }
//
//            if (entity instanceof Animal) {
//                f += 0.2F;
//            }
//        }
//
//        return new Vector3f(0.0F, g, f * scale);
//    }

    @Override
    public float getScale() {
        return this.isBaby() ? 0.45F : 1.0F;
    }

    private double getBodyAnchorAnimationYOffset(boolean firstPassenger, float partialTick, EntityDimensions dimensions, float scale) {
        double d = (double)(dimensions.height - 0.375F * scale);
        float f = scale * 1.43F;
        float g = f - scale * 0.2F;
        float h = f - g;
        boolean bl = this.isInPoseTransition();
        boolean bl2 = this.isBanthaSitting();
        if (bl) {
            int i = bl2 ? 40 : 52;
            int j;
            float k;
            if (bl2) {
                j = 28;
                k = firstPassenger ? 0.5F : 0.1F;
            } else {
                j = firstPassenger ? 24 : 32;
                k = firstPassenger ? 0.6F : 0.35F;
            }

            float l = Mth.clamp((float)this.getPoseTime() + partialTick, 0.0F, (float)i);
            boolean bl3 = l < (float)j;
            float m = bl3 ? l / (float)j : (l - (float)j) / (float)(i - j);
            float n = f - k * g;
            d += bl2 ? (double)Mth.lerp(m, bl3 ? f : n, bl3 ? n : h) : (double)Mth.lerp(m, bl3 ? h - f : h - n, bl3 ? h - n : 0.0F);
        }

        if (bl2 && !bl) {
            d += (double)h;
        }

        return d;
    }

    @Override
    public Vec3 getLeashOffset(float partialTick) {
        EntityDimensions entityDimensions = this.getDimensions(this.getPose());
        float f = this.getScale();
        return new Vec3(0.0, this.getBodyAnchorAnimationYOffset(true, partialTick, entityDimensions, f) - (double)(0.2F * f), (double)(entityDimensions.width * 0.56F));
    }

    private void clampHeadRotationToBody(Entity entity, float maxYRot) {
        float f = entity.getYHeadRot();
        float g = Mth.wrapDegrees(this.yBodyRot - f);
        float h = Mth.clamp(Mth.wrapDegrees(this.yBodyRot - f), -maxYRot, maxYRot);
        float i = f + g - h;
        entity.setYHeadRot(i);
    }

    @Override
    public int getMaxHeadYRot() {
        return 30;
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return this.getPassengers().size() <= 2;
    }

    @Override
    protected void sendDebugPackets() {
        super.sendDebugPackets();
        DebugPackets.sendEntityBrain(this);
    }

    public boolean isBanthaSitting() {
        return (Long)this.entityData.get(LAST_POSE_CHANGE_TICK) < 0L;
    }

    public boolean isBanthaVisuallySitting() {
        return this.getPoseTime() < 0L != this.isBanthaSitting();
    }

    public boolean isInPoseTransition() {
        long l = this.getPoseTime();
        return l < (long)(this.isBanthaSitting() ? 40 : 52);
    }

    private boolean isVisuallySittingDown() {
        return this.isBanthaSitting() && this.getPoseTime() < 40L && this.getPoseTime() >= 0L;
    }

    public void sitDown() {
        if (!this.isBanthaSitting()) {
            this.playSound(SoundEvents.CAMEL_SIT, 1.0F, this.getVoicePitch());
            this.setPose(Pose.SITTING);
            this.resetLastPoseChangeTick(-this.level().getGameTime());
        }
    }

    public void standUp() {
        if (this.isBanthaSitting()) {
            this.playSound(SoundEvents.CAMEL_STAND, 1.0F, this.getVoicePitch());
            this.setPose(Pose.STANDING);
            this.resetLastPoseChangeTick(this.level().getGameTime());
        }
    }

    public void standUpInstantly() {
        this.setPose(Pose.STANDING);
        this.resetLastPoseChangeTickToFullStand(this.level().getGameTime());
    }

    @VisibleForTesting
    public void resetLastPoseChangeTick(long lastPoseChangeTick) {
        this.entityData.set(LAST_POSE_CHANGE_TICK, lastPoseChangeTick);
    }

    private void resetLastPoseChangeTickToFullStand(long lastPoseChangedTick) {
        this.resetLastPoseChangeTick(Math.max(0L, lastPoseChangedTick - 52L - 1L));
    }

    public long getPoseTime() {
        return this.level().getGameTime() - Math.abs((Long)this.entityData.get(LAST_POSE_CHANGE_TICK));
    }

    public SoundEvent getSaddleSoundEvent() {
        return SoundEvents.CAMEL_SADDLE;
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (!this.firstTick && DASH.equals(key)) {
            this.dashCooldown = this.dashCooldown == 0 ? 55 : this.dashCooldown;
        }

        super.onSyncedDataUpdated(key);
    }

    public boolean isTamed() {
        return true;
    }

    public void openCustomInventoryScreen(Player player) {
        if (!this.level().isClientSide) {
            player.openHorseInventory(this, this.inventory);
        }

    }

    protected BodyRotationControl createBodyControl() {
        return new BanthaBodyRotationControl(this);
    }

    static double createOffspringAttribute(double value1, double value2, double min, double max, RandomSource random) {
        if (max <= min) {
            throw new IllegalArgumentException("Incorrect range for an attribute");
        } else {
            value1 = Mth.clamp(value1, min, max);
            value2 = Mth.clamp(value2, min, max);
            double d = 0.15 * (max - min);
            double e = Math.abs(value1 - value2) + d * 2.0;
            double f = (value1 + value2) / 2.0;
            double g = (random.nextDouble() + random.nextDouble() + random.nextDouble()) / 3.0 - 0.5;
            double h = f + e * g;
            double i;
            if (h > max) {
                i = h - max;
                return max - i;
            } else if (h < min) {
                i = min - h;
                return min + i;
            } else {
                return h;
            }
        }
    }

    @Override
    public boolean isSaddled() {
        return true;
    }

    static {
        TEMPTATION_ITEM = Ingredient.of(new ItemLike[]{Items.WHEAT});
        DASH = SynchedEntityData.defineId(BanthaEntity.class, EntityDataSerializers.BOOLEAN);
        LAST_POSE_CHANGE_TICK = SynchedEntityData.defineId(BanthaEntity.class, EntityDataSerializers.LONG);
        SITTING_DIMENSIONS = EntityDimensions.scalable(EntityType.CAMEL.getWidth(), EntityType.CAMEL.getHeight() - 1.43F);
    }

    private class BanthaMoveControl extends MoveControl {
        public BanthaMoveControl() {
            super(BanthaEntity.this);
        }

        public void tick() {
            if (this.operation == Operation.MOVE_TO && !BanthaEntity.this.isLeashed() && BanthaEntity.this.isBanthaSitting() && !BanthaEntity.this.isInPoseTransition() && BanthaEntity.this.canBanthaChangePose()) {
                BanthaEntity.this.standUp();
            }


            super.tick();
        }
    }

    class BanthaLookControl extends LookControl {
        BanthaLookControl() {
            super(BanthaEntity.this);
        }

        public void tick() {
            if (!BanthaEntity.this.hasControllingPassenger()) {
                super.tick();
            }

        }
    }

    class BanthaBodyRotationControl extends BodyRotationControl {
        public BanthaBodyRotationControl(BanthaEntity bantha) {
            super(bantha);
        }

        public void clientTick() {
            if (!BanthaEntity.this.refuseToMove()) {
                super.clientTick();
            }

        }
    }
}
