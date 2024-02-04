package fr.tathan.swplanets;

import fr.tathan.swplanets.client.SWPlanetsClient;
import fr.tathan.swplanets.common.registry.EntityRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class FabricSWPlanetsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SWPlanetsClient.init();
        EntityRendererRegistry.register(EntityRegistry.LASER.get(), (p_174060_) -> {
            return new ThrownItemRenderer<>(p_174060_, 0.75F, true);
        });


    }
}
