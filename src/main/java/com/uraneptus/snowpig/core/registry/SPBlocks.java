package com.uraneptus.snowpig.core.registry;

import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.common.blocks.ArcticLilyFlower;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SPBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SnowPigMod.MOD_ID);

    public static final RegistryObject<Block> ARCTIC_LILY = BLOCKS.register("arctic_lily", () -> new ArcticLilyFlower(BlockBehaviour.Properties.copy(Blocks.CORNFLOWER)));
    public static final RegistryObject<Block> POTTED_ARCTIC_LILY = BLOCKS.register("potted_arctic_lily", () -> new FlowerPotBlock(null, ARCTIC_LILY, BlockBehaviour.Properties.copy(Blocks.POTTED_CORNFLOWER)));

}
