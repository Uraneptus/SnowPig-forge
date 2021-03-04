package com.uraneptus.snowpig.common.entities;

import com.uraneptus.snowpig.core.registry.EntityTypeRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class SnowPigEntity extends PigEntity {

    public SnowPigEntity(EntityType<? extends SnowPigEntity> snowpig, World worldIn) {
        super(snowpig, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute func_234215_eI_() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.15D)
                .createMutableAttribute(Attributes.MAX_HEALTH, 10.0D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.3D)
                .createMutableAttribute(Attributes.ARMOR, 0.5D);
    }

    public void func_241841_a(ServerWorld p_241841_1_, LightningBoltEntity p_241841_2_) {
        return;
    }

    public static boolean canSpawn(EntityType<SnowPigEntity> entityType, IServerWorld world, SpawnReason reason, BlockPos pos, Random random) {
        return world.getLightSubtracted(pos, 0) > 8;
    }


    @Override
    public PigEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return EntityTypeRegistry.SNOW_PIG.get().create(p_241840_1_);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
