package com.uraneptus.snowpig.core.data.server;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.uraneptus.snowpig.SnowPigMod;
import com.uraneptus.snowpig.common.items.SnowPigLootItem;
import com.uraneptus.snowpig.core.registry.SPBlocks;
import com.uraneptus.snowpig.core.registry.SPEntityTypes;
import com.uraneptus.snowpig.core.registry.SPItems;
import com.uraneptus.snowpig.core.tags.SPEntityTags;
import com.uraneptus.snowpig.core.tags.SPItemTags;
import net.minecraft.advancements.critereon.EntityEquipmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.EntityLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SPLootTableProvider extends LootTableProvider {
    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> lootTables = ImmutableList.of(Pair.of(SPBlockLoot::new, LootContextParamSets.BLOCK), Pair.of(SPEntityLoot::new, LootContextParamSets.ENTITY));

    public SPLootTableProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return this.lootTables;
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
    }

    private static class SPBlockLoot extends BlockLoot {
        @Override
        protected void addTables() {
            this.dropSelf(SPBlocks.ARCTIC_LILY.get());
            this.dropPottedContents(SPBlocks.POTTED_ARCTIC_LILY.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ForgeRegistries.BLOCKS.getValues().stream().filter(blocks -> ForgeRegistries.BLOCKS.getKey(blocks) != null && SnowPigMod.MOD_ID.equals(ForgeRegistries.BLOCKS.getKey(blocks).getNamespace())).collect(Collectors.toSet());
        }
    }

    private static class SPEntityLoot extends EntityLoot {

        @Override
        protected Iterable<EntityType<?>> getKnownEntities() {
            return ForgeRegistries.ENTITY_TYPES.getValues().stream().filter(entityType -> ForgeRegistries.ENTITY_TYPES.getKey(entityType) != null && SnowPigMod.MOD_ID.equals(ForgeRegistries.ENTITY_TYPES.getKey(entityType).getNamespace())).collect(Collectors.toSet());
        }

        @Override
        protected void addTables() {
            this.add(SPEntityTypes.SNOW_PIG.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(SnowPigLootItem.lootTableItem(SPItems.FROZEN_PORKCHOP.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))).apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(SnowPigLootItem.lootTableItem(SPItems.MUSIC_DISC_FROSTY_SNIG.get()).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER, EntityPredicate.Builder.entity().of(SPEntityTags.KILLER_FOR_SNOWPIG_DISC))))));
            this.add(SnowPigMod.modPrefix("entities/mod_integration/frozen_ham_loot"), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(2.0F)).add(SnowPigLootItem.lootTableItem(SPItems.FROZEN_PORKCHOP.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))).add(SnowPigLootItem.lootTableItem(SPItems.FROZEN_HAM.get()).apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER, EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(SPItemTags.KNIVES).build()).build()))).when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.5F, 0.1F)))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(SnowPigLootItem.lootTableItem(SPItems.MUSIC_DISC_FROSTY_SNIG.get()).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER, EntityPredicate.Builder.entity().of(SPEntityTags.KILLER_FOR_SNOWPIG_DISC))))));
        }
    }
}
