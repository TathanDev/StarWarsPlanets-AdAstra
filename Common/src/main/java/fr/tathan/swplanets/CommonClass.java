package fr.tathan.swplanets;

import fr.tathan.swplanets.platform.Services;
import fr.tathan.swplanets.registry.BlocksRegistry;
import fr.tathan.swplanets.registry.ItemsRegistry;
import fr.tathan.swplanets.registry.SoundsRegistry;
import fr.tathan.swplanets.registry.TagsRegistry;
import fr.tathan.swplanets.world.features.ModConfiguredFeatures;
import fr.tathan.swplanets.world.features.ModPlacedFeatures;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class CommonClass {

    // This method serves as an initialization hook for the mod. The vanilla
    // game has no mechanism to load tooltip listeners so this must be
    // invoked from a mod loader specific project like Forge or Fabric.
    public static void init() {


        ItemsRegistry.init();
        BlocksRegistry.init();
        SoundsRegistry.init();
        ModPlacedFeatures.init();
        ModConfiguredFeatures.init();
        TagsRegistry.init();


        Constants.LOG.info("Hello There !");
    }


}