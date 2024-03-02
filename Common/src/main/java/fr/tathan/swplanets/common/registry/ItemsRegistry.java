package fr.tathan.swplanets.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.adastra.common.items.armor.base.CustomDyeableArmorItem;
import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.items.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;
public class ItemsRegistry {

   // public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, Constants.MODID);
    public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(BuiltInRegistries.ITEM, Constants.MODID);

    public static final ResourcefulRegistry<Item> TAB_ITEMS = ResourcefulRegistries.create(ITEMS);
    public static final RegistryEntry<Item> KYBER_CRYSTAL = TAB_ITEMS.register("kyber_crystal", () -> new Item(new Item.Properties().stacksTo(16)));

    /** Light Saber */
    public static final RegistryEntry<Item> LIGHT_SABER_BASE = TAB_ITEMS.register("light_saber_base", () -> new Item(new Item.Properties().stacksTo(1)));


    /**Jedi Light Saber*/
    public static final RegistryEntry<Item> BLUE_LIGHT_SABER = TAB_ITEMS.register("light_saber_blue", () -> new JediLightSaber(StarWarsTiers.LIGHT_SABER,11, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryEntry<Item> GREEN_LIGHT_SABER = TAB_ITEMS.register("light_saber_green", () -> new JediLightSaber(StarWarsTiers.LIGHT_SABER,11, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryEntry<Item> VIOLET_LIGHT_SABER = TAB_ITEMS.register("light_saber_violet", () -> new JediLightSaber(StarWarsTiers.LIGHT_SABER,11, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));

    /** Sith Light Sabers **/
    public static final RegistryEntry<Item> RED_LIGHT_SABER = TAB_ITEMS.register("light_saber_red", () -> new SithLightSaber(StarWarsTiers.LIGHT_SABER,11, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryEntry<Item> KYLO_REN_LIGHT_SABER = TAB_ITEMS.register("light_saber_kylo_ren", () -> new SithLightSaber(StarWarsTiers.LIGHT_SABER,11, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryEntry<Item> DARK_MAUL_LIGHT_SABER = TAB_ITEMS.register("light_saber_dark_maul", () -> new SithLightSaber(StarWarsTiers.LIGHT_SABER,11, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryEntry<Item> PLASTIC_PLATE = TAB_ITEMS.register("plastic_plate", () -> new Item(new Item.Properties().stacksTo(64)));

    /** Mandalorian **/
    public static final RegistryEntry<Item> DARKSABER = TAB_ITEMS.register("darksaber", () -> new SwordItem(StarWarsTiers.BESKAR, 11, -2.5F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryEntry<Item> BESKAR = TAB_ITEMS.register("beskar", () -> new Item(new Item.Properties().fireResistant().stacksTo(16)));
    public static final RegistryEntry<BlockItem> BESKAR_ORE_SANDSTONE_ITEM = TAB_ITEMS.register("beskar_ore_sandstone", () -> new BlockItem(BlocksRegistry.BESKAR_ORE_SANDSTONE.get(), new Item.Properties()));

    public static final RegistryEntry<BlockItem> KYBER_CRYSTAl_ORE_ITEM = TAB_ITEMS.register("kyber_crystal_ore", () -> new BlockItem(BlocksRegistry.CRYSTAL_KYBER_ORE.get(), new Item.Properties()));
    public static final RegistryEntry<BlockItem> KYBER_CRYSTAl_ORE_SANDSTONE_ITEM = TAB_ITEMS.register("kyber_crystal_ore_sandstone", () -> new BlockItem(BlocksRegistry.CRYSTAL_KYBER_ORE_SANDSTONE.get(), new Item.Properties()));
    public static final RegistryEntry<BlockItem> MUSTAFAR_STONE_ITEM = TAB_ITEMS.register("mustafar_stone", () -> new BlockItem(BlocksRegistry.MUSTAFAR_STONE.get(), new Item.Properties()));
    //public static final RegistryObject<BlockItem> MUSTAFAR_SAND_ITEM = ITEMS.register("mustafar_sand", () -> new BlockItem(BlocksRegistry.MUSTAFAR_SAND.get(), new Item.Properties()));
    public static final RegistryEntry<Item> LASER_ITEM = ITEMS.register("laser_item", () -> new Item(new Item.Properties()));

    public static final RegistryEntry<Item> STAR_MANUAL = TAB_ITEMS.register("star_manual", () -> new StarManual(new Item.Properties()));

    public static final RegistryEntry<ArmorItem> STORMTROOPER_MASK = TAB_ITEMS.register("stormtrooper_mask",
            () -> new CustomDyeableArmorItem(ArmorMaterialRegistry.STORMTROOPER_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties()));

    public static final RegistryEntry<Item> STORMTROOPER_CHESTPLATE = TAB_ITEMS.register("stormtrooper_chestplate",
            () -> new CustomDyeableArmorItem(ArmorMaterialRegistry.STORMTROOPER_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties()));
    public static final RegistryEntry<Item> STORMTROOPER_LEGGINGS = TAB_ITEMS.register("stormtrooper_leggings",
            () -> new CustomDyeableArmorItem(ArmorMaterialRegistry.STORMTROOPER_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties()));
    public static final RegistryEntry<Item> STORMTROOPER_BOOTS = TAB_ITEMS.register("stormtrooper_boots",
            () -> new CustomDyeableArmorItem(ArmorMaterialRegistry.STORMTROOPER_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties()));

    /** Beskar*/
    public static final RegistryEntry<ArmorItem> BESKAR_HELMET = TAB_ITEMS.register("beskar_armor_helmet",
            () -> new ArmorItem(ArmorMaterialRegistry.BESKAR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties()));

    public static final RegistryEntry<Item> BESKAR_CHESTPLATE = TAB_ITEMS.register("beskar_armor_chestplate",
            () -> new ArmorItem(ArmorMaterialRegistry.BESKAR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties()));
    public static final RegistryEntry<Item> BESKAR_LEGGINGS = TAB_ITEMS.register("beskar_armor_leggings",
            () -> new ArmorItem(ArmorMaterialRegistry.BESKAR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties()));
    public static final RegistryEntry<Item> BESKAR_BOOTS = TAB_ITEMS.register("beskar_armor_boots",
            () -> new ArmorItem(ArmorMaterialRegistry.BESKAR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties()));

    /** Blaster */
    //Blasters
    public static final RegistryEntry<Item> BLASTER = TAB_ITEMS.register("blaster", () -> new Blaster(new Item.Properties().durability(2000)));
    public static final RegistryEntry<Item> BLASTER_ZOOM = ITEMS.register("blaster_zoom", () -> new Blaster(new Item.Properties().durability(2000), true));

    // Blaster Upgrade
    public static final RegistryEntry<Item> BLASTER_ZOOM_UPGRADE = TAB_ITEMS.register("blaster_zoom_upgrade", () -> new BlasterUpgrade(new Item.Properties().stacksTo(16), true, 0));


}
