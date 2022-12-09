package com.uraneptus.snowpig.core.registry;

import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.common.items.FrozenHam;
import com.uraneptus.snowpig.core.ModIntegrations;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SnowPigMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SPItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SnowPigMod.MOD_ID);

    public static final RegistryObject<Item> FROZEN_PORKCHOP = ITEMS.register("frozen_porkchop",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
                    .food(new FoodProperties.Builder().nutrition(5).saturationMod(0.3f).meat()
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2), 1.0f).build())));

    public static final RegistryObject<Item> SNOW_PIG_EGG = ITEMS.register("snow_pig_spawn_egg",
            () -> new ForgeSpawnEggItem(SPEntityTypes.SNOW_PIG, 0x70CFF3, 0xE2FBFA, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> MUSIC_DISC_FROSTY_SNIG = ITEMS.register("music_disc_frosty_snig",
            () -> new RecordItem(12, SPSounds.MUSIC_DISC_FROSTY_SNIG, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE), 3720));

    public static final RegistryObject<Item> FROZEN_HAM = ITEMS.register("frozen_ham", () -> new FrozenHam(2,-1.2F, (new Item.Properties()
            .tab(!ModList.get().isLoaded("farmersdelight") ? null : !ModList.get().isLoaded("abnormals_delight") ? ModIntegrations.getFdItemGroup() : CreativeModeTab.TAB_FOOD)
            .food(new FoodProperties.Builder().nutrition(5).saturationMod(0.3F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2), 1.0F).build()))));

}