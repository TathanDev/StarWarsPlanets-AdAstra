package fr.tathan.swplanets.common.entities.ai;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import fr.tathan.swplanets.common.entities.BanthaEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Predicate;

public class BanthaAi {
    private static final float SPEED_MULTIPLIER_WHEN_PANICKING = 4.0F;
    private static final float SPEED_MULTIPLIER_WHEN_IDLING = 2.0F;
    private static final float SPEED_MULTIPLIER_WHEN_TEMPTED = 2.5F;
    private static final float SPEED_MULTIPLIER_WHEN_FOLLOWING_ADULT = 2.5F;
    private static final float SPEED_MULTIPLIER_WHEN_MAKING_LOVE = 1.0F;
    private static final UniformInt ADULT_FOLLOW_RANGE = UniformInt.of(5, 16);
    private static final ImmutableList<SensorType<? extends Sensor<? super BanthaEntity>>> SENSOR_TYPES;
    private static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES;

    public BanthaAi() {
    }

    public static void initMemories(BanthaEntity bantha, RandomSource random) {
    }

    public static Brain.Provider<BanthaEntity> brainProvider() {
        return Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
    }

    public static Brain<?> makeBrain(Brain<BanthaEntity> brain) {
        initCoreActivity(brain);
        initIdleActivity(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.useDefaultActivity();
        return brain;
    }

    private static void initCoreActivity(Brain<BanthaEntity> brain) {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(new Swim(0.8F), new BanthaAi.BanthaPanic(1.5F), new LookAtTargetSink(45, 90), new MoveToTargetSink(), new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS), new CountDownCooldownTicks(MemoryModuleType.GAZE_COOLDOWN_TICKS)));
    }

    private static void initIdleActivity(Brain<BanthaEntity> brain) {
        brain.addActivity(Activity.IDLE, ImmutableList.of(Pair.of(0, SetEntityLookTargetSometimes.create(EntityType.PLAYER, 6.0F, UniformInt.of(30, 60))), Pair.of(1, new AnimalMakeLove(EntityType.CAMEL, 1.0F)), Pair.of(2, new RunOne(ImmutableList.of(Pair.of(new FollowTemptation((livingEntity) -> {
            return 2.5F;
        }, (livingEntity) -> {
            return livingEntity.isBaby() ? 2.5 : 3.5;
        }), 1), Pair.of(BehaviorBuilder.triggerIf(Predicate.not(BanthaEntity::refuseToMove), BabyFollowAdult.create(ADULT_FOLLOW_RANGE, 2.5F)), 1)))), Pair.of(3, new RandomLookAround(UniformInt.of(150, 250), 30.0F, 0.0F, 0.0F)), Pair.of(4, new RunOne(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT), ImmutableList.of(Pair.of(BehaviorBuilder.triggerIf(Predicate.not(BanthaEntity::refuseToMove), RandomStroll.stroll(1.5F)), 1), Pair.of(BehaviorBuilder.triggerIf(Predicate.not(BanthaEntity::refuseToMove), SetWalkTargetFromLookTarget.create(2.0F, 3)), 1), Pair.of(new BanthaAi.RandomSitting(20), 1), Pair.of(new DoNothing(30, 60), 1))))));
    }

    public static void updateActivity(BanthaEntity camel) {
        camel.getBrain().setActiveActivityToFirstValid(ImmutableList.of(Activity.IDLE));
    }

    public static Ingredient getTemptations() {
        return BanthaEntity.TEMPTATION_ITEM;
    }

    static {
        SENSOR_TYPES = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.HURT_BY, SensorType.CAMEL_TEMPTATIONS, SensorType.NEAREST_ADULT);
        MEMORY_TYPES = ImmutableList.of(MemoryModuleType.IS_PANICKING, MemoryModuleType.HURT_BY, MemoryModuleType.HURT_BY_ENTITY, MemoryModuleType.WALK_TARGET, MemoryModuleType.LOOK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, MemoryModuleType.TEMPTING_PLAYER, MemoryModuleType.TEMPTATION_COOLDOWN_TICKS, MemoryModuleType.GAZE_COOLDOWN_TICKS, MemoryModuleType.IS_TEMPTED, new MemoryModuleType[]{MemoryModuleType.BREED_TARGET, MemoryModuleType.NEAREST_VISIBLE_ADULT});
    }

    public static class BanthaPanic extends AnimalPanic {
        public BanthaPanic(float f) {
            super(f);
        }

        protected void start(ServerLevel level, PathfinderMob entity, long gameTime) {
            if (entity instanceof BanthaEntity bantha) {
                bantha.standUpInstantly();
            }

            super.start(level, entity, gameTime);
        }
    }

    public static class RandomSitting extends Behavior<BanthaEntity> {
        private final int minimalPoseTicks;

        public RandomSitting(int minimalPoseSeconds) {
            super(ImmutableMap.of());
            this.minimalPoseTicks = minimalPoseSeconds * 20;
        }

        protected boolean checkExtraStartConditions(ServerLevel level, BanthaEntity owner) {
            return !owner.isInWater() && owner.getPoseTime() >= (long)this.minimalPoseTicks && !owner.isLeashed() && owner.onGround() && !owner.hasControllingPassenger() && owner.canBanthaChangePose();
        }

        protected void start(ServerLevel level, BanthaEntity entity, long gameTime) {
            if (entity.isBanthaSitting()) {
                entity.standUp();
                //TODO check panick
            } else {
                entity.sitDown();
            }

        }
    }

}
