package com.uraneptus.snowpig.common.entities;

import com.uraneptus.snowpig.core.registry.EntityTypeRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SnowPigEntity extends PigEntity {

    public SnowPigEntity(EntityType<? extends SnowPigEntity> snowpig, World worldIn) {
        super(snowpig, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute func_234215_eI_() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.15D)
                .createMutableAttribute(Attributes.MAX_HEALTH, 10.0D);
    }

  /*  @Override
    public boolean func_230264_L__() {
        return this.isAlive() && !this.isChild();
    } */

    @Override
    public PigEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return EntityTypeRegistry.SNOW_PIG.get().create(p_241840_1_);
    }

   /* @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.fromItems(Items.CARROT_ON_A_STICK), false));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, false, TEMPTATION_ITEMS));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Nullable
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    @Override
    public boolean canBeSteered() {
        Entity entity = this.getControllingPassenger();
        if (!(entity instanceof PlayerEntity)) {
            return false;
        } else {
            PlayerEntity playerentity = (PlayerEntity)entity;
            return playerentity.getHeldItemMainhand().getItem() == Items.CARROT_ON_A_STICK || playerentity.getHeldItemOffhand().getItem() == Items.CARROT_ON_A_STICK;
        }
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {
        if (BOOST_TIME.equals(key) && this.world.isRemote) {
            this.field_234214_bx_.updateData();
        }

        super.notifyDataManagerChange(key);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(SADDLED, false);
        this.dataManager.register(BOOST_TIME, 0);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        this.field_234214_bx_.setSaddledToNBT(compound);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.field_234214_bx_.setSaddledFromNBT(compound);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PIG_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_PIG_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PIG_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15F, 1.0F);
    }

    @Override
    public ActionResultType func_230254_b_(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        boolean flag = this.isBreedingItem(p_230254_1_.getHeldItem(p_230254_2_));
        if (!flag && this.isHorseSaddled() && !this.isBeingRidden() && !p_230254_1_.isSecondaryUseActive()) {
            if (!this.world.isRemote) {
                p_230254_1_.startRiding(this);
            }

            return ActionResultType.func_233537_a_(this.world.isRemote);
        } else {
            ActionResultType actionresulttype = super.func_230254_b_(p_230254_1_, p_230254_2_);
            if (!actionresulttype.isSuccessOrConsume()) {
                ItemStack itemstack = p_230254_1_.getHeldItem(p_230254_2_);
                return itemstack.getItem() == Items.SADDLE ? itemstack.interactWithEntity(p_230254_1_, this, p_230254_2_) : ActionResultType.PASS;
            } else {
                return actionresulttype;
            }
        }
    }

    @Override
    public boolean func_230264_L__() {
        return this.isAlive() && !this.isChild();
    }

    @Override
    public void func_230266_a_(@Nullable SoundCategory p_230266_1_) {
        this.field_234214_bx_.setSaddledFromBoolean(true);
        if (p_230266_1_ != null) {
            this.world.playMovingSound((PlayerEntity)null, this, SoundEvents.ENTITY_PIG_SADDLE, p_230266_1_, 0.5F, 1.0F);
        }
    }

    @Override
    public boolean isHorseSaddled() {
        return this.field_234214_bx_.getSaddled();
    }

    @Override
    protected void dropInventory() {
        super.dropInventory();
        if (this.isHorseSaddled()) {
            this.entityDropItem(Items.SADDLE);
        }
    }

    public Vector3d func_230268_c_(LivingEntity livingEntity) {
        Direction direction = this.getAdjustedHorizontalFacing();
        if (direction.getAxis() == Direction.Axis.Y) {
            return super.func_230268_c_(livingEntity);
        } else {
            int[][] aint = TransportationHelper.func_234632_a_(direction);
            BlockPos blockpos = this.getPosition();
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

            for(Pose pose : livingEntity.getAvailablePoses()) {
                AxisAlignedBB axisalignedbb = livingEntity.getPoseAABB(pose);

                for(int[] aint1 : aint) {
                    blockpos$mutable.setPos(blockpos.getX() + aint1[0], blockpos.getY(), blockpos.getZ() + aint1[1]);
                    double d0 = this.world.func_242403_h(blockpos$mutable);
                    if (TransportationHelper.func_234630_a_(d0)) {
                        Vector3d vector3d = Vector3d.copyCenteredWithVerticalOffset(blockpos$mutable, d0);
                        if (TransportationHelper.func_234631_a_(this.world, livingEntity, axisalignedbb.offset(vector3d))) {
                            livingEntity.setPose(pose);
                            return vector3d;
                        }
                    }
                }
            }

            return super.func_230268_c_(livingEntity);
        }
    }

    @Override
    public void travel(Vector3d travelVector) {
        this.ride(this, this.field_234214_bx_, travelVector);
    }

    @Override
    public boolean boost() {
        return this.field_234214_bx_.boost(this.getRNG());
    }

    @Override
    public void travelTowards(Vector3d travelVec) {
        super.travel(travelVec);
    }

    @Override
    public float getMountedSpeed() {
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.225F;
    }

    @Override
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return EntityTypeRegistry.SNOW_PIG.get().create(p_241840_1_);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return TEMPTATION_ITEMS.test(stack);
    }

    @OnlyIn(Dist.CLIENT)
    public Vector3d func_241205_ce_() {
        return new Vector3d(0.0D, (double)(0.6F * this.getEyeHeight()), (double)(this.getWidth() * 0.4F));
    } */
}
