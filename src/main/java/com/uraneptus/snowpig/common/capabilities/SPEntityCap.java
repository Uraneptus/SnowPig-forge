package com.uraneptus.snowpig.common.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class SPEntityCap implements INBTSerializable<CompoundTag> {

    public static Capability<SPEntityCap> ENTITY_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});

    public int freezeTicks = 300;

    public SPEntityCap() {

    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        tag.putInt("freezeTicks", freezeTicks);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        freezeTicks = nbt.getInt("freezeTicks");
    }

    public static LazyOptional<SPEntityCap> getCapOptional(LivingEntity entity) {
        return entity.getCapability(ENTITY_CAPABILITY);
    }

    @Mod.EventBusSubscriber
    public static class Events {

        @SubscribeEvent
        public static void onAttachCapability(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Pig) {
                final SPEntityCapProvider provider = new SPEntityCapProvider();
                event.addCapability(SPEntityCapProvider.LOCATION, provider);
            }
        }
    }
}
