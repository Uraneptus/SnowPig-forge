package com.uraneptus.snowpig.core.world.gen;


import com.uraneptus.snowpig.core.registry.EntityTypeRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;

public class SnowPigSpawn {

    private static void biomeLoadingAddition(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.Category.ICY) {
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityTypeRegistry.SNOW_PIG.get(), 20, 1, 4));
        }
    }

    public static void registerForge(IEventBus bus) {
        bus.addListener(EventPriority.HIGH, SnowPigSpawn::biomeLoadingAddition);
    }
}
