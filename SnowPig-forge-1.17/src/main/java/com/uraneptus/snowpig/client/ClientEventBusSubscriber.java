package com.uraneptus.snowpig.client;

import com.uraneptus.snowpig.SnowPig;
import com.uraneptus.snowpig.client.entity.render.SnowPigRender;
import com.uraneptus.snowpig.core.registry.EntityTypeRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnowPig.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityTypeRegistry.SNOW_PIG.get(), SnowPigRender::new);
    }

}
