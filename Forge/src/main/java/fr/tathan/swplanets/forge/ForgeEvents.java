package fr.tathan.swplanets.forge;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.entities.BanthaEntity;
import fr.tathan.swplanets.common.entities.JawaEntity;
import fr.tathan.swplanets.common.registry.EntityRegistry;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeEvents {

    @SubscribeEvent
    public static void onAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.JAWA.get(), JawaEntity.addAttributes().build());
        event.put(EntityRegistry.BANTHA.get(), BanthaEntity.addAttributes().build());
        Constants.LOG.info("entity attributes registered!");
    }

}
