package fr.tathan.swplanets;

import fr.tathan.swplanets.registry.TagsRegistry;
import fr.tathan.swplanets.world.features.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.dimension.LevelStem;

import java.util.function.Predicate;

public class OreGeneration {
    public static void generateOres() {

        BiomeModifications.addFeature(BiomeSelectors.tag(TagsRegistry.STARWARS_PLANETS),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.KYBER_CRYSTAL_PLACED.getResourceKey());

        BiomeModifications.addFeature(BiomeSelectors.tag(TagsRegistry.TATOOINE_LIKE),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.TATOOINE_KYBER_CRYSTAL_PLACED.getResourceKey());


    }

}
