package fr.tathan.swplanets.world.features;

import com.google.common.base.Suppliers;
import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.registry.BlocksRegistry;
import fr.tathan.swplanets.registry.RegistrationProvider;
import fr.tathan.swplanets.registry.RegistryObject;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {


    public static final RegistrationProvider<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES  = RegistrationProvider.get(Registry.CONFIGURED_FEATURE_REGISTRY, Constants.MODID);



    public static final Supplier<List<OreConfiguration.TargetBlockState>> TATOOINE_KYBER_CRYSTALS = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.SANDSTONE), BlocksRegistry.CRYSTAL_KYBER_ORE_SANDSTONE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> DEEPSLATE_KYBER_CRYSTAL = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlocksRegistry.CRYSTAL_KYBER_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> SANDSTONE_KYBER_CRYSTAL = CONFIGURED_FEATURES.register("zircon_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(TATOOINE_KYBER_CRYSTALS.get(),7)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> KYBER_CRYSTAL = CONFIGURED_FEATURES.register("zircon_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(DEEPSLATE_KYBER_CRYSTAL.get(),7)));


    public static void init() {
    }

}
