package com.uraneptus.snowpig.common.entities;

import com.uraneptus.snowpig.core.registry.SPEntityTypes;
import com.uraneptus.snowpig.core.registry.SPItems;
import net.minecraft.BlockUtil;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.Tags;

public class ThrownIceBomb extends ThrowableItemProjectile {

    public ThrownIceBomb(EntityType<ThrownIceBomb> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownIceBomb(Level pLevel, LivingEntity pShooter) {
        super(SPEntityTypes.ICE_BOMB.get(), pShooter, pLevel);
    }

    public ThrownIceBomb(Level pLevel, double pX, double pY, double pZ) {
        super(SPEntityTypes.ICE_BOMB.get(), pX, pY, pZ, pLevel);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            double d0 = 0.08D;

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5D) * d0, ((double)this.random.nextFloat() - 0.5D) * d0, ((double)this.random.nextFloat() - 0.5D) * d0);
            }
        }

    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        Level level = this.getLevel();
        if (!level.isClientSide) {
            if (pResult instanceof BlockHitResult blockHitResult) {
                BlockPos pPos = blockHitResult.getBlockPos();

                for(BlockPos blockpos : BlockPos.betweenClosed(pPos.offset(-2, 0, -2), pPos.offset(2, 1, 2))) {
                    BlockState state = level.getBlockState(blockpos);

                    if (level.getBlockState(blockpos.above()).isAir()) {
                        level.setBlock(blockpos.above(), Blocks.SNOW.defaultBlockState(), 2);
                    }
                    if (level.getFluidState(blockpos).is(Fluids.WATER)) {
                        level.setBlock(blockpos, Blocks.ICE.defaultBlockState(), 2);
                    }
                }
            }

            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }



    @Override
    protected Item getDefaultItem() {
        return SPItems.ICE_BOMB.get();
    }
}
