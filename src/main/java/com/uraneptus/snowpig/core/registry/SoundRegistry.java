package com.uraneptus.snowpig.core.registry;

import com.uraneptus.snowpig.SnowPig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundRegistry {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SnowPig.MOD_ID);

    public static final RegistryObject<SoundEvent> SNOW_PIG_AMBIENT = SOUNDS.register("entity.snow_pig.ambient",
            () -> new SoundEvent(new ResourceLocation(SnowPig.MOD_ID, "entity.snow_pig.ambient")));

    public static final RegistryObject<SoundEvent> SNOW_PIG_HURT = SOUNDS.register("entity.snow_pig.hurt",
            () -> new SoundEvent(new ResourceLocation(SnowPig.MOD_ID, "entity.snow_pig.hurt")));

    public static final RegistryObject<SoundEvent> SNOW_PIG_DEATH = SOUNDS.register("entity.snow_pig.death",
            () -> new SoundEvent(new ResourceLocation(SnowPig.MOD_ID,"entity.snow_pig.death")));

    public static final RegistryObject<SoundEvent> SNOW_PIG_STEP = SOUNDS.register("entity.snow_pig.step",
            () -> new SoundEvent(new ResourceLocation(SnowPig.MOD_ID, "entity.snow_pig.step")));

    public static final RegistryObject<SoundEvent> MUSIC_DISC_FROSTY_SNIG = SOUNDS.register("music_disc.frosty_snig",
            () -> new SoundEvent(new ResourceLocation(SnowPig.MOD_ID, "music_disc.frosty_snig")));
}
