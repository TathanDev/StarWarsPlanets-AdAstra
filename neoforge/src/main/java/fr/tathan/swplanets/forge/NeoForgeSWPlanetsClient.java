package fr.tathan.swplanets.forge;

import earth.terrarium.adastra.client.AdAstraClient;
import fr.tathan.swplanets.client.SWPlanetsClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeoForgeSWPlanetsClient {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(SWPlanetsClient::init);
    }
}
