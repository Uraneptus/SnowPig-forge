package com.uraneptus.snowpig.core.registry;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class SPBrewingRecipes {

    public static void register() {
        addRecipe(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER), SPItems.FROZEN_PORKCHOP.get().getDefaultInstance(), SPItems.ICE_BOMB.get().getDefaultInstance());
    }

    public static void addRecipe(ItemStack input, ItemStack ingredient, ItemStack output) {
        BrewingRecipeRegistry.addRecipe(Ingredient.of(input), Ingredient.of(ingredient), output);

    }

}
