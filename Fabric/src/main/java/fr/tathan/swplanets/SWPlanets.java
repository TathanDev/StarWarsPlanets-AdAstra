package fr.tathan.swplanets;

import net.fabricmc.api.ModInitializer;

public class SWPlanets implements ModInitializer {

	@Override
	public void onInitialize() {

		Constants.LOG.info("Star Wars Planets 🚀!");
		CommonClass.init();
	}
}
