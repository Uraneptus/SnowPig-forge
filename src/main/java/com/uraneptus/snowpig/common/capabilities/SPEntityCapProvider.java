package com.uraneptus.snowpig.common.capabilities;

import com.uraneptus.snowpig.SnowPig;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SPEntityCapProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    //The Capability Name MUST be snake case
    public static final ResourceLocation LOCATION = new ResourceLocation(SnowPig.MOD_ID, "snowpig_cap");
    private final SPEntityCap backend = new SPEntityCap();
    private final LazyOptional<SPEntityCap> optional = LazyOptional.of(() -> backend);

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return SPEntityCap.ENTITY_CAPABILITY.orEmpty(cap, this.optional);
    }

    @Override
    public CompoundTag serializeNBT() {
        return this.backend.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.backend.deserializeNBT(nbt);
    }

    public SPEntityCapProvider() {

    }

}
