package com.uraneptus.snowpig.core.registry;

import com.uraneptus.snowpig.SnowPig;
import com.uraneptus.snowpig.common.items.SnowPigLootItem;
import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SPLootPoolEntryTypes {
    public static final DeferredRegister<LootPoolEntryType> LOOT_ENTRY = DeferredRegister.create(Registry.LOOT_ENTRY_REGISTRY, SnowPig.MOD_ID);

    public static final RegistryObject<LootPoolEntryType> SNOWPIG_ITEM = LOOT_ENTRY.register("item", () -> new LootPoolEntryType(new SnowPigLootItem.Serializer()));


}
