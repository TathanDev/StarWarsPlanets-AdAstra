package fr.tathan.swplanets.registry;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.menu.BlasterUpgraderMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ForgeMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Constants.MODID);

    public static final RegistryObject<MenuType<BlasterUpgraderMenu>> BLASTER_UPGRADER_MENU =
            MENUS.register("blaster_upgrader_menu", () -> IForgeMenuType.create(BlasterUpgraderMenu::new));


}
