package fr.tathan.swplanets.client;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.client.renderer.LaserModel;
import fr.tathan.swplanets.client.renderer.LaserRenderer;
import fr.tathan.swplanets.common.registry.EntityRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SWPlanetsClient {

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LaserModel.LAYER_LOCATION, LaserModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegistry.LASER.get(), LaserRenderer::new);
    }
}
