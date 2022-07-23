package com.uraneptus.snowpig.core.other;

import com.uraneptus.snowpig.SnowPig;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class EntityTags {

    public static final TagKey<EntityType<?>> KILLER_FOR_SNOWPIG_DISC = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(SnowPig.MOD_ID, "killer_for_snowpig_disc"));
}
