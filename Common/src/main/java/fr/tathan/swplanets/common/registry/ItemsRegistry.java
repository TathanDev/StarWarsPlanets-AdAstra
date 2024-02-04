package fr.tathan.swplanets.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.adastra.AdAstra;
import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.items.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.List;

public class ItemsRegistry {

   // public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, Constants.MODID);
    public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(BuiltInRegistries.ITEM, Constants.MODID);

    public static final RegistryEntry<Item> KYBER_CRYSTAL = ITEMS.register("kyber_crystal", () -> new Item(new Item.Properties().stacksTo(16)));

    /** Light Saber */
    public static final RegistryEntry<Item> LIGHT_SABER_BASE = ITEMS.register("light_saber_base", () -> new Item(new Item.Properties().stacksTo(1)));


    /**Jedi Light Saber*/
    public static final RegistryEntry<Item> BLUE_LIGHT_SABER = ITEMS.register("light_saber_blue", () -> new JediLightSaber(StarWarsTiers.LIGHT_SABER,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryEntry<Item> GREEN_LIGHT_SABER = ITEMS.register("light_saber_green", () -> new JediLightSaber(StarWarsTiers.LIGHT_SABER,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryEntry<Item> VIOLET_LIGHT_SABER = ITEMS.register("light_saber_violet", () -> new JediLightSaber(StarWarsTiers.LIGHT_SABER,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));

    /** Sith Light Sabers **/
    public static final RegistryEntry<Item> RED_LIGHT_SABER = ITEMS.register("light_saber_red", () -> new SithLightSaber(StarWarsTiers.LIGHT_SABER,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryEntry<Item> KYLO_REN_LIGHT_SABER = ITEMS.register("light_saber_kylo_ren", () -> new SithLightSaber(StarWarsTiers.LIGHT_SABER,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryEntry<Item> DARK_MAUL_LIGHT_SABER = ITEMS.register("light_saber_dark_maul", () -> new SithLightSaber(StarWarsTiers.LIGHT_SABER,3, -2.4F, new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryEntry<Item> PLASTIC_PLATE = ITEMS.register("plastic_plate", () -> new Item(new Item.Properties().stacksTo(64)));

    /** Mandalorian **/
    public static final RegistryEntry<Item> DARKSABER = ITEMS.register("darksaber", () -> new SwordItem(StarWarsTiers.BESKAR, 8, -2.5F, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryEntry<Item> BESKAR = ITEMS.register("beskar", () -> new Item(new Item.Properties().fireResistant().stacksTo(16)));
    public static final RegistryEntry<BlockItem> BESKAR_ORE_SANDSTONE_ITEM = ITEMS.register("beskar_ore_sandstone", () -> new BlockItem(BlocksRegistry.BESKAR_ORE_SANDSTONE.get(), new Item.Properties()));

    public static final RegistryEntry<BlockItem> KYBER_CRYSTAl_ORE_ITEM = ITEMS.register("kyber_crystal_ore", () -> new BlockItem(BlocksRegistry.CRYSTAL_KYBER_ORE.get(), new Item.Properties()));
    public static final RegistryEntry<BlockItem> KYBER_CRYSTAl_ORE_SANDSTONE_ITEM = ITEMS.register("kyber_crystal_ore_sandstone", () -> new BlockItem(BlocksRegistry.CRYSTAL_KYBER_ORE_SANDSTONE.get(), new Item.Properties()));
    public static final RegistryEntry<BlockItem> MUSTAFAR_STONE_ITEM = ITEMS.register("mustafar_stone", () -> new BlockItem(BlocksRegistry.MUSTAFAR_STONE.get(), new Item.Properties()));
    //public static final RegistryObject<BlockItem> MUSTAFAR_SAND_ITEM = ITEMS.register("mustafar_sand", () -> new BlockItem(BlocksRegistry.MUSTAFAR_SAND.get(), new Item.Properties()));
    public static final RegistryEntry<Item> LASER_ITEM = ITEMS.register("laser_item", () -> new Item(new Item.Properties()));

    public static final RegistryEntry<Item> STAR_MANUAL = ITEMS.register("star_manual", () -> new StarManual(new Item.Properties()));

    public static final RegistryEntry<ArmorItem> STORMTROOPER_MASK = ITEMS.register("stormtrooper_mask",
            () -> new ArmorItem(ArmorMaterialRegistry.STORMTROOPER_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties()));

    public static final RegistryEntry<Item> STORMTROOPER_CHESTPLATE = ITEMS.register("stormtrooper_chestplate",
            () -> new ArmorItem(ArmorMaterialRegistry.STORMTROOPER_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties()));
    public static final RegistryEntry<Item> STORMTROOPER_LEGGINGS = ITEMS.register("stormtrooper_leggings",
            () -> new ArmorItem(ArmorMaterialRegistry.STORMTROOPER_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties()));
    public static final RegistryEntry<Item> STORMTROOPER_BOOTS = ITEMS.register("stormtrooper_boots",
            () -> new ArmorItem(ArmorMaterialRegistry.STORMTROOPER_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties()));


    /** Beskar*/
    public static final RegistryEntry<ArmorItem> BESKAR_HELMET = ITEMS.register("beskar_armor_helmet",
            () -> new ArmorItem(ArmorMaterialRegistry.BESKAR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties()));

    public static final RegistryEntry<Item> BESKAR_CHESTPLATE = ITEMS.register("beskar_armor_chestplate",
            () -> new ArmorItem(ArmorMaterialRegistry.BESKAR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties()));
    public static final RegistryEntry<Item> BESKAR_LEGGINGS = ITEMS.register("beskar_armor_leggings",
            () -> new ArmorItem(ArmorMaterialRegistry.BESKAR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties()));
    public static final RegistryEntry<Item> BESKAR_BOOTS = ITEMS.register("beskar_armor_boots",
            () -> new ArmorItem(ArmorMaterialRegistry.BESKAR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties()));

    /** Blaster */
    //Blasters
    public static final RegistryEntry<Item> BLASTER = ITEMS.register("blaster", () -> new Blaster(new Item.Properties().durability(2000)));
    public static final RegistryEntry<Item> BLASTER_ZOOM = ITEMS.register("blaster_zoom", () -> new Blaster(new Item.Properties().durability(2000), true));

    // Blaster Upgrade
    public static final RegistryEntry<Item> BLASTER_ZOOM_UPGRADE = ITEMS.register("blaster_zoom_upgrade", () -> new BlasterUpgrade(new Item.Properties().stacksTo(16), true, 0));

    //public static final RegistryEntry<Item> BLASTER_UPGRADER = ITEMS.register("blaster_upgrader", () -> new BlockItem(BlocksRegistry.BLASTER_UPGRADER.get(), new Item.Properties()));

}
