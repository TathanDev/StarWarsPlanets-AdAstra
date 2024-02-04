package fr.tathan.swplanets.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.adastra.common.blocks.base.MachineBlock;
import earth.terrarium.adastra.common.registry.ModBlocks;
import fr.tathan.swplanets.Constants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class BlocksRegistry {

    public static final ResourcefulRegistry<Block> BLOCKS = ResourcefulRegistries.create(BuiltInRegistries.BLOCK, Constants.MODID);

    public static final BlockBehaviour.Properties IRON_PROPERTIES = BlockBehaviour.Properties.of()
        .mapColor(MapColor.METAL)
        .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
        .requiresCorrectToolForDrops()
        .strength(5, 6)
        .sound(SoundType.COPPER);

    /** ORES */
    public static final RegistryEntry<Block> CRYSTAL_KYBER_ORE = BLOCKS.register("kyber_crystal_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).sound(SoundType.STONE).strength(3.0F, 3.0F).explosionResistance(3).requiresCorrectToolForDrops()));
    public static final RegistryEntry<Block> CRYSTAL_KYBER_ORE_SANDSTONE = BLOCKS.register("kyber_crystal_ore_sandstone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).sound(SoundType.STONE).strength(3.0F, 3.0F).explosionResistance(3).requiresCorrectToolForDrops()));

    public static final RegistryEntry<Block> BESKAR_ORE_SANDSTONE = BLOCKS.register("beskar_ore_sandstone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).sound(SoundType.STONE).strength(3.0F, 3.0F).explosionResistance(3).requiresCorrectToolForDrops()));

   //public static final RegistryObject<Block> MUSTAFAR_SAND = BLOCKS.register("mustafar_sand", () -> new FallingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).sound(SoundType.SAND).strength(0.5f, 0.5f)));
    public static final RegistryEntry<Block> MUSTAFAR_STONE = BLOCKS.register("mustafar_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));

    public static final RegistryEntry<Block> BLASTER_UPGRADER = BLOCKS.register("blaster_upgrader", () -> new MachineBlock(IRON_PROPERTIES));


}
