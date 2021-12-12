package com.uraneptus.snowpig.core.registry;

import com.uraneptus.snowpig.SnowPig;
import com.uraneptus.snowpig.common.items.FrozenHam;
import com.uraneptus.snowpig.common.items.SnowPigEgg;
import com.uraneptus.snowpig.core.ModIntegrations;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = SnowPig.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SnowPig.MOD_ID);

    public static final RegistryObject<Item> FROZEN_PORKCHOP = ITEMS.register("frozen_porkchop",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD)
                .food(new Food.Builder().nutrition(5).saturationMod(0.3f).meat()
                    .effect(() -> new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 2), 1.0f).build())));

    public static final RegistryObject<Item> SNOW_PIG_EGG = ITEMS.register("snow_pig_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypeRegistry.SNOW_PIG, 0x70CFF3, 0xE2FBFA, new Item.Properties().tab(ItemGroup.TAB_MISC)));

    public static final RegistryObject<Item> FROZEN_HAM = ITEMS.register("frozen_ham", () -> new FrozenHam(2,-1.2F, (new Item.Properties()
            .tab(ModList.get().isLoaded("farmersdelight") ? ModIntegrations.getFdItemGroup() : ItemGroup.TAB_FOOD)
            .food(new Food.Builder().nutrition(5).saturationMod(0.3F).effect(() -> new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 2), 1.0F).build()))));

    public static final RegistryObject<Item> MUSIC_DISC_FROSTY_SNIG = ITEMS.register("music_disc_frosty_snig",
            () -> new MusicDiscItem(12, SoundRegistry.MUSIC_DISC_FROSTY_SNIG, new Item.Properties().stacksTo(1).tab(ItemGroup.TAB_MISC).rarity(Rarity.RARE)));

}