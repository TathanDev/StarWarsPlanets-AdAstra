package fr.tathan.swplanets;

import fr.tathan.swplanets.common.registry.*;

public class CommonClass {

    public static void init() {

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
        Constants.LOG.info("Hello There !");

    }

}