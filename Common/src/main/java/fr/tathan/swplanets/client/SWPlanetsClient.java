package fr.tathan.swplanets.client;

import earth.terrarium.adastra.client.AdAstraClient;
import earth.terrarium.adastra.client.screens.machines.OxygenDistributorScreen;
import earth.terrarium.adastra.common.registry.ModMenus;
import fr.tathan.swplanets.client.screens.BlasterUpgraderScreen;
import fr.tathan.swplanets.common.blocks.entities.BlasterUpgraderEntity;
import fr.tathan.swplanets.common.registry.MenusRegistry;
import net.minecraft.client.gui.screens.MenuScreens;

public class SWPlanetsClient {

    public static void init() {
        registerScreens();
    }

    private static void registerScreens() {
        MenuScreens.register(MenusRegistry.BLASTER_UPGRADER.get(), BlasterUpgraderScreen::new);

    }
}
