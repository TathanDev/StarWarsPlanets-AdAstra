package fr.tathan.swplanets.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.math.Axis;
import earth.terrarium.adastra.client.screens.PlanetsScreen;
import earth.terrarium.adastra.client.utils.DimensionRenderingUtils;
import earth.terrarium.adastra.common.constants.PlanetConstants;
import fr.tathan.swplanets.common.util.PlanetRenderUtil;
import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(PlanetsScreen.class)
public abstract class PlanetRendererScreenMixin {

    @Shadow
    private ResourceLocation selectedSolarSystem;

    @Inject(
            method = "renderBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/vertex/Tesselator;end()V",
                    shift = At.Shift.BEFORE
            )
    )
    private void swplanets$renderOuterRimsCircles(GuiGraphics graphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci, @Local BufferBuilder bufferBuilder){
        PlanetsScreen screen = (PlanetsScreen) ((Object) this);

        if (PlanetRenderUtil.OUTER_RIMS.equals(selectedSolarSystem)) {
            screen.drawCircles(0, 5, 0xff24327b, bufferBuilder);
        } else if (PlanetConstants.SOLAR_SYSTEM.equals(selectedSolarSystem)) {
            screen.drawCircles(0, 4, 0xff24327b, bufferBuilder);
        } else if (PlanetConstants.PROXIMA_CENTAURI.equals(selectedSolarSystem)) {
            screen.drawCircles(1, 1, 0xff008080, bufferBuilder);
        }
    }

    @Inject(
            method = "renderBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/vertex/Tesselator;end()V",
                    shift = At.Shift.AFTER
            )
    )
    private void swplanets$renderOuterRimsPlanets(GuiGraphics graphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci, @Local BufferBuilder bufferBuilder){
        PlanetsScreen screen = (PlanetsScreen) ((Object) this);

        if (PlanetRenderUtil.OUTER_RIMS.equals(selectedSolarSystem)) {
            renderOuterRims(graphics);
        } else if (PlanetConstants.PROXIMA_CENTAURI.equals(selectedSolarSystem)) {
            screen.renderProximaCentauri(graphics);
        } else if (PlanetConstants.SOLAR_SYSTEM.equals(selectedSolarSystem)) {
            screen.renderSolarSystem(graphics);
        }
    }

    public void renderOuterRims(GuiGraphics graphics) {
        PlanetsScreen screen = (PlanetsScreen) ((Object) this);

        graphics.blit(DimensionRenderingUtils.SUN, screen.width / 2 - 8, screen.height / 2 - 8, 0, 0, 16, 16, 16, 16);
        float yRot = Util.getMillis() / 100f;
        for (int i = 1; i < 6; i++) {
            graphics.pose().pushPose();
            graphics.pose().translate(screen.width / 2f, screen.height / 2f, 0);
            graphics.pose().mulPose(Axis.ZP.rotationDegrees(yRot * (6 - i) / 2));
            graphics.pose().translate(31 * i - 10, 0, 0);
            graphics.blit(PlanetRenderUtil.STAR_WARS_PLANETS_TEXTURES.get(i - 1), 0, 0, 0, 0, 12, 12, 12, 12);
            graphics.pose().popPose();
        }
    }

}
