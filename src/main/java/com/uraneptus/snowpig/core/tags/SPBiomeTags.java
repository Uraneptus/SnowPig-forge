package com.uraneptus.snowpig.core.tags;

import com.uraneptus.snowpig.SnowPigMod;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class SPBiomeTags {
    public static final TagKey<Biome> SNOWPIG_SPAWN_IN = TagKey.create(Registry.BIOME_REGISTRY, SnowPigMod.modPrefix("snow_pig_spawn_in"));

}
