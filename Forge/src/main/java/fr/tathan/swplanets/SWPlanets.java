package fr.tathan.swplanets;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MODID)
public class SWPlanets {
    
    public SWPlanets() {
    
        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();
    
        MinecraftForge.EVENT_BUS.addListener(this::onItemTooltip);
        
    }
    
    private void onItemTooltip(ItemTooltipEvent event) {
        
        CommonClass.onItemTooltip(event.getItemStack(), event.getFlags(), event.getToolTip());
    }
}