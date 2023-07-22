package com.uraneptus.snowpig.core.events;

import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.client.entity.render.SnowPigRender;
import com.uraneptus.snowpig.core.ModIntegrations;
import com.uraneptus.snowpig.core.registry.SPEntityTypes;
import com.uraneptus.snowpig.core.registry.SPItems;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = SnowPigMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SPClientEvents {

    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SPEntityTypes.SNOW_PIG.get(), SnowPigRender::new);
    }

    @SubscribeEvent
    public static void buildTabContents(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tabKey = event.getTabKey();
        CreativeModeTab tab = event.getTab();

        if (tabKey == CreativeModeTabs.FOOD_AND_DRINKS) {
            addBefore(Items.PORKCHOP, SPItems.FROZEN_PORKCHOP, event);
        }
        if (tabKey == CreativeModeTabs.SPAWN_EGGS) {
            addBefore(Items.PIG_SPAWN_EGG, SPItems.SNOW_PIG_EGG, event);
        }
        if (tabKey == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            addAfter(Items.MUSIC_DISC_OTHERSIDE, SPItems.MUSIC_DISC_FROSTY_SNIG, event);
        }

        if (ModIntegrations.IS_FD_LOADED) {
            if (!ModIntegrations.IS_AD_LOADED) {
                if (tab == ModIntegrations.getFdTab()) {
                    addBefore(ModIntegrations.getHam(), SPItems.FROZEN_HAM, event);
                }
            }
        } else {
            if (tabKey == CreativeModeTabs.FOOD_AND_DRINKS) {
                addAfter(Items.COOKED_PORKCHOP, SPItems.FROZEN_HAM, event);
            }
        }
    }

    private static void addBefore(Item before, Supplier<? extends ItemLike> inputItem, BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putBefore(before.getDefaultInstance(), inputItem.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
    private static void addAfter(Item after, Supplier<? extends ItemLike> inputItem, BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(after.getDefaultInstance(), inputItem.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

}
