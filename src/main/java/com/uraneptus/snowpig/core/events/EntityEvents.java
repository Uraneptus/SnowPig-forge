package com.uraneptus.snowpig.core.events;

import com.uraneptus.snowpig.SnowPig;
import com.uraneptus.snowpig.common.entities.SnowPigEntity;
import com.uraneptus.snowpig.core.registry.EntityTypeRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnowPig.MOD_ID)
public class EntityEvents {

    /*@SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingUpdateEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Pig) {
            if (entity.isInPowderSnow) { //TODO: Add timer, using capabilities probably
                ((Pig) entity).convertTo(EntityTypeRegistry.SNOW_PIG.get(), true);
            }
        }

    }*/
    
}
