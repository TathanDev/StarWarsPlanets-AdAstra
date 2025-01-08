package fr.tathan.swplanets.forge;


import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import fr.tathan.swplanets.CommonClass;
import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.entities.BanthaEntity;
import fr.tathan.swplanets.common.entities.JawaEntity;
import fr.tathan.swplanets.common.registry.EntityRegistry;
import fr.tathan.swplanets.common.registry.ItemsRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(Constants.MODID)
public class ForgeSWPlanets {

    public ForgeSWPlanets() {

        Constants.LOG.info("Hello There ✨!");
        CommonClass.init();
        MinecraftForge.EVENT_BUS.addListener((BuildCreativeModeTabContentsEvent event) -> {
            if (event.getTab() == BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.TOOLS_AND_UTILITIES)) ItemsRegistry.ITEMS.stream().map(RegistryEntry::get).forEach(event::accept);
        });
        MinecraftForge.EVENT_BUS.addListener(ForgeSWPlanets::commonSetup);
        MinecraftForge.EVENT_BUS.addListener(ForgeSWPlanets::onAttributes);

    }

    public static void commonSetup(FMLCommonSetupEvent event) {
        CommonClass.postInit();
    }

    public static void onAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.JAWA.get(), JawaEntity.addAttributes().build());
        event.put(EntityRegistry.BANTHA.get(), BanthaEntity.addAttributes().build());
        Constants.LOG.info("entity attributes registered!");
    }


}