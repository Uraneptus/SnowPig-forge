package com.uraneptus.snowpig;

import com.uraneptus.snowpig.client.entity.render.SnowPigRender;
import com.uraneptus.snowpig.common.entities.SnowPigEntity;
import com.uraneptus.snowpig.common.items.SnowPigEgg;
import com.uraneptus.snowpig.core.registry.EntityTypeRegistry;
import com.uraneptus.snowpig.core.registry.ItemRegistry;
import com.uraneptus.snowpig.core.registry.SoundRegistry;
import com.uraneptus.snowpig.core.world.gen.SnowPigSpawn;
import com.uraneptus.snowpig.core.world.gen.SnowPigSpawnPlacement;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SnowPig.MOD_ID)
@Mod.EventBusSubscriber(modid = SnowPig.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SnowPig
{

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "snowpig";

    public SnowPig() {
        IEventBus event_bus = FMLJavaModLoadingContext.get().getModEventBus();
        event_bus.addListener(this::setup);
        event_bus.addListener(this::setupClient);

        SoundRegistry.SOUNDS.register(event_bus);
        ItemRegistry.ITEMS.register(event_bus);
        EntityTypeRegistry.ENTITY_TYPES.register(event_bus);

        MinecraftForge.EVENT_BUS.register(this);

        SnowPigSpawnPlacement.registerMod(event_bus);
        SnowPigSpawn.registerForge(MinecraftForge.EVENT_BUS);
    }

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        SnowPigEgg.regSpawnEggs();
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->{
            GlobalEntityTypeAttributes.put(EntityTypeRegistry.SNOW_PIG.get(), SnowPigEntity.createAttributes().build());
        });

    }

    public void setupClient(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeRegistry.SNOW_PIG.get(), SnowPigRender::new);
    }

}
