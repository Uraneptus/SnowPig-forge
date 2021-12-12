package com.uraneptus.snowpig.core.registry;

import com.uraneptus.snowpig.SnowPig;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.food.FoodProperties;

@Mod.EventBusSubscriber(modid = SnowPig.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SnowPig.MOD_ID);

    public static final RegistryObject<Item> FROZEN_PORKCHOP = ITEMS.register("frozen_porkchop",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
                    .food(new FoodProperties.Builder().nutrition(5).saturationMod(0.3f).meat()
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2), 1.0f).build())));

    public static final RegistryObject<Item> SNOW_PIG_EGG = ITEMS.register("snow_pig_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypeRegistry.SNOW_PIG, 0x70CFF3, 0xE2FBFA, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> MUSIC_DISC_FROSTY_SNIG = ITEMS.register("music_disc_frosty_snig",
            () -> new RecordItem(12, SoundRegistry.MUSIC_DISC_FROSTY_SNIG, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE)));

    /*public static final RegistryObject<Item> FROZEN_HAM = ITEMS.register("frozen_ham", () -> new FrozenHam(2,-1.2F, (new Item.Properties()
            .tab(ModList.get().isLoaded("farmersdelight") ? ModIntegrations.getFdItemGroup() : CreativeModeTab.TAB_FOOD)
            .food(new FoodProperties.Builder().nutrition(5).saturationMod(0.3F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2), 1.0F).build()))));*/

}