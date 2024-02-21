package fr.tathan.swplanets;

import com.mojang.datafixers.kinds.Const;
import fr.tathan.swplanets.common.registry.EntityRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.ModContainer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;

import static net.fabricmc.fabric.api.resource.ResourceManagerHelper.registerBuiltinResourcePack;

public class SWPlanetsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntityRegistry.LASER.get(), (p_174060_) -> {
            return new ThrownItemRenderer<>(p_174060_, 0.75F, true);
        });
        FabricLoader.getInstance().getModContainer(Constants.MODID).ifPresent(modContainer -> {
            ResourceManagerHelper.registerBuiltinResourcePack(new ResourceLocation("swplanets:old_lightsabers_models"), modContainer, ResourcePackActivationType.NORMAL);

        });

        }
}
