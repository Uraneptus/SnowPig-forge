package com.uraneptus.snowpig.core.world.gen;

import com.uraneptus.snowpig.common.entities.SnowPigEntity;
import com.uraneptus.snowpig.core.registry.EntityTypeRegistry;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class SnowPigSpawnPlacement {

    private static void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            EntitySpawnPlacementRegistry.register(EntityTypeRegistry.SNOW_PIG.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SnowPigEntity::canSpawn);
        });
    }

    public static void registerMod(IEventBus bus) {
        bus.addListener(SnowPigSpawnPlacement::setup);
    }
}
