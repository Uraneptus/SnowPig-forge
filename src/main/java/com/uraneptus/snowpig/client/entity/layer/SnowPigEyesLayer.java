package com.uraneptus.snowpig.client.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.common.entities.SnowPig;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class SnowPigEyesLayer extends RenderLayer<SnowPig, PigModel<SnowPig>> {
    private static final ResourceLocation TEXTURE = SnowPigMod.modPrefix("textures/entity/glow_eyes.png");

    public SnowPigEyesLayer(RenderLayerParent<SnowPig, PigModel<SnowPig>> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, SnowPig entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer iVertexBuilder = bufferIn.getBuffer(RenderType.eyes(TEXTURE));
        this.getParentModel().renderToBuffer(matrixStackIn, iVertexBuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
    }
}
