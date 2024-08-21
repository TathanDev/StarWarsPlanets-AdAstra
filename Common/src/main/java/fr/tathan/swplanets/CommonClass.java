package fr.tathan.swplanets;

import fr.tathan.swplanets.common.config.SWPlanetsConfig;
import fr.tathan.swplanets.common.registry.*;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;

import java.util.Map;

public class CommonClass {

    public static void init() {

        Constants.CONFIGURATOR.registerConfig(SWPlanetsConfig.class);

        // TabsRegistry.init();
        BlocksRegistry.BLOCKS.init();
        TagsRegistry.init();
        ArmorMaterialRegistry.init();
        ItemsRegistry.ITEMS.init();
        SoundsRegistry.SOUNDS.init();
        EntityRegistry.ENTITY_TYPES.init();
        BlockEntitiesRegistry.BLOCK_ENTITY_TYPES.init();
        MenusRegistry.MENUS.init();
        TabsRegistry.init();
        LevelRegistry.init();
        TabsRegistry.CREATIVE_MODE_TABS.init();
        Constants.LOG.info("Hello There !");
        FeatureRegistry.FEATURES.init();

    }

    public static void postInit() {
        CauldronInteraction.WATER.put(ItemsRegistry.STORMTROOPER_BOOTS.get(), CauldronInteraction.DYED_ITEM);
        CauldronInteraction.WATER.put(ItemsRegistry.STORMTROOPER_CHESTPLATE.get(), CauldronInteraction.DYED_ITEM);
        CauldronInteraction.WATER.put(ItemsRegistry.STORMTROOPER_LEGGINGS.get(), CauldronInteraction.DYED_ITEM);
        CauldronInteraction.WATER.put(ItemsRegistry.STORMTROOPER_MASK.get(), CauldronInteraction.DYED_ITEM);
        EntityRegistry.registerSpawnPlacements();
    }
}