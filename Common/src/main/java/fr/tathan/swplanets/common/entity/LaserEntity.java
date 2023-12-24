package fr.tathan.swplanets.common.entity;

import com.mojang.datafixers.kinds.Const;
import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.registry.EntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class LaserEntity extends AbstractHurtingProjectile {

    public int life;
    public int lifetime;
    public Direction direction;

    public LaserEntity(EntityType<? extends LaserEntity> laser, Level level) {
        super(laser, level);
    }
    public LaserEntity(Level $$0, LivingEntity $$1, double $$2, double $$3, double $$4, Direction direction) {
        super(EntityRegistry.LASER.get(), $$1, $$2, $$3, $$4, $$0);
        this.life = 0;
        this.lifetime = 50 + this.random.nextInt(6) + this.random.nextInt(7);
        this.direction = direction;


    }
    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (!this.level().isClientSide) {
            Entity entity = entityHitResult.getEntity();
            entity.setSecondsOnFire(5);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        if (!this.level().isClientSide) {
            Entity $$1 = this.getOwner();
            if (!($$1 instanceof Mob) || this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                BlockPos blockPos = blockHitResult.getBlockPos().relative(blockHitResult.getDirection());
                if (this.level().isEmptyBlock(blockPos)) {
                    this.level().setBlockAndUpdate(blockPos, BaseFireBlock.getState(this.level(), blockPos));
                }
            }
        }
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide) {
            this.discard();
        }
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource $$0, float $$1) {
        return true;
    }

    @Override
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.LAVA;
    }


    @Override
    public void addAdditionalSaveData(CompoundTag $$0) {
        super.addAdditionalSaveData($$0);
        $$0.putInt("Life", this.life);
        $$0.putInt("LifeTime", this.lifetime);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag $$0) {
        super.readAdditionalSaveData($$0);
        this.life = $$0.getInt("Life");
        this.lifetime = $$0.getInt("LifeTime");
    }

    @Override
    public void tick() {
        super.tick();
        ++this.life;
        if(!this.level().isClientSide && this.life > this.lifetime ) {
            Constants.LOG.error("LaserEntity:" + life + ">" + lifetime);
            this.discard();
        }

    }


}
