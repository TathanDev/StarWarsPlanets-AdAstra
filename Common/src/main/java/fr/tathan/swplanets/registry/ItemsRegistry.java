package fr.tathan.swplanets.registry;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.items.JediLightSaber;
import fr.tathan.swplanets.items.SithLightSaber;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.List;

public class ItemsRegistry {

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, Constants.MODID);

    public static final RegistryObject<Item> KYBER_CRYSTAL = ITEMS.register("kyber_crystal", () -> new Item(new Item.Properties().stacksTo(16)));

    /** Light Saber */
    public static final RegistryObject<Item> LIGHT_SABER_BASE = ITEMS.register("light_saber_base", () -> new Item(new Item.Properties().stacksTo(1)));


    /**Jedi Light Saber*/
    public static final RegistryObject<Item> BLUE_LIGHT_SABER = ITEMS.register("light_saber_blue", () -> new JediLightSaber(Tiers.NETHERITE,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> GREEN_LIGHT_SABER = ITEMS.register("light_saber_green", () -> new JediLightSaber(Tiers.NETHERITE,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> VIOLET_LIGHT_SABER = ITEMS.register("light_saber_violet", () -> new JediLightSaber(Tiers.NETHERITE,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));

    /** Sith Light Sabers **/
    public static final RegistryObject<Item> RED_LIGHT_SABER = ITEMS.register("light_saber_red", () -> new SithLightSaber(Tiers.NETHERITE,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> KYLO_REN_LIGHT_SABER = ITEMS.register("light_saber_kylo_ren", () -> new SithLightSaber(Tiers.NETHERITE,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> DARK_MAUL_LIGHT_SABER = ITEMS.register("light_saber_dark_maul", () -> new SithLightSaber(Tiers.NETHERITE,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> DARKSABER = ITEMS.register("darksaber", () -> new SwordItem(Tiers.NETHERITE, 4, -2.5F, new Item.Properties().fireResistant().stacksTo(1).durability(4000)));

    public static final RegistryObject<BlockItem> KYBER_CRYSTAl_ORE_ITEM = ITEMS.register("kyber_crystal_ore", () -> new BlockItem(BlocksRegistry.CRYSTAL_KYBER_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> KYBER_CRYSTAl_ORE_SANDSTONE_ITEM = ITEMS.register("kyber_crystal_ore_sandstone", () -> new BlockItem(BlocksRegistry.CRYSTAL_KYBER_ORE_SANDSTONE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MUSTAFAR_STONE_ITEM = ITEMS.register("mustafar_stone", () -> new BlockItem(BlocksRegistry.MUSTAFAR_STONE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MUSTAFAR_SAND_ITEM = ITEMS.register("mustafar_sand", () -> new BlockItem(BlocksRegistry.MUSTAFAR_SAND.get(), new Item.Properties()));

    //public static final RegistryObject<Item> STAR_MANUAL = ITEMS.register("star_manual", () -> new StarManual(new Item.Properties()));

    public static void init() {
    }

    public static void onRegisterCreativeTabs(TriConsumer<ResourceLocation, RegistryObject<Item>, List<Item>> consumer) {
        consumer.accept(new ResourceLocation(Constants.MODID, "main"), ItemsRegistry.BLUE_LIGHT_SABER, BuiltInRegistries.ITEM.stream().filter(i -> BuiltInRegistries.ITEM.getKey(i).getNamespace().equals(Constants.MODID)).toList());
    }


}
