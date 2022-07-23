package com.uraneptus.snowpig.core.registry;

import com.uraneptus.snowpig.SnowPig;
import com.uraneptus.snowpig.common.entities.SnowPigEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTypeRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, SnowPig.MOD_ID);

    public static final RegistryObject<EntityType<SnowPigEntity>> SNOW_PIG = ENTITY_TYPES.register("snow_pig",
            () -> EntityType.Builder.of(SnowPigEntity::new, MobCategory.CREATURE)
                    .sized(0.9f,0.9f)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(SnowPig.MOD_ID, "snow_pig").toString()));
}
