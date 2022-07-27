package com.uraneptus.snowpig;

import com.uraneptus.snowpig.client.entity.render.SnowPigRender;
import com.uraneptus.snowpig.common.capabilities.SPEntityCap;
import com.uraneptus.snowpig.common.entities.SnowPigEntity;
import com.uraneptus.snowpig.core.registry.EntityTypeRegistry;
import com.uraneptus.snowpig.core.registry.ItemRegistry;
import com.uraneptus.snowpig.core.registry.LootPoolEntryTypeRegistry;
import com.uraneptus.snowpig.core.registry.SoundRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SnowPig.MOD_ID)
@Mod.EventBusSubscriber(modid = SnowPig.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SnowPig
{
    public static final String MOD_ID = "snowpig";

    public SnowPig() {
        IEventBus event_bus = FMLJavaModLoadingContext.get().getModEventBus();
        //event_bus.addListener(this::setup);
        event_bus.addListener(this::setupClient);

        SoundRegistry.SOUNDS.register(event_bus);
        ItemRegistry.ITEMS.register(event_bus);
        EntityTypeRegistry.ENTITY_TYPES.register(event_bus);
        LootPoolEntryTypeRegistry.LOOT_ENTRY.register(event_bus);

        MinecraftForge.EVENT_BUS.register(this);

    }
    public void setupClient(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityTypeRegistry.SNOW_PIG.get(), SnowPigRender::new);
    }

    @SubscribeEvent
    public static void addEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(EntityTypeRegistry.SNOW_PIG.get(), SnowPigEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(SPEntityCap.class);
    }
}
