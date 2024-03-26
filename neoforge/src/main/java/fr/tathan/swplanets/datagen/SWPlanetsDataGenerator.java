package fr.tathan.swplanets.datagen;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.datagen.provider.PlanetsRendererProvider;
import fr.tathan.swplanets.datagen.provider.base.StructureUpdater;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;


@Mod.EventBusSubscriber(modid = Constants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SWPlanetsDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();


        generator.addProvider(event.includeClient(), new PlanetsRendererProvider(packOutput));
        generator.addProvider(true, new StructureUpdater("structures", Constants.MODID, existingFileHelper, packOutput));

    }
}
