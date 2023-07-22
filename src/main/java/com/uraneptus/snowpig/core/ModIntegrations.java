package com.uraneptus.snowpig.core;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;
import vectorwing.farmersdelight.common.registry.ModCreativeTabs;
import vectorwing.farmersdelight.common.registry.ModItems;

public class ModIntegrations {

    public static final String FD_MODID = "farmersdelight";
    public static final Boolean IS_FD_LOADED = ModList.get().isLoaded(FD_MODID);
    public static final Boolean IS_AD_LOADED = ModList.get().isLoaded("abnormals_delight");

    public static CreativeModeTab getFdTab() {
        return ModCreativeTabs.TAB_FARMERS_DELIGHT.get();
    }

    public static Item getHam() {
        return ModItems.HAM.get();
    }


}
