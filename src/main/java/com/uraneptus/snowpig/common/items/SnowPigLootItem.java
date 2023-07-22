package com.uraneptus.snowpig.common.items;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.uraneptus.snowpig.core.registry.SPLootPoolEntryTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.Consumer;

public class SnowPigLootItem extends LootPoolSingletonContainer {
    final Item item;

    protected SnowPigLootItem(Item pItem, int pWeight, int pQuality, LootItemCondition[] pConditions, LootItemFunction[] pFunctions) {
        super(pWeight, pQuality, pConditions, pFunctions);
        this.item = pItem;
    }

    @Override
    public LootPoolEntryType getType() {
        return SPLootPoolEntryTypes.SNOWPIG_ITEM.get();
    }


    @Override
    protected void createItemStack(Consumer<ItemStack> pStackConsumer, LootContext pLootContext) {
        pStackConsumer.accept(new ItemStack(this.item));
    }


    public static LootPoolSingletonContainer.Builder<?> lootTableItem(ItemLike pItem) {
        return simpleBuilder((p_79583_, p_79584_, p_79585_, p_79586_) -> {
            return new SnowPigLootItem(pItem.asItem(), p_79583_, p_79584_, p_79585_, p_79586_);
        });
    }

    public static class Serializer extends LootPoolSingletonContainer.Serializer<SnowPigLootItem> {
        public void serializeCustom(JsonObject pObject, SnowPigLootItem pContext, JsonSerializationContext pConditions) {
            super.serializeCustom(pObject, pContext, pConditions);
            ResourceLocation resourcelocation = BuiltInRegistries.ITEM.getKey(pContext.item);
            if (resourcelocation == null) {
                pObject.addProperty("name", Items.AIR.toString());
            } else {
                pObject.addProperty("name", resourcelocation.toString());
            }
        }

        protected SnowPigLootItem deserialize(JsonObject pObject, JsonDeserializationContext pContext, int pWeight, int pQuality, LootItemCondition[] pConditions, LootItemFunction[] pFunctions) {
            Item item = GsonHelper.getAsItem(pObject, "name");
            return new SnowPigLootItem(item, pWeight, pQuality, pConditions, pFunctions);
        }
    }


}
