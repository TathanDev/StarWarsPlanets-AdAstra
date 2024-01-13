package fr.tathan.swplanets;

import fr.tathan.swplanets.common.registry.EntityRegistry;
import fr.tathan.swplanets.menu.BlasterUpgraderScreen;
import fr.tathan.swplanets.registry.FabricScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class SWPlanetsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntityRegistry.LASER.get(), (p_174060_) -> {
            return new ThrownItemRenderer<>(p_174060_, 0.75F, true);
        });

        MenuScreens.register(FabricScreenHandlers.BLASTER_UPGRADER_SCREEN_HANDLER, BlasterUpgraderScreen::new);

    }
}
