package com.uraneptus.snowpig.core.registry;

import com.uraneptus.snowpig.SnowPigMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SnowPigMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SPPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, SnowPigMod.MOD_ID);

    public static final RegistryObject<Potion> FREEZING_POTION = POTIONS.register("freezing_potion", () -> new Potion(new MobEffectInstance(SPMobEffects.FREEZING_EFFECT.get(), 400)));
}
