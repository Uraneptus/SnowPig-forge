package com.uraneptus.snowpig.core.events;

import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.common.capabilities.SPEntityCap;
import com.uraneptus.snowpig.core.registry.SPEntityTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnowPigMod.MOD_ID)
public class SPCommonEvents {

    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Pig pig) {
            if (pig.isInPowderSnow) {
                if (pig.isFullyFrozen()) {
                    SPEntityCap.getCapOptional(pig).ifPresent(cap -> {
                        if (cap.freezeTicks > 0) {
                            cap.freezeTicks--;
                        }
                        if (cap.freezeTicks == 0) {
                            pig.convertTo(SPEntityTypes.SNOW_PIG.get(), true);
                        }
                    });
                }
            }
        }
    }
}