package com.uraneptus.snowpig.core.data.client;

import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.core.registry.SPItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

import static com.uraneptus.snowpig.core.data.SPDatagenUtil.*;

@SuppressWarnings({"unused", "SameParameterValue"})
public class SPItemModelProvider extends ItemModelProvider {

    public SPItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SnowPigMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(SPItems.FROZEN_PORKCHOP);
        basicHandheldItem(SPItems.FROZEN_HAM);
        basicItem(SPItems.MUSIC_DISC_FROSTY_SNIG);
        basicSpawnEggItem(SPItems.SNOW_PIG_EGG);
    }

    private void basicItem(Supplier<? extends Item> item) {
        basicItem(item.get());
    }

    private void basicHandheldItem(Supplier<? extends Item> item) {
        withExistingParent(name(item.get()), HANDHELD).texture(LAYER0, modItemLocation(name(item.get())));
    }

    private void basicSpawnEggItem(Supplier<? extends Item> item) {
        withExistingParent(name(item.get()), SPAWN_EGG);
    }
}
