package com.uraneptus.snowpig.core.data.server.tags;

import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.core.tags.SPBiomeTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class SPBiomeTagsProvider extends BiomeTagsProvider {
    public SPBiomeTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper fileHelper) {
        super(generator, SnowPigMod.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags() {
        tag(SPBiomeTags.SNOWPIG_SPAWN_IN).addTag(Tags.Biomes.IS_SNOWY);
    }
}
