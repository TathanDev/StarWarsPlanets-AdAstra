package fr.tathan.swplanets.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.entity.LaserEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class LaserRenderer extends EntityRenderer<LaserEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/laser.png");
    private final LaserModel model;

    public LaserRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new LaserModel(context.bakeLayer(LaserModel.LAYER_LOCATION));
    }

    public @NotNull ResourceLocation getTextureLocation(LaserEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(LaserEntity entity, float $$1, float $$2, PoseStack poseStack, MultiBufferSource $$4, int $$5) {
        poseStack.pushPose();
        VertexConsumer vertexConsumer = $$4.getBuffer(this.model.renderType(this.getTextureLocation(entity)));
        this.model.renderToBuffer(poseStack, vertexConsumer, $$5, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(entity, $$1, $$2, poseStack, $$4, $$5);
    }

}
