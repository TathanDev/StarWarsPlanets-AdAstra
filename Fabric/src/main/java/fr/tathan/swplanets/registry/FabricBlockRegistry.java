package fr.tathan.swplanets.registry;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.blocks.BlasterUpgrader;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class FabricBlockRegistry {

    public static final Block BLASTER_UPGRADER = registerBlock("blaster_upgrader",
            new BlasterUpgrader(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));


    private static Block registerBlock(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MODID, name), block);
    }

    public static void init() {}

}
