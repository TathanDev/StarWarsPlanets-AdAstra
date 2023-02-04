package fr.tathan.swplanets;

import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MODID)
public class SWPlanets {
    
    public SWPlanets() {

        Constants.LOG.info("Star Wars Planets 🚀!");
        CommonClass.init();
    }
}