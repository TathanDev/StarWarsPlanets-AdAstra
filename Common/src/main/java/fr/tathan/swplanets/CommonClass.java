package fr.tathan.swplanets;

import fr.tathan.swplanets.registry.*;

public class CommonClass {

    public static void init() {

        ItemsRegistry.init();
        BlocksRegistry.init();
        SoundsRegistry.init();
        TagsRegistry.init();
        ArmorMaterialRegistry.init();
        Constants.LOG.info("Hello There !");
    }

}