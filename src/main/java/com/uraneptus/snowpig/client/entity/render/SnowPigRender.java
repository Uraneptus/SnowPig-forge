package com.uraneptus.snowpig.client.entity.render;

import com.uraneptus.snowpig.client.entity.layer.SnowPigEyesLayer;
import com.uraneptus.snowpig.common.entities.SnowPig;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SnowPigRender extends MobRenderer<SnowPig, PigModel<SnowPig>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(com.uraneptus.snowpig.SnowPig.MOD_ID, "textures/entity/snow_pig.png");

     public SnowPigRender (EntityRendererProvider.Context context) {
        super(context, new PigModel<>(context.bakeLayer(ModelLayers.PIG)), 0.5f);
         this.addLayer(new SaddleLayer<>(this, new PigModel<>(context.bakeLayer(ModelLayers.PIG_SADDLE)), new ResourceLocation("textures/entity/pig/pig_saddle.png")));
         this.addLayer(new SnowPigEyesLayer(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(SnowPig entity) {
         return TEXTURE;
    }


}
