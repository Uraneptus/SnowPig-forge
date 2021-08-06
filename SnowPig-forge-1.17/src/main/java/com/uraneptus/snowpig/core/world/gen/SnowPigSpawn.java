package com.uraneptus.snowpig.core.world.gen;


import com.uraneptus.snowpig.core.registry.EntityTypeRegistry;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;

public class SnowPigSpawn {

    private static void biomeLoadingAddition(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.ICY) {
            event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityTypeRegistry.SNOW_PIG.get(), 20, 1, 4));
        }
    }

    public static void registerForge(IEventBus bus) {
        bus.addListener(EventPriority.HIGH, SnowPigSpawn::biomeLoadingAddition);
    }
}
