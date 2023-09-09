package fr.tathan.swplanets;

import fr.tathan.swplanets.registry.ItemsRegistry;
import fr.tathan.swplanets.registry.TabsRegistry;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MODID)
public class SWPlanets {

    public SWPlanets() {

        Constants.LOG.info("Star Wars Planets 🚀!");
        CommonClass.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(SWPlanets::onRegisterCreativeTabs);

    }
    public static void onRegisterCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if(event.getTab() == TabsRegistry.TAB.get()) {

            event.accept(ItemsRegistry.PLASTIC_PLATE);
            event.accept(ItemsRegistry.STORMTROOPER_MASK);
            event.accept(ItemsRegistry.STORMTROOPER_CHESTPLATE);
            event.accept(ItemsRegistry.STORMTROOPER_LEGGINGS);
            event.accept(ItemsRegistry.STORMTROOPER_BOOTS);
            event.accept(ItemsRegistry.LIGHT_SABER_BASE);
            event.accept(ItemsRegistry.DARK_MAUL_LIGHT_SABER);
            event.accept(ItemsRegistry.RED_LIGHT_SABER);
            event.accept(ItemsRegistry.KYLO_REN_LIGHT_SABER);
            event.accept(ItemsRegistry.BLUE_LIGHT_SABER);
            event.accept(ItemsRegistry.GREEN_LIGHT_SABER);
            event.accept(ItemsRegistry.VIOLET_LIGHT_SABER);
            event.accept(ItemsRegistry.KYBER_CRYSTAL);
            event.accept(ItemsRegistry.KYBER_CRYSTAl_ORE_SANDSTONE_ITEM);
            event.accept(ItemsRegistry.KYBER_CRYSTAl_ORE_SANDSTONE_ITEM);
            event.accept(ItemsRegistry.BESKAR_ORE_SANDSTONE_ITEM);
            event.accept(ItemsRegistry.BESKAR);

            event.accept(ItemsRegistry.BESKAR_HELMET);
            event.accept(ItemsRegistry.BESKAR_CHESTPLATE);
            event.accept(ItemsRegistry.BESKAR_LEGGINGS);
            event.accept(ItemsRegistry.BESKAR_BOOTS);

        }

    }
}