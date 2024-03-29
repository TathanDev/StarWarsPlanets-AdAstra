package fr.tathan.swplanets.common.registry;


import com.teamresourceful.resourcefullib.common.item.tabs.ResourcefulCreativeTab;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import fr.tathan.swplanets.Constants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

public class TabsRegistry {

    public static final ResourcefulRegistry<CreativeModeTab> CREATIVE_MODE_TABS = ResourcefulRegistries.create(BuiltInRegistries.CREATIVE_MODE_TAB, Constants.MODID);


    public static final Supplier<CreativeModeTab> TAB = new ResourcefulCreativeTab(new ResourceLocation(Constants.MODID, "main"))
        .setItemIcon(ItemsRegistry.BLUE_LIGHT_SABER)
        .addRegistry(ItemsRegistry.TAB_ITEMS)
        .build();
    public static void init() {}

}
