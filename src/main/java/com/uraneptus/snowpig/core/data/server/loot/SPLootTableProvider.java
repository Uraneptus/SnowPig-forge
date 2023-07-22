package com.uraneptus.snowpig.core.data.server.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Collections;
import java.util.List;

public class SPLootTableProvider extends LootTableProvider {

    public SPLootTableProvider(PackOutput output) {
        super(output, Collections.emptySet(), List.of(
                new LootTableProvider.SubProviderEntry(SPEntityLoot::new, LootContextParamSets.ENTITY)
        ));
    }

}
