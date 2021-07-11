package com.uraneptus.snowpig.common.entities;

import com.uraneptus.snowpig.SnowPig;
import com.uraneptus.snowpig.core.registry.EntityTypeRegistry;
import com.uraneptus.snowpig.core.registry.SoundRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class SnowPigEntity extends AnimalEntity implements IRideable, IEquipable {
    private static final DataParameter<Boolean> DATA_SADDLE_ID = EntityDataManager.defineId(PigEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> DATA_BOOST_TIME = EntityDataManager.defineId(PigEntity.class, DataSerializers.INT);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.CARROT, Items.POTATO, Items.BEETROOT);
    private final BoostHelper steering = new BoostHelper(this.entityData, DATA_BOOST_TIME, DATA_SADDLE_ID);

    public SnowPigEntity(EntityType<? extends SnowPigEntity> snowpig, World worldIn) {
        super(snowpig, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.of(Items.CARROT_ON_A_STICK), false));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, false, FOOD_ITEMS));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.15D)
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.3D)
                .add(Attributes.ARMOR, 0.5D);
    }

    public static boolean canSpawn(EntityType<SnowPigEntity> entityType, IServerWorld world, SpawnReason reason, BlockPos pos, Random random) {
        return world.getRawBrightness(pos, 0) > 8;
    }

    /*public boolean removeWhenFarAway(double p_213397_1_) {
        return false;
    }*/

    public static final ResourceLocation FROZEN_HAM_LOOT = new ResourceLocation(SnowPig.MOD_ID, "entities/mod_integration/frozen_ham_loot");

    public ResourceLocation getDefaultLootTable() {
        if(ModList.get().isLoaded("farmersdelight")) {
            return FROZEN_HAM_LOOT;
        } else {
            return this.getType().getDefaultLootTable();
        }
    }

    @Nullable
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    public boolean canBeControlledByRider() {
        Entity entity = this.getControllingPassenger();
        if (!(entity instanceof PlayerEntity)) {
            return false;
        } else {
            PlayerEntity playerentity = (PlayerEntity)entity;
            return playerentity.getMainHandItem().getItem() == Items.CARROT_ON_A_STICK || playerentity.getOffhandItem().getItem() == Items.CARROT_ON_A_STICK;
        }
    }

    public void onSyncedDataUpdated(DataParameter<?> p_184206_1_) {
        if (DATA_BOOST_TIME.equals(p_184206_1_) && this.level.isClientSide) {
            this.steering.onSynced();
        }

        super.onSyncedDataUpdated(p_184206_1_);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SADDLE_ID, false);
        this.entityData.define(DATA_BOOST_TIME, 0);
    }

    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        this.steering.addAdditionalSaveData(p_213281_1_);
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.steering.readAdditionalSaveData(p_70037_1_);
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

    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        boolean flag = this.isFood(p_230254_1_.getItemInHand(p_230254_2_));
        if (!flag && this.isSaddled() && !this.isVehicle() && !p_230254_1_.isSecondaryUseActive()) {
            if (!this.level.isClientSide) {
                p_230254_1_.startRiding(this);
            }

            return ActionResultType.sidedSuccess(this.level.isClientSide);
        } else {
            ActionResultType actionresulttype = super.mobInteract(p_230254_1_, p_230254_2_);
            if (!actionresulttype.consumesAction()) {
                ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
                return itemstack.getItem() == Items.SADDLE ? itemstack.interactLivingEntity(p_230254_1_, this, p_230254_2_) : ActionResultType.PASS;
            } else {
                return actionresulttype;
            }
        }
    }

    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby();
    }

    protected void dropEquipment() {
        super.dropEquipment();
        if (this.isSaddled()) {
            this.spawnAtLocation(Items.SADDLE);
        }

    }

    public boolean isSaddled() {
        return this.steering.hasSaddle();
    }

    public void equipSaddle(@Nullable SoundCategory p_230266_1_) {
        this.steering.setSaddle(true);
        if (p_230266_1_ != null) {
            this.level.playSound((PlayerEntity)null, this, SoundEvents.PIG_SADDLE, p_230266_1_, 0.5F, 1.0F);
        }

    }

    public Vector3d getDismountLocationForPassenger(LivingEntity p_230268_1_) {
        Direction direction = this.getMotionDirection();
        if (direction.getAxis() == Direction.Axis.Y) {
            return super.getDismountLocationForPassenger(p_230268_1_);
        } else {
            int[][] aint = TransportationHelper.offsetsForDirection(direction);
            BlockPos blockpos = this.blockPosition();
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

            for(Pose pose : p_230268_1_.getDismountPoses()) {
                AxisAlignedBB axisalignedbb = p_230268_1_.getLocalBoundsForPose(pose);

                for(int[] aint1 : aint) {
                    blockpos$mutable.set(blockpos.getX() + aint1[0], blockpos.getY(), blockpos.getZ() + aint1[1]);
                    double d0 = this.level.getBlockFloorHeight(blockpos$mutable);
                    if (TransportationHelper.isBlockFloorValid(d0)) {
                        Vector3d vector3d = Vector3d.upFromBottomCenterOf(blockpos$mutable, d0);
                        if (TransportationHelper.canDismountTo(this.level, p_230268_1_, axisalignedbb.move(vector3d))) {
                            p_230268_1_.setPose(pose);
                            return vector3d;
                        }
                    }
                }
            }

            return super.getDismountLocationForPassenger(p_230268_1_);
        }
    }

    public void travel(Vector3d p_213352_1_) {
        this.travel(this, this.steering, p_213352_1_);
    }

    public float getSteeringSpeed() {
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.225F;
    }

    public void travelWithInput(Vector3d p_230267_1_) {
        super.travel(p_230267_1_);
    }

    public boolean boost() {
        return this.steering.boost(this.getRandom());
    }

    public SnowPigEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return EntityTypeRegistry.SNOW_PIG.get().create(p_241840_1_);
    }

    public boolean isFood(ItemStack p_70877_1_) {
        return FOOD_ITEMS.test(p_70877_1_);
    }

    @OnlyIn(Dist.CLIENT)
    public Vector3d getLeashOffset() {
        return new Vector3d(0.0D, (double)(0.6F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
