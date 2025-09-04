package com.uraneptus.snowpig.client.entity.layer;

import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.common.entities.SnowPig;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class SnowPigEyesLayer<T extends SnowPig> extends EyesLayer<T, PigModel<T>> {
    private static final ResourceLocation TEXTURE = SnowPigMod.modPrefix("textures/entity/glow_eyes.png");

    public SnowPigEyesLayer(RenderLayerParent<T, PigModel<T>> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public RenderType renderType() {
        return RenderType.eyes(TEXTURE);
    }
}
