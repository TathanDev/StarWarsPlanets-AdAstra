package fr.tathan.swplanets.forge;


import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import fr.tathan.swplanets.CommonClass;
import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.registry.ItemsRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(Constants.MODID)
public class NeoForgeSWPlanets {

    public NeoForgeSWPlanets(IEventBus bus) {
        Constants.LOG.info("Hello There ✨!");
        CommonClass.init();
        bus.addListener((BuildCreativeModeTabContentsEvent event) -> {
            if (event.getTab() == BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.TOOLS_AND_UTILITIES)) ItemsRegistry.ITEMS.stream().map(RegistryEntry::get).forEach(event::accept);
        });
    }
}