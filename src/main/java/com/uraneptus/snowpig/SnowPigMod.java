package com.uraneptus.snowpig;

import com.uraneptus.snowpig.client.entity.render.SnowPigRender;
import com.uraneptus.snowpig.common.capabilities.SPEntityCap;
import com.uraneptus.snowpig.common.entities.SnowPig;
import com.uraneptus.snowpig.core.data.client.SPItemModelProvider;
import com.uraneptus.snowpig.core.data.client.SPLangProvider;
import com.uraneptus.snowpig.core.data.client.SPSoundDefinitionsProvider;
import com.uraneptus.snowpig.core.data.server.SPRecipeProvider;
import com.uraneptus.snowpig.core.data.server.datapack_registries.SPBiomeModifiers;
import com.uraneptus.snowpig.core.data.server.loot.SPLootTableProvider;
import com.uraneptus.snowpig.core.data.server.tags.SPBiomeTagsProvider;
import com.uraneptus.snowpig.core.data.server.tags.SPBlockTagsProvider;
import com.uraneptus.snowpig.core.data.server.tags.SPEntityTagsProvider;
import com.uraneptus.snowpig.core.data.server.tags.SPItemTagsProvider;
import com.uraneptus.snowpig.core.registry.SPEntityTypes;
import com.uraneptus.snowpig.core.registry.SPItems;
import com.uraneptus.snowpig.core.registry.SPLootPoolEntryTypes;
import com.uraneptus.snowpig.core.registry.SPSounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod(SnowPigMod.MOD_ID)
@Mod.EventBusSubscriber(modid = SnowPigMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SnowPigMod {
    public static final String MOD_ID = "snowpig";
    private static final RegistrySetBuilder SET_BUILDER = new RegistrySetBuilder().add(ForgeRegistries.Keys.BIOME_MODIFIERS, SPBiomeModifiers::create);

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
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(includeClient, new SPItemModelProvider(packOutput, fileHelper));
        generator.addProvider(includeClient, new SPSoundDefinitionsProvider(packOutput, fileHelper));
        generator.addProvider(includeClient, new SPLangProvider(packOutput));

        SPBlockTagsProvider blockTagProvider = new SPBlockTagsProvider(packOutput, lookupProvider, fileHelper);
        generator.addProvider(includeServer, blockTagProvider);
        generator.addProvider(includeServer, new SPItemTagsProvider(packOutput, lookupProvider, blockTagProvider.contentsGetter(), fileHelper));
        generator.addProvider(includeServer, new SPEntityTagsProvider(packOutput, lookupProvider, fileHelper));
        generator.addProvider(includeServer, new SPBiomeTagsProvider(packOutput, lookupProvider, fileHelper));
        generator.addProvider(includeServer, new SPLootTableProvider(packOutput));
        generator.addProvider(includeServer, new SPRecipeProvider(packOutput));
        generator.addProvider(includeServer, new DatapackBuiltinEntriesProvider(packOutput, lookupProvider, SET_BUILDER, Set.of(MOD_ID)));
    }

}
