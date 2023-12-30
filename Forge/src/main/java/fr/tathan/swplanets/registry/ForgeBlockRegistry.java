package fr.tathan.swplanets.registry;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.blocks.BlasterUpgraderBlock;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ForgeBlockRegistry {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MODID);

    public static final RegistryObject<Block> BLASTER_UPGRADER = BLOCKS.register("blaster_upgrader",
            () -> new BlasterUpgraderBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }


}
