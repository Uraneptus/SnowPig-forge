package com.uraneptus.snowpig.core.registry;

import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.common.entities.SnowPig;
import com.uraneptus.snowpig.common.entities.ThrownIceBomb;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SPEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SnowPigMod.MOD_ID);

    public static final RegistryObject<EntityType<SnowPig>> SNOW_PIG = ENTITY_TYPES.register("snow_pig",
            () -> EntityType.Builder.of(SnowPig::new, MobCategory.CREATURE)
                    .sized(0.9f,0.9f)
                    .clientTrackingRange(10)
                    .build(SnowPigMod.modPrefix("snow_pig").toString()));

    public static final RegistryObject<EntityType<ThrownIceBomb>> ICE_BOMB = ENTITY_TYPES.register("ice_bomb", () -> EntityType.Builder.<ThrownIceBomb>of(ThrownIceBomb::new, MobCategory.MISC).setTrackingRange(64).setUpdateInterval(1).sized(0.25f, 0.25f).build(SnowPigMod.modPrefix("ice_bomb").toString()));

}
