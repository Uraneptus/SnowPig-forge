package com.uraneptus.snowpig.core.other;

import com.uraneptus.snowpig.SnowPig;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class SPBiomeTags {
    public static final TagKey<Biome> SNOWPIG_SPAWN_IN = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(SnowPig.MOD_ID, "snow_pig_spawn_in"));

}
