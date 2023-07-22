package com.uraneptus.snowpig.core.data.client;

import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.core.registry.SPSounds;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

import java.util.function.Supplier;

@SuppressWarnings("SameParameterValue")
public class SPSoundDefinitionsProvider extends SoundDefinitionsProvider {

    public SPSoundDefinitionsProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, SnowPigMod.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {

        //Music Discs
        addMusicDiscSound(SPSounds.MUSIC_DISC_FROSTY_SNIG, "frosty_snig");

        addbasicSound(SPSounds.SNOW_PIG_AMBIENT,
                sound(SnowPigMod.modPrefix("entity/entity.snow_pig.ambient0")),
                sound(SnowPigMod.modPrefix("entity/entity.snow_pig.ambient1")),
                sound(SnowPigMod.modPrefix("entity/entity.snow_pig.ambient2"))
        );
        addbasicSound(SPSounds.SNOW_PIG_HURT,
                sound(SnowPigMod.modPrefix("entity/entity.snow_pig.ambient0")),
                sound(SnowPigMod.modPrefix("entity/entity.snow_pig.ambient1")),
                sound(SnowPigMod.modPrefix("entity/entity.snow_pig.ambient2"))
        );
        addbasicSound(SPSounds.SNOW_PIG_DEATH, sound(SnowPigMod.modPrefix("entity/entity.snow_pig.death")));
        addSoundWithSubtitle(SPSounds.SNOW_PIG_STEP, "block.generic.footsteps",
                sound(SnowPigMod.modPrefix("entity/entity.snow_pig.step0")),
                sound(SnowPigMod.modPrefix("entity/entity.snow_pig.step1")),
                sound(SnowPigMod.modPrefix("entity/entity.snow_pig.step2")),
                sound(SnowPigMod.modPrefix("entity/entity.snow_pig.step3")),
                sound(SnowPigMod.modPrefix("entity/entity.snow_pig.step4"))
        );

    }

    private void addbasicSound(Supplier<SoundEvent> soundEvent, SoundDefinition.Sound... sounds) {
        this.add(soundEvent.get(), SoundDefinition.definition().subtitle("subtitles." + soundEvent.get().getLocation().getPath()).with(sounds));
    }

    private void addSoundWithSubtitle(Supplier<SoundEvent> soundEvent, String subtitle, SoundDefinition.Sound... sounds) {
        this.add(soundEvent.get(), SoundDefinition.definition().subtitle("subtitles." + subtitle).with(sounds));
    }

    private void addMusicDiscSound(Supplier<SoundEvent> soundEvent, String name) {
        this.add(soundEvent.get(), SoundDefinition.definition().with(sound(SnowPigMod.modPrefix("records/" + name)).stream()));
    }
}
