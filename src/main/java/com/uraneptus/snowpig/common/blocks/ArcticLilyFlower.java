package com.uraneptus.snowpig.common.blocks;

import com.uraneptus.snowpig.core.tags.SPBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ArcticLilyFlower extends FlowerBlock {

    public ArcticLilyFlower(Properties pProperties) {
        super(() -> MobEffects.MOVEMENT_SLOWDOWN, 9, pProperties);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.below()).is(SPBlockTags.ARCTIC_LILY_PLACE_ON);
    }

}
