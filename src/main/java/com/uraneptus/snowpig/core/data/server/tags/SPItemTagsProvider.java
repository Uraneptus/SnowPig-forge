package com.uraneptus.snowpig.core.data.server.tags;

import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.core.tags.SPItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class SPItemTagsProvider extends ItemTagsProvider {

    public SPItemTagsProvider(DataGenerator generator, BlockTagsProvider provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, provider, SnowPigMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        //Mod Tags
        tag(SPItemTags.KNIVES);
    }
}
