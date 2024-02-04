package fr.tathan.swplanets.client.screens;

import earth.terrarium.adastra.client.screens.base.MachineScreen;
import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.blocks.entities.BlasterUpgraderEntity;
import fr.tathan.swplanets.common.menu.BlasterUpgraderMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class BlasterUpgraderScreen extends MachineScreen<BlasterUpgraderMenu, BlasterUpgraderEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MODID, "textures/gui/blaster_upgrader.png");
    public static final Rect2i CLICK_AREA = new Rect2i(93, 52, 26, 25);
    public BlasterUpgraderScreen(BlasterUpgraderMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component, TEXTURE, STEEL_SLOT, 177, 224);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        super.renderBg(graphics, partialTick, mouseX, mouseY);
        if (this.sideConfigWidget.isActive()) {
            graphics.fill(leftPos + 26, topPos + 46, leftPos + 92, topPos + 46, 0xFF00FF00);
            graphics.fill(leftPos + 92, topPos + 46, leftPos + 92, topPos + 63, 0xFF00FF00);
            graphics.fill(leftPos + 92, topPos + 63, leftPos + 26, topPos + 63, 0xFF00FF00);
            graphics.fill(leftPos + 26, topPos + 63, leftPos + 26, topPos + 46, 0xFF00FF00);
        }
    }

}
