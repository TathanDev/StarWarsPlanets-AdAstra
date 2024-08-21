package fr.tathan.swplanets.forge;


import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import fr.tathan.swplanets.CommonClass;
import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.registry.EntityRegistry;
import fr.tathan.swplanets.common.registry.ItemsRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(Constants.MODID)
public class NeoForgeSWPlanets {

    public NeoForgeSWPlanets(IEventBus bus) {
        Constants.LOG.info("Hello There ✨!");
        CommonClass.init();
        bus.addListener((BuildCreativeModeTabContentsEvent event) -> {
            if (event.getTab() == BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.TOOLS_AND_UTILITIES)) ItemsRegistry.ITEMS.stream().map(RegistryEntry::get).forEach(event::accept);
        });
        bus.addListener(NeoForgeSWPlanets::commonSetup);
        bus.addListener(NeoForgeSWPlanets::onAttributes);

    }

    public static void commonSetup(FMLCommonSetupEvent event) {
        CommonClass.postInit();
    }

    public static void onAttributes(EntityAttributeCreationEvent event) {
        EntityRegistry.registerAttributes((entityType, attribute) -> event.put(entityType.get(), attribute.get().build()));
    }


}