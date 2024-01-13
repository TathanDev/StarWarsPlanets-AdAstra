package fr.tathan.swplanets.menu;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.tathan.swplanets.Constants;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BlasterUpgraderScreen extends AbstractContainerScreen<BlasterUpgraderScreenHandler> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Constants.MODID,"textures/gui/blaster_upgrader.png");


    public BlasterUpgraderScreen(BlasterUpgraderScreenHandler p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
        this.imageWidth = 175;
        this.imageHeight = 165;
        this.titleLabelY -= 2;

        this.inventoryLabelY += 3;
    }
    @Override
    protected void init() {
        super.init();
    }
    @Override
    protected void renderBg(GuiGraphics graphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        //graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
    }


    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        this.renderTooltip(guiGraphics, mouseX, mouseY);

    }

}
