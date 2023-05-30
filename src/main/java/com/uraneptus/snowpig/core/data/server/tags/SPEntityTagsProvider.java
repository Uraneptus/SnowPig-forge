package com.uraneptus.snowpig.core.data.server.tags;

import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.core.tags.SPEntityTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class SPEntityTagsProvider extends EntityTypeTagsProvider {
    public SPEntityTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, SnowPigMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(SPEntityTags.KILLER_FOR_SNOWPIG_DISC)
                .add(EntityType.STRAY)
                .addOptional(new ResourceLocation("tinyskeletons", "baby_stray"));
    }
}
