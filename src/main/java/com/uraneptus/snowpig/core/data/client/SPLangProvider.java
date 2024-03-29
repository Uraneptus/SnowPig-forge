package com.uraneptus.snowpig.core.data.client;

import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.core.registry.SPEntityTypes;
import com.uraneptus.snowpig.core.registry.SPItems;
import com.uraneptus.snowpig.core.registry.SPPotions;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public class SPLangProvider extends LanguageProvider {

    public SPLangProvider(PackOutput output) {
        super(output, SnowPigMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addItem(SPItems.FROZEN_PORKCHOP, "Frozen Porkchop");
        addItem(SPItems.FROZEN_HAM, "Frozen Ham");
        addItem(SPItems.SNOW_PIG_EGG, "Snow Pig Spawn Egg");
        addMusicDisc(SPItems.MUSIC_DISC_FROSTY_SNIG, "Luz - Frosty Snig");

        addPotionsForEffect(SPPotions.FREEZING_POTION, "Freezing");

        addEntityType(SPEntityTypes.SNOW_PIG, "Snow Pig");

        addSubtitles("entity.snow_pig.ambient", "Snow Pig oinks");
        addSubtitles("entity.snow_pig.hurt", "Snow Pig hurts");
        addSubtitles("entity.snow_pig.death", "Snow Pig dies");
    }

    public void addSubtitles(String path, String name) {
        add("subtitles." + path, name);
    }

    public void addMusicDisc(Supplier<? extends Item> item, String description) {
        String disc = item.get().getDescriptionId();
        add(disc, "Music Disc");
        add(disc + ".desc", description);
    }

    public void addPotionsForEffect(Supplier<? extends Potion> potionEffect, String name) {
        add(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), potionEffect.get()), "Potion of " + name);
        add(PotionUtils.setPotion(Items.SPLASH_POTION.getDefaultInstance(), potionEffect.get()), "Splash Potion of " + name);
        add(PotionUtils.setPotion(Items.LINGERING_POTION.getDefaultInstance(), potionEffect.get()), "Lingering Potion of " + name);
        add(PotionUtils.setPotion(Items.TIPPED_ARROW.getDefaultInstance(), potionEffect.get()), "Arrow of " + name);
    }
}