package com.uraneptus.snowpig.core.events;

import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.common.capabilities.SPEntityCap;
import com.uraneptus.snowpig.common.entities.SnowPig;
import com.uraneptus.snowpig.core.registry.SPBlocks;
import com.uraneptus.snowpig.core.registry.SPEntityTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnowPigMod.MOD_ID)
public class SPEntityEvents {

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

    @SubscribeEvent
    public static void onBabyEntitySpawn(BabyEntitySpawnEvent event) {
        Mob parentA = event.getParentA();
        Mob parentB = event.getParentB();
        Level level = parentA.getLevel();

        if (parentA instanceof SnowPig && parentB instanceof SnowPig) {
            level.setBlock(parentA.blockPosition(), SPBlocks.ARCTIC_LILY.get().defaultBlockState(), 2);
        }
    }
}