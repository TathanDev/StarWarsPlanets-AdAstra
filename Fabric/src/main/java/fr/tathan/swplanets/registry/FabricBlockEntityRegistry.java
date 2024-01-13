package fr.tathan.swplanets.registry;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.blocks.entity.BlasterUpgraderEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class FabricBlockEntityRegistry {

    public static final BlockEntityType<BlasterUpgraderEntity> BLASTER_UPGRADER_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(Constants.MODID, "blaster_upgrader_be"),
                    FabricBlockEntityTypeBuilder.create(BlasterUpgraderEntity::new,
                            FabricBlockRegistry.BLASTER_UPGRADER).build());

    public static void init() {}
}
