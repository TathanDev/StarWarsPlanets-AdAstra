package fr.tathan.swplanets;


import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import fr.tathan.swplanets.common.registry.ItemsRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(Constants.MODID)
public class SWPlanets {

    public SWPlanets(IEventBus bus) {

        Constants.LOG.info("Star Wars Planets 🚀!");
        CommonClass.init();
        bus.addListener((BuildCreativeModeTabContentsEvent event) -> {
            if (event.getTab() == BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.TOOLS_AND_UTILITIES)) ItemsRegistry.ITEMS.stream().map(RegistryEntry::get).forEach(event::accept);
        });
    }
}