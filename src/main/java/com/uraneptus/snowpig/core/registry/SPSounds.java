package com.uraneptus.snowpig.core.registry;

import com.uraneptus.snowpig.SnowPigMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SPSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SnowPigMod.MOD_ID);

    public static final RegistryObject<SoundEvent> SNOW_PIG_AMBIENT = SOUNDS.register("entity.snow_pig.ambient",
            () -> SoundEvent.createVariableRangeEvent(SnowPigMod.modPrefix("entity.snow_pig.ambient")));

    public static final RegistryObject<SoundEvent> SNOW_PIG_HURT = SOUNDS.register("entity.snow_pig.hurt",
            () -> SoundEvent.createVariableRangeEvent(SnowPigMod.modPrefix("entity.snow_pig.hurt")));

    public static final RegistryObject<SoundEvent> SNOW_PIG_DEATH = SOUNDS.register("entity.snow_pig.death",
            () -> SoundEvent.createVariableRangeEvent(SnowPigMod.modPrefix("entity.snow_pig.death")));

    public static final RegistryObject<SoundEvent> SNOW_PIG_STEP = SOUNDS.register("entity.snow_pig.step",
            () -> SoundEvent.createVariableRangeEvent(SnowPigMod.modPrefix("entity.snow_pig.step")));

    public static final RegistryObject<SoundEvent> MUSIC_DISC_FROSTY_SNIG = SOUNDS.register("music_disc.frosty_snig",
            () -> SoundEvent.createVariableRangeEvent(SnowPigMod.modPrefix("music_disc.frosty_snig")));
}
