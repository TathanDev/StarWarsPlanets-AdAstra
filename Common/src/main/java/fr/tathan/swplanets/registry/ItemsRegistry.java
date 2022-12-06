package fr.tathan.swplanets.registry;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.items.JediLightSaber;
import fr.tathan.swplanets.items.SithLightSaber;
import net.minecraft.core.Registry;
import net.minecraft.world.item.*;

import java.util.function.Supplier;

public class ItemsRegistry {



    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registry.ITEM, Constants.MODID);

    /**Jedi Light Saber*/
    public static final RegistryObject<SwordItem> BLUE_LIGHT_SABER = ITEMS.register("light_saber_blue", () -> new JediLightSaber(Tiers.NETHERITE,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<SwordItem> GREEN_LIGHT_SABER = ITEMS.register("light_saber_green", () -> new JediLightSaber(Tiers.NETHERITE,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<SwordItem> VIOLET_LIGHT_SABER = ITEMS.register("light_saber_violet", () -> new JediLightSaber(Tiers.NETHERITE,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));

    /** Sith Light Sabers **/
    public static final RegistryObject<SwordItem> RED_LIGHT_SABER = ITEMS.register("light_saber_red", () -> new SithLightSaber(Tiers.NETHERITE,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<SwordItem> KYLO_REN_LIGHT_SABER = ITEMS.register("light_saber_kylo_ren", () -> new SithLightSaber(Tiers.NETHERITE,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<SwordItem> DARK_MAUL_LIGHT_SABER = ITEMS.register("light_saber_dark_maul", () -> new SithLightSaber(Tiers.NETHERITE,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));



    public static void init() {
    }
    public static <T extends Item> RegistryObject<T> register(String itemName, Supplier<T> item) {
        return ITEMS.register(itemName, item);
    }



}
