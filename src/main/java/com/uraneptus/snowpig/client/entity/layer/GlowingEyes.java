package com.uraneptus.snowpig.client.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.snowpig.SnowPig;
import com.uraneptus.snowpig.common.entities.SnowPigEntity;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class GlowingEyes extends RenderLayer<SnowPigEntity, PigModel<SnowPigEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(SnowPig.MOD_ID, "textures/entity/glow_eyes.png");

    public GlowingEyes(RenderLayerParent<SnowPigEntity, PigModel<SnowPigEntity>> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, SnowPigEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer iVertexBuilder = bufferIn.getBuffer(RenderType.eyes(TEXTURE));
        this.getParentModel().renderToBuffer(matrixStackIn, iVertexBuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
    }
}
