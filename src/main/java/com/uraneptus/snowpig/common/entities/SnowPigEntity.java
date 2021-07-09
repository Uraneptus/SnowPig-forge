package com.uraneptus.snowpig.common.entities;

import com.uraneptus.snowpig.SnowPig;
import com.uraneptus.snowpig.core.registry.EntityTypeRegistry;
import com.uraneptus.snowpig.core.registry.SoundRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Random;

public class SnowPigEntity extends PigEntity {

    public SnowPigEntity(EntityType<? extends SnowPigEntity> snowpig, World worldIn) {
        super(snowpig, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.15D)
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.3D)
                .add(Attributes.ARMOR, 0.5D);
    }

    public void thunderHit(ServerWorld p_241841_1_, LightningBoltEntity p_241841_2_) {

    }

    public static boolean canSpawn(EntityType<SnowPigEntity> entityType, IServerWorld world, SpawnReason reason, BlockPos pos, Random random) {
        return world.getRawBrightness(pos, 0) > 8;
    }

    public boolean removeWhenFarAway(double p_213397_1_) {
        return false;
    }

    public static final ResourceLocation FROZEN_HAM_LOOT = new ResourceLocation(SnowPig.MOD_ID, "entities/mod_integration/frozen_ham_loot.json");

    public ResourceLocation getDefaultLootTable() {
        if(ModList.get().isLoaded("farmersdelight")) {
            return FROZEN_HAM_LOOT;
        } else {
            return this.getType().getDefaultLootTable();
        }
    }

    @Override
    protected float getSoundVolume ()
    {
        return 0.3F;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.SNOW_PIG_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundRegistry.SNOW_PIG_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound () {
        return SoundRegistry.SNOW_PIG_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundRegistry.SNOW_PIG_STEP.get(), 0.10F, 1.0F);
    }

    @Override
    public PigEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return EntityTypeRegistry.SNOW_PIG.get().create(p_241840_1_);
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
