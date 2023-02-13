package fr.tathan.swplanets;

import net.fabricmc.api.ModInitializer;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public class SWPlanets implements ModInitializer {

	@Override
	public void onInitialize() {

		Constants.LOG.info("Star Wars Planets 🚀!");
		CommonClass.init();

	}

}
