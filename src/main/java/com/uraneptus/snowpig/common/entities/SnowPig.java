package com.uraneptus.snowpig.common.entities;

import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.core.ModIntegrations;
import com.uraneptus.snowpig.core.registry.SPEntityTypes;
import com.uraneptus.snowpig.core.registry.SPSounds;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class SnowPig extends Pig {
    public static final ResourceLocation FROZEN_HAM_LOOT = SnowPigMod.modPrefix("entities/mod_integration/frozen_ham_loot");

    public SnowPig(EntityType<? extends SnowPig> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.15D)
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.3D)
                .add(Attributes.ARMOR, 0.5D);
    }

    @NotNull
    public ResourceLocation getDefaultLootTable() {
        if (ModIntegrations.IS_FD_LOADED) {
            return FROZEN_HAM_LOOT;
        }
        return super.getDefaultLootTable();
    }

    @Override
    public void thunderHit(ServerLevel pLevel, LightningBolt pLightning) {

    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SPSounds.SNOW_PIG_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SPSounds.SNOW_PIG_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound () {
        return SPSounds.SNOW_PIG_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SPSounds.SNOW_PIG_STEP.get(), 0.15F, 1.0F);
    }
    public boolean canFreeze() {
        return false;
    }

    public SnowPig getBreedOffspring(ServerLevel level, AgeableMob ageableMob) {
        return SPEntityTypes.SNOW_PIG.get().create(level);
    }
}