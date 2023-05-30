package com.uraneptus.snowpig.core.data.server.datapack_registries;

import com.mojang.serialization.JsonOps;
import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.core.data.SPDatagenUtil;
import com.uraneptus.snowpig.core.registry.SPEntityTypes;
import com.uraneptus.snowpig.core.tags.SPBiomeTags;
import net.minecraft.core.HolderSet;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class SPBiomeModifiersProvider {
    private static final Map<ResourceLocation, BiomeModifier> ENTRIES = new HashMap<>();

    public static JsonCodecProvider<BiomeModifier> createBiomeModifiers(DataGenerator generator, ExistingFileHelper fileHelper) {
        addSingleSpawnModifier("snow_pig", SPBiomeTags.SNOWPIG_SPAWN_IN, SPEntityTypes.SNOW_PIG.get(), 20, 1, 4);

        return JsonCodecProvider.forDatapackRegistry(generator, fileHelper, SnowPigMod.MOD_ID,  RegistryOps.create(JsonOps.INSTANCE, SPDatagenUtil.REGISTRY_ACCESS), ForgeRegistries.Keys.BIOME_MODIFIERS, ENTRIES);
    }

    private static void addSingleSpawnModifier(String name, TagKey<Biome> biomeTag, EntityType<?> entity, int weight, int minCount, int maxCount) {
        addEntry(name, ForgeBiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(new HolderSet.Named<>(SPDatagenUtil.BIOME_REGISTRY, biomeTag), new MobSpawnSettings.SpawnerData(entity, weight, minCount, maxCount)));
    }

    private static void addEntry(String name, BiomeModifier modifier) {
        ENTRIES.put(SnowPigMod.modPrefix(name), modifier);
    }
}