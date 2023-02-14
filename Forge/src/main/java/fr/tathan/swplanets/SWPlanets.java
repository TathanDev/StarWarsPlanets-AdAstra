package fr.tathan.swplanets;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MODID)
public class SWPlanets {
    
    public SWPlanets() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        Constants.LOG.info("Star Wars Planets 🚀!");
        CommonClass.init();

    }

}