package com.uraneptus.snowpig.client.entity.render;

import com.uraneptus.snowpig.SnowPig;
import com.uraneptus.snowpig.client.entity.layer.GlowingEyes;
import com.uraneptus.snowpig.common.entities.SnowPigEntity;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;

public class SnowPigRender extends MobRenderer<SnowPigEntity, PigModel<SnowPigEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(SnowPig.MOD_ID, "textures/entity/snow_pig.png");

     public SnowPigRender (EntityRendererProvider.Context p_174340_) {
        super(p_174340_, new PigModel(p_174340_.bakeLayer(ModelLayers.PIG)), 0.5f);
         this.addLayer(new SaddleLayer(this, new PigModel(p_174340_.bakeLayer(ModelLayers.PIG_SADDLE)), new ResourceLocation("textures/entity/pig/pig_saddle.png")));
         this.addLayer(new GlowingEyes(this));
    }

    @Override
    public ResourceLocation getTextureLocation(SnowPigEntity entity){
         return TEXTURE;
    }


}
