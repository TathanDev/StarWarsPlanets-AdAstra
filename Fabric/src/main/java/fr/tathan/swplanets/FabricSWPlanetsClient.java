package fr.tathan.swplanets;

import fr.tathan.swplanets.client.SWPlanetsClient;
import fr.tathan.swplanets.common.registry.EntityRegistry;
import fr.tathan.swplanets.common.registry.ItemsRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;

public class FabricSWPlanetsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SWPlanetsClient.init();
        EntityRendererRegistry.register(EntityRegistry.LASER.get(), (p_174060_) -> {
            return new ThrownItemRenderer<>(p_174060_, 0.75F, true);
        });

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0xFFFFFF, ItemsRegistry.STORMTROOPER_MASK.get(), ItemsRegistry.STORMTROOPER_CHESTPLATE.get(), ItemsRegistry.STORMTROOPER_LEGGINGS.get(), ItemsRegistry.STORMTROOPER_LEGGINGS.get());
        FabricLoader.getInstance().getModContainer(Constants.MODID).ifPresent(modContainer -> {
            ResourceManagerHelper.registerBuiltinResourcePack(new ResourceLocation("swplanets:old_lightsabers_models"), modContainer, ResourcePackActivationType.NORMAL);

        });
    }
}
