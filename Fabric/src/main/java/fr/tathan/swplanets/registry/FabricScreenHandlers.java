package fr.tathan.swplanets.registry;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.menu.BlasterUpgraderScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;

public class FabricScreenHandlers {

    public static final MenuType<BlasterUpgraderScreenHandler> BLASTER_UPGRADER_SCREEN_HANDLER =
            Registry.register(BuiltInRegistries.MENU, new ResourceLocation(Constants.MODID, "blaster_upgrader"),
                    new ExtendedScreenHandlerType<>(BlasterUpgraderScreenHandler::new));

    public static void init() {
    }

}
