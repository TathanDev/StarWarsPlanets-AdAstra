package fr.tathan.swplanets.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.entities.LaserEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class EntityRegistry {

    public static final ResourcefulRegistry<EntityType<?>> ENTITY_TYPES = ResourcefulRegistries.create(BuiltInRegistries.ENTITY_TYPE, Constants.MODID);

    public static final RegistryEntry<EntityType<LaserEntity>> LASER = ENTITY_TYPES.register("laser",() -> EntityType.Builder.<LaserEntity>of(LaserEntity::new, MobCategory.MISC).fireImmune().sized(0.4f, 0.4f).build(new ResourceLocation(Constants.MODID, "laser").toString()));


}
