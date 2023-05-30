package com.uraneptus.snowpig;

import com.uraneptus.snowpig.client.entity.render.SnowPigRender;
import com.uraneptus.snowpig.common.capabilities.SPEntityCap;
import com.uraneptus.snowpig.common.entities.SnowPig;
import com.uraneptus.snowpig.core.data.client.SPItemModelProvider;
import com.uraneptus.snowpig.core.data.client.SPLangProvider;
import com.uraneptus.snowpig.core.data.client.SPSoundDefinitionsProvider;
import com.uraneptus.snowpig.core.data.server.SPLootTableProvider;
import com.uraneptus.snowpig.core.data.server.SPRecipeProvider;
import com.uraneptus.snowpig.core.data.server.datapack_registries.SPBiomeModifiersProvider;
import com.uraneptus.snowpig.core.data.server.tags.SPBiomeTagsProvider;
import com.uraneptus.snowpig.core.data.server.tags.SPBlockTagsProvider;
import com.uraneptus.snowpig.core.data.server.tags.SPEntityTagsProvider;
import com.uraneptus.snowpig.core.data.server.tags.SPItemTagsProvider;
import com.uraneptus.snowpig.core.registry.SPEntityTypes;
import com.uraneptus.snowpig.core.registry.SPItems;
import com.uraneptus.snowpig.core.registry.SPLootPoolEntryTypes;
import com.uraneptus.snowpig.core.registry.SPSounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SnowPigMod.MOD_ID)
@Mod.EventBusSubscriber(modid = SnowPigMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SnowPigMod {
    public static final String MOD_ID = "snowpig";

    public static ResourceLocation modPrefix(String path) {
        return new ResourceLocation(SnowPigMod.MOD_ID, path);

    }

    public SnowPigMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setupClient);
        bus.addListener(this::gatherData);

        SPSounds.SOUNDS.register(bus);
        SPItems.ITEMS.register(bus);
        SPEntityTypes.ENTITY_TYPES.register(bus);
        SPLootPoolEntryTypes.LOOT_ENTRY.register(bus);

        MinecraftForge.EVENT_BUS.register(this);

    }
    public void setupClient(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SPEntityTypes.SNOW_PIG.get(), SnowPigRender::new);
    }

    @SubscribeEvent
    public static void addEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(SPEntityTypes.SNOW_PIG.get(), SnowPig.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(SPEntityCap.class);
    }

    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        boolean includeClient = event.includeClient();
        boolean includeServer = event.includeServer();
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        generator.addProvider(includeClient, new SPItemModelProvider(generator, fileHelper));
        generator.addProvider(includeClient, new SPSoundDefinitionsProvider(generator, fileHelper));
        generator.addProvider(includeClient, new SPLangProvider(generator));

        SPBlockTagsProvider blockTagProvider = new SPBlockTagsProvider(generator, fileHelper);
        generator.addProvider(includeServer, blockTagProvider);
        generator.addProvider(includeServer, new SPItemTagsProvider(generator, blockTagProvider, fileHelper));
        generator.addProvider(includeServer, new SPEntityTagsProvider(generator, fileHelper));
        generator.addProvider(includeServer, new SPBiomeTagsProvider(generator, fileHelper));
        generator.addProvider(includeServer, new SPLootTableProvider(generator));
        generator.addProvider(includeServer, new SPRecipeProvider(generator));
        generator.addProvider(includeServer, SPBiomeModifiersProvider.createBiomeModifiers(generator, fileHelper));
    }
}
