package com.uraneptus.snowpig.core.tags;

import com.uraneptus.snowpig.SnowPigMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class SPEntityTags {

    public static final TagKey<EntityType<?>> KILLER_FOR_SNOWPIG_DISC = TagKey.create(Registries.ENTITY_TYPE, SnowPigMod.modPrefix("killer_for_snowpig_disc"));
}
