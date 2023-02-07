package fr.tathan.swplanets;

import fr.tathan.swplanets.registry.ItemsRegistry;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MODID)
public class SWPlanets {
    
    public SWPlanets() {

        Constants.LOG.info("Star Wars Planets 🚀!");
        CommonClass.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(SWPlanets::onRegisterCreativeTabs);

    }

    public static void onRegisterCreativeTabs(CreativeModeTabEvent.Register event) {
        ItemsRegistry.onRegisterCreativeTabs((loc, item, items) -> event.registerCreativeModeTab(loc, b -> b
                .title(Component.translatable("itemGroup." + loc.getNamespace() + "." + loc.getPath()))
                .icon(() -> item.get().getDefaultInstance())
                .displayItems((featureFlagSet, output, bl) -> items.forEach(output::accept))
                .build()));
    }
}