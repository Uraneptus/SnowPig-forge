package com.uraneptus.snowpig.core.data.client;

import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.core.registry.SPBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

import static com.uraneptus.snowpig.core.data.SPDatagenUtil.*;

public class SPBlockStateProvider extends BlockStateProvider {

    public SPBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SnowPigMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        plantWithPottedBlock(SPBlocks.ARCTIC_LILY, SPBlocks.POTTED_ARCTIC_LILY);
    }

    private void crossBlock(Supplier<? extends Block> block) {
        getVariantBuilder(block.get()).forAllStates(blockState -> ConfiguredModel.builder()
                .modelFile(models().cross(name(block.get()), modBlockLocation(name(block.get()))).renderType("cutout")).build());
    }

    private void plantWithPottedBlock(Supplier<? extends Block> plant, Supplier<? extends Block> potted_plant) {
        crossBlock(plant);
        simpleBlock(potted_plant.get(), models().withExistingParent(name(potted_plant.get()), POTTED_CROSS).renderType("cutout").texture("plant", modBlockLocation(name(plant.get()))));
    }
}
