package com.uraneptus.snowpig.common.entities;

import com.uraneptus.snowpig.core.registry.EntityTypeRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.effect.LightningBoltEntity;
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

    public void func_241841_a(ServerWorld p_241841_1_, LightningBoltEntity p_241841_2_) {
        return;
    }


    @Override
    public PigEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return EntityTypeRegistry.SNOW_PIG.get().create(p_241840_1_);
    }

}
