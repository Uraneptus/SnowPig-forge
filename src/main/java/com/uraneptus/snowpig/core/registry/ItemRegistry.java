package com.uraneptus.snowpig.core.registry;

import com.uraneptus.snowpig.SnowPig;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SnowPig.MOD_ID);

    public static final RegistryObject<Item> FROZEN_PORKCHOP = ITEMS.register("frozen_porkchop", () -> new Item(new Item.Properties().group(ItemGroup.FOOD)
            .food(new Food.Builder().hunger(3).saturation(0.6f).meat().effect(() -> new EffectInstance(Effects.SLOWNESS, 100, 2), 1.0f).build())));

}
