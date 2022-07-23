package com.uraneptus.snowpig.core.world.gen;


import com.uraneptus.snowpig.SnowPig;
import com.uraneptus.snowpig.core.registry.EntityTypeRegistry;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnowPig.MOD_ID)
public class SnowPigSpawn {

    @SubscribeEvent
    public static void onBiomeLoad(final BiomeLoadingEvent event) {
        if(event.getName() == null)
            return;
        MobSpawnSettingsBuilder spawns = event.getSpawns();

        if(event.getCategory().equals(Biome.BiomeCategory.ICY)) {
            spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityTypeRegistry.SNOW_PIG.get(), 20, 1, 4));
        }
    }
}
