package fr.tathan.swplanets.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.botarium.common.registry.RegistryHelpers;
import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.blocks.entities.BlasterUpgraderEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockEntitiesRegistry {

    public static final ResourcefulRegistry<BlockEntityType<?>> BLOCK_ENTITY_TYPES = ResourcefulRegistries.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Constants.MODID);

    public static final RegistryEntry<BlockEntityType<BlasterUpgraderEntity>> BLASTER_UPGRADER = BLOCK_ENTITY_TYPES.register(
        "blaster_upgrader",
        () -> RegistryHelpers.createBlockEntityType(
            BlasterUpgraderEntity::new,
            BlocksRegistry.BLASTER_UPGRADER.get()));


}
