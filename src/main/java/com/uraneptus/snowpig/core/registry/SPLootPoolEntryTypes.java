package com.uraneptus.snowpig.core.registry;

import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.common.items.SnowPigLootItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SPLootPoolEntryTypes {
    public static final DeferredRegister<LootPoolEntryType> LOOT_ENTRY = DeferredRegister.create(Registries.LOOT_POOL_ENTRY_TYPE, SnowPigMod.MOD_ID);

    public static final RegistryObject<LootPoolEntryType> SNOWPIG_ITEM = LOOT_ENTRY.register("item", () -> new LootPoolEntryType(new SnowPigLootItem.Serializer()));


}
