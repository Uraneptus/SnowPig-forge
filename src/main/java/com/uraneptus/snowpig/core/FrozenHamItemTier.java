package com.uraneptus.snowpig.core;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public class FrozenHamItemTier implements IItemTier {
    private final int harvestLevel;
    private final int maxUses;
    private final float speed;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairIngredient;

    public FrozenHamItemTier(int harvestLevelIn, int maxUsesIn, float speedIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairIngredientIn) {
        this.harvestLevel = harvestLevelIn;
        this.maxUses = maxUsesIn;
        this.speed = speedIn;
        this.attackDamage = attackDamageIn;
        this.enchantability = enchantabilityIn;
        this.repairIngredient = new LazyValue<>(repairIngredientIn);
    }

    public static final FrozenHamItemTier FROZEN_HAM_ITEM_TIER = new FrozenHamItemTier(0, 0, 0.0F, 0.0F, 0, null);

    @Override
    public int getUses() {
        return maxUses;
    }
    @Override
    public float getSpeed() {
        return speed;
    }
    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }
    @Override
    public int getLevel() {
        return harvestLevel;
    }
    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }
    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}
