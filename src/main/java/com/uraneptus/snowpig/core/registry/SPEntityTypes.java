package com.uraneptus.snowpig.core.registry;

import com.uraneptus.snowpig.common.entities.SnowPig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SPEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, com.uraneptus.snowpig.SnowPig.MOD_ID);

    public static final RegistryObject<EntityType<SnowPig>> SNOW_PIG = ENTITY_TYPES.register("snow_pig",
            () -> EntityType.Builder.of(SnowPig::new, MobCategory.CREATURE)
                    .sized(0.9f,0.9f)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(com.uraneptus.snowpig.SnowPig.MOD_ID, "snow_pig").toString()));
}
