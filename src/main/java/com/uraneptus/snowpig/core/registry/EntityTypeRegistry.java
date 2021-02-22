package com.uraneptus.snowpig.core.registry;

import com.uraneptus.snowpig.SnowPig;
import com.uraneptus.snowpig.common.entities.SnowPigEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypeRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, SnowPig.MOD_ID);

    public static final RegistryObject<EntityType<SnowPigEntity>> SNOW_PIG = ENTITY_TYPES.register("snow_pig",
            () -> EntityType.Builder.create(SnowPigEntity::new, EntityClassification.CREATURE)
                    .size(0.9f,0.9f)
                    .build(new ResourceLocation(SnowPig.MOD_ID, "snow_pig").toString()));
}
