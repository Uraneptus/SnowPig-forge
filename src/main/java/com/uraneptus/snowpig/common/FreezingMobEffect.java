package com.uraneptus.snowpig.common;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class FreezingMobEffect extends MobEffect {

    public FreezingMobEffect() {
        super(MobEffectCategory.HARMFUL, 11007999);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        MobEffectInstance effect = pLivingEntity.getEffect(this);

        if (effect != null) {
            pLivingEntity.setTicksFrozen(pLivingEntity.getTicksFrozen() + effect.getDuration());
        }
    }
}
