package com.uraneptus.snowpig.core;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;

public class ModIntegrations {

    public static final String FD_MODID = "farmersdelight";
    public static final Boolean IS_FD_LOADED = ModList.get().isLoaded(FD_MODID);

    public static final Item HAM = ModItems.HAM.get();

    public static CreativeModeTab getFdItemGroup() {
        return FarmersDelight.CREATIVE_TAB;
    }
}
