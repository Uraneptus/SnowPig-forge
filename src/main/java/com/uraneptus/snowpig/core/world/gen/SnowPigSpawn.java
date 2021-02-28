package com.uraneptus.snowpig.core.world.gen;


import com.uraneptus.snowpig.core.registry.EntityTypeRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class SnowPigSpawn {

    private static void biomeLoadingAddition(BiomeLoadingEvent event) {
        if(event.getCategory() == Biome.Category.ICY) {
            event.getSpawns().withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityTypeRegistry.SNOW_PIG.get(), 20, 1, 4));
        }
    }
}
