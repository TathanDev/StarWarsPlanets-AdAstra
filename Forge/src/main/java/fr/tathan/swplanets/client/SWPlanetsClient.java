package fr.tathan.swplanets.client;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.registry.EntityRegistry;
import fr.tathan.swplanets.menu.BlasterUpgraderScreen;
import fr.tathan.swplanets.registry.ForgeMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Constants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SWPlanetsClient {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegistry.LASER.get(), (p_174060_) -> {
            return new ThrownItemRenderer<>(p_174060_, 0.75F, true);
        });

    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(ForgeMenuTypes.BLASTER_UPGRADER_MENU.get(), BlasterUpgraderScreen::new);

    }

}
