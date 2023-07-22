package com.uraneptus.snowpig.core.tags;

import com.uraneptus.snowpig.core.ModIntegrations;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class SPItemTags {

    public static final TagKey<Item> KNIVES = TagKey.create(Registries.ITEM, new ResourceLocation(ModIntegrations.FD_MODID, "tools/knives"));

}
