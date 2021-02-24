package com.uraneptus.snowpig.client.entity.render;

import com.uraneptus.snowpig.SnowPig;
import com.uraneptus.snowpig.client.entity.layer.GlowingEyes;
import com.uraneptus.snowpig.common.entities.SnowPigEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.client.renderer.entity.model.PigModel;
import net.minecraft.util.ResourceLocation;

public class SnowPigRender extends MobRenderer<SnowPigEntity, PigModel<SnowPigEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(SnowPig.MOD_ID, "textures/entity/snow_pig.png");

     public SnowPigRender (EntityRendererManager rendererManager) {
        super(rendererManager, new PigModel<>(), 0.5f);
         this.addLayer(new SaddleLayer<>(this, new PigModel<>(0.5F), new ResourceLocation("textures/entity/pig/pig_saddle.png")));
         this.addLayer(new GlowingEyes(this));
    }

    @Override
    public ResourceLocation getEntityTexture(SnowPigEntity entity){
         return TEXTURE;
    }


}
