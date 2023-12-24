package fr.tathan.swplanets.common.registry;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.entity.LaserEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import fr.tathan.swplanets.common.platform.Services;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.SmallFireball;

import java.util.function.Supplier;

public class EntityRegistry {

    private static final RegistrationProvider<EntityType<?>> ENTITY_TYPES = RegistrationProvider.get(Registries.ENTITY_TYPE, Constants.MODID);

    public static final RegistryObject<EntityType<LaserEntity>> LASER = register("laser",() -> EntityType.Builder.<LaserEntity>of(LaserEntity::new, MobCategory.MISC).fireImmune().sized(0.4f, 0.4f).build(new ResourceLocation(Constants.MODID, "laser").toString()));

    public static void init() {

    }

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String name, Supplier<EntityType<T>> entityType) {
        return ENTITY_TYPES.register(name, entityType);
    }

}
