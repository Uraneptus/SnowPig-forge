package com.uraneptus.snowpig.core.data;

import com.uraneptus.snowpig.SnowPigMod;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public class SPDatagenUtil {
    public static final String LAYER0 = "layer0";
    public static final String GENERATED = "item/generated";
    public static final String HANDHELD = "item/handheld";
    public static final String SPAWN_EGG = "item/template_spawn_egg";
    public static final String POTTED_CROSS = "block/flower_pot_cross";
    public static final RegistryAccess REGISTRY_ACCESS = RegistryAccess.builtinCopy();
    public static final Registry<Biome> BIOME_REGISTRY = REGISTRY_ACCESS.registryOrThrow(Registry.BIOME_REGISTRY);
    public static final Registry<PlacedFeature> PLACED_FEATURE_REGISTRY = REGISTRY_ACCESS.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY);
    public static HolderSet<PlacedFeature> getPlacementHolder(String name) {
        return HolderSet.direct(SPDatagenUtil.PLACED_FEATURE_REGISTRY.getOrCreateHolderOrThrow(ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, SnowPigMod.modPrefix(name))));
    }

    public static String name(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    public static String name(Item item) {
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    public static ResourceLocation modBlockLocation(String path) {
        return SnowPigMod.modPrefix(ModelProvider.BLOCK_FOLDER + "/" + path);
    }

    public static ResourceLocation modItemLocation(String path) {
        return SnowPigMod.modPrefix(ModelProvider.ITEM_FOLDER + "/" + path);
    }

    public static ResourceLocation vanillaBlockLocation(String path) {
        return new ResourceLocation(ModelProvider.BLOCK_FOLDER + "/" + path);
    }

    public static ResourceLocation vanillaItemLocation(String path) {
        return new ResourceLocation(ModelProvider.ITEM_FOLDER + "/" + path);
    }

    public static ResourceLocation craftingPath(String name) {
        return SnowPigMod.modPrefix("crafting/" + name);
    }

    public static ResourceLocation smeltingPath(String name) {
        return SnowPigMod.modPrefix("smelting/" + name);
    }

    public static ResourceLocation blastingPath(String name) {
        return SnowPigMod.modPrefix("blasting/" + name);
    }

    public static ResourceLocation smokingPath(String name) {
        return SnowPigMod.modPrefix("smoking/" + name);
    }

    public static ResourceLocation campfire_cookingPath(String name) {
        return SnowPigMod.modPrefix("campfire_cooking/" + name);
    }

    public static ResourceLocation stonecuttingPath(String name) {
        return SnowPigMod.modPrefix("stonecutting/" + name);
    }

    public static ResourceLocation smithingPath(String name) {
        return SnowPigMod.modPrefix("smithing/" + name);
    }
}
