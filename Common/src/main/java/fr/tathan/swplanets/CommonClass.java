package fr.tathan.swplanets;

import fr.tathan.swplanets.common.registry.*;

public class CommonClass {

    public static void init() {

       // TabsRegistry.init();
        ItemsRegistry.init();
        BlocksRegistry.init();
        SoundsRegistry.init();
        TagsRegistry.init();
        ArmorMaterialRegistry.init();
        TabsRegistry.init();
        EntityRegistry.init();
        Constants.LOG.info("Hello There !");
    }

}