package com.uraneptus.snowpig.common.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FrozenItem extends Item {
    private final int freezingTicks;

    public FrozenItem(int freezingTicks, Properties pProperties) {
        super(pProperties);
        this.freezingTicks = freezingTicks;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        pLivingEntity.setTicksFrozen(pLivingEntity.getTicksFrozen() + freezingTicks);
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }

}
