package fr.tathan.swplanets.world.features;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.registry.RegistrationProvider;
import fr.tathan.swplanets.registry.RegistryObject;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;



import java.util.List;

public class ModPlacedFeatures {

    public static final RegistrationProvider<PlacedFeature> PLACED_FEATURES  = RegistrationProvider.get(Registry.PLACED_FEATURE_REGISTRY, Constants.MODID);

    public static final RegistryObject<PlacedFeature> TATOOINE_KYBER_CRYSTAL_PLACED = PLACED_FEATURES.register("sandstone_kyber_crystal_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.SANDSTONE_KYBER_CRYSTAL.asHolder(),
                    rareOrePlacement(2, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> KYBER_CRYSTAL_PLACED = PLACED_FEATURES.register("kyber_crystal_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.KYBER_CRYSTAL.asHolder(),
                    rareOrePlacement(2,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));



    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }


    public static void init() {
    }

    GenerationStep.Decoration ores = GenerationStep.Decoration.UNDERGROUND_ORES;



}
