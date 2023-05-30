package com.uraneptus.snowpig.core.data.server.tags;

import com.uraneptus.snowpig.SnowPigMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class SPBlockTagsProvider extends BlockTagsProvider {

    public SPBlockTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, SnowPigMod.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags() {

    }
}
