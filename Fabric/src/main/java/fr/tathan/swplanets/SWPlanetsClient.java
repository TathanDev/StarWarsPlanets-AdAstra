package fr.tathan.swplanets;

import fr.tathan.swplanets.client.renderer.LaserModel;
import fr.tathan.swplanets.client.renderer.LaserRenderer;
import fr.tathan.swplanets.common.registry.EntityRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class SWPlanetsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntityRegistry.LASER.get(), LaserRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(LaserModel.LAYER_LOCATION, LaserModel::createBodyLayer);
    }
}
