package com.uraneptus.snowpig.core.registry;

import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.common.FreezingMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SPMobEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SnowPigMod.MOD_ID);

    public static final RegistryObject<MobEffect> FREEZING_EFFECT = EFFECTS.register("freezing", FreezingMobEffect::new);
}
