package fr.tathan.swplanets;

import fr.tathan.swplanets.registry.BlocksRegistry;
import fr.tathan.swplanets.registry.ItemsRegistry;
import fr.tathan.swplanets.registry.SoundsRegistry;
import fr.tathan.swplanets.registry.TagsRegistry;

public class CommonClass {

    public static void init() {

        ItemsRegistry.init();
        BlocksRegistry.init();
        SoundsRegistry.init();
        TagsRegistry.init();
        Constants.LOG.info("Hello There !");
    }

}