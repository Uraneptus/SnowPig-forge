package com.uraneptus.snowpig.core.data.server.tags;

import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.core.registry.SPBlocks;
import com.uraneptus.snowpig.core.tags.SPBlockTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class SPBlockTagsProvider extends BlockTagsProvider {

    public SPBlockTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, SnowPigMod.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags() {
        tag(BlockTags.FLOWER_POTS).add(SPBlocks.POTTED_ARCTIC_LILY.get());
        tag(SPBlockTags.ARCTIC_LILY_PLACE_ON).addTag(BlockTags.DIRT).add(Blocks.SNOW_BLOCK);
    }
}
