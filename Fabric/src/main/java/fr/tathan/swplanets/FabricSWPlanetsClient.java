package fr.tathan.swplanets;

import earth.terrarium.adastra.common.registry.ModItems;
import fr.tathan.swplanets.client.SWPlanetsClient;
import fr.tathan.swplanets.common.registry.EntityRegistry;
import fr.tathan.swplanets.common.registry.ItemsRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.level.ItemLike;

import java.util.function.BiConsumer;

public class FabricSWPlanetsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SWPlanetsClient.init();
        EntityRendererRegistry.register(EntityRegistry.LASER.get(), (p_174060_) -> {
            return new ThrownItemRenderer<>(p_174060_, 0.75F, true);
        });

        SWPlanetsClient.onRegisterEntityLayers((location, definition) -> EntityModelLayerRegistry.registerModelLayer(location, definition::get));


        ColorProviderRegistry.ITEM.register((stack, i) -> i > 0 ? -1 : ((DyeableArmorItem) stack.getItem()).getColor(stack), new ItemLike[]{ItemsRegistry.STORMTROOPER_MASK.get(), ItemsRegistry.STORMTROOPER_CHESTPLATE.get(), ItemsRegistry.STORMTROOPER_LEGGINGS.get(), ItemsRegistry.STORMTROOPER_BOOTS.get()});
        FabricLoader.getInstance().getModContainer(Constants.MODID).ifPresent(modContainer -> {
            ResourceManagerHelper.registerBuiltinResourcePack(new ResourceLocation("swplanets:old_lightsabers_models"), modContainer, ResourcePackActivationType.NORMAL);

        });
    }

}
