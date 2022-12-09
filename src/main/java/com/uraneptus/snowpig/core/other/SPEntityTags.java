package com.uraneptus.snowpig.core.other;

import com.uraneptus.snowpig.SnowPigMod;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class SPEntityTags {

    public static final TagKey<EntityType<?>> KILLER_FOR_SNOWPIG_DISC = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, SnowPigMod.modPrefix("killer_for_snowpig_disc"));
}
