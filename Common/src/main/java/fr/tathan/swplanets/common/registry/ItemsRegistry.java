package fr.tathan.swplanets.common.registry;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.items.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.List;

public class ItemsRegistry {

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, Constants.MODID);

    public static final RegistryObject<Item> KYBER_CRYSTAL = ITEMS.register("kyber_crystal", () -> new Item(new Item.Properties().stacksTo(16)));

    /** Light Saber */
    public static final RegistryObject<Item> LIGHT_SABER_BASE = ITEMS.register("light_saber_base", () -> new Item(new Item.Properties().stacksTo(1)));


    /**Jedi Light Saber*/
    public static final RegistryObject<Item> BLUE_LIGHT_SABER = ITEMS.register("light_saber_blue", () -> new JediLightSaber(StarWarsTiers.LIGHT_SABER,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> GREEN_LIGHT_SABER = ITEMS.register("light_saber_green", () -> new JediLightSaber(StarWarsTiers.LIGHT_SABER,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> VIOLET_LIGHT_SABER = ITEMS.register("light_saber_violet", () -> new JediLightSaber(StarWarsTiers.LIGHT_SABER,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));

    /** Sith Light Sabers **/
    public static final RegistryObject<Item> RED_LIGHT_SABER = ITEMS.register("light_saber_red", () -> new SithLightSaber(StarWarsTiers.LIGHT_SABER,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> KYLO_REN_LIGHT_SABER = ITEMS.register("light_saber_kylo_ren", () -> new SithLightSaber(StarWarsTiers.LIGHT_SABER,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> DARK_MAUL_LIGHT_SABER = ITEMS.register("light_saber_dark_maul", () -> new SithLightSaber(StarWarsTiers.LIGHT_SABER,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> PLASTIC_PLATE = ITEMS.register("plastic_plate", () -> new Item(new Item.Properties().stacksTo(64)));

    /** Mandalorian **/
    public static final RegistryObject<Item> DARKSABER = ITEMS.register("darksaber", () -> new SwordItem(StarWarsTiers.BESKAR, 8, -2.5F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> BESKAR = ITEMS.register("beskar", () -> new Item(new Item.Properties().fireResistant().stacksTo(16)));
    public static final RegistryObject<BlockItem> BESKAR_ORE_SANDSTONE_ITEM = ITEMS.register("beskar_ore_sandstone", () -> new BlockItem(BlocksRegistry.BESKAR_ORE_SANDSTONE.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> KYBER_CRYSTAl_ORE_ITEM = ITEMS.register("kyber_crystal_ore", () -> new BlockItem(BlocksRegistry.CRYSTAL_KYBER_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> KYBER_CRYSTAl_ORE_SANDSTONE_ITEM = ITEMS.register("kyber_crystal_ore_sandstone", () -> new BlockItem(BlocksRegistry.CRYSTAL_KYBER_ORE_SANDSTONE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MUSTAFAR_STONE_ITEM = ITEMS.register("mustafar_stone", () -> new BlockItem(BlocksRegistry.MUSTAFAR_STONE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MUSTAFAR_SAND_ITEM = ITEMS.register("mustafar_sand", () -> new BlockItem(BlocksRegistry.MUSTAFAR_SAND.get(), new Item.Properties()));
    public static final RegistryObject<Item> LASER_ITEM = ITEMS.register("laser_item", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAR_MANUAL = ITEMS.register("star_manual", () -> new StarManual(new Item.Properties()));

    public static final RegistryObject<ArmorItem> STORMTROOPER_MASK = ITEMS.register("stormtrooper_mask",
            () -> new ArmorItem(ArmorMaterialRegistry.STORMTROOPER_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties()));

    public static final RegistryObject<Item> STORMTROOPER_CHESTPLATE = ITEMS.register("stormtrooper_chestplate",
            () -> new ArmorItem(ArmorMaterialRegistry.STORMTROOPER_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties()));
    public static final RegistryObject<Item> STORMTROOPER_LEGGINGS = ITEMS.register("stormtrooper_leggings",
            () -> new ArmorItem(ArmorMaterialRegistry.STORMTROOPER_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties()));
    public static final RegistryObject<Item> STORMTROOPER_BOOTS = ITEMS.register("stormtrooper_boots",
            () -> new ArmorItem(ArmorMaterialRegistry.STORMTROOPER_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties()));


    /** Beskar*/
    public static final RegistryObject<ArmorItem> BESKAR_HELMET = ITEMS.register("beskar_armor_helmet",
            () -> new ArmorItem(ArmorMaterialRegistry.BESKAR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties()));

    public static final RegistryObject<Item> BESKAR_CHESTPLATE = ITEMS.register("beskar_armor_chestplate",
            () -> new ArmorItem(ArmorMaterialRegistry.BESKAR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties()));
    public static final RegistryObject<Item> BESKAR_LEGGINGS = ITEMS.register("beskar_armor_leggings",
            () -> new ArmorItem(ArmorMaterialRegistry.BESKAR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties()));
    public static final RegistryObject<Item> BESKAR_BOOTS = ITEMS.register("beskar_armor_boots",
            () -> new ArmorItem(ArmorMaterialRegistry.BESKAR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties()));
    public static void init() {
    }

    /** Blaster */
    //Blasters
    public static final RegistryObject<Item> BLASTER = ITEMS.register("blaster", () -> new Blaster(new Item.Properties().durability(2000)));
    public static final RegistryObject<Item> BLASTER_ZOOM = ITEMS.register("blaster_zoom", () -> new Blaster(new Item.Properties().durability(2000), true));

    // Blaster Upgrade
    public static final RegistryObject<Item> BLASTER_ZOOM_UPGRADE = ITEMS.register("blaster_zoom_upgrade", () -> new BlasterUpgrade(new Item.Properties().stacksTo(16), true, 0));

    public static void onRegisterCreativeTabs(TriConsumer<ResourceLocation, RegistryObject<Item>, List<Item>> consumer) {
        consumer.accept(new ResourceLocation(Constants.MODID, "main"), ItemsRegistry.BLUE_LIGHT_SABER, BuiltInRegistries.ITEM.stream().filter(i -> BuiltInRegistries.ITEM.getKey(i).getNamespace().equals(Constants.MODID)).toList());
    }


}
