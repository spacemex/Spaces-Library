package me.spacemex.spacelib.datagen.modifier;

import me.spacemex.spacelib.enums.LootType;
import me.spacemex.spacelib.modifier.type.AddEnchantable;
import me.spacemex.spacelib.modifier.type.AddItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.registries.RegistryObject;

public class CustomLootModifierProvider extends GlobalLootModifierProvider {
    public CustomLootModifierProvider(DataGenerator gen, String modid) {
        super(gen, modid);
        this.modid=modid;
    }
    private final String modid;

    @Override
    protected void start() {
    }
    protected void addItem(LootModifier modifier, String resourceLocation, Item item){
        String itemName = item.toString();
        String name = itemName + "_" + modifier;
        add(name, new AddItem(
                new LootItemCondition[]{LootTableIdCondition.builder(new ResourceLocation(resourceLocation)).build()},
                item));
    }
    protected void addItem(LootType lootType, Item item){
        String itemName = item.toString();
        String name = itemName + "_" + lootType.getModifier();
        add(name, new AddItem(
                new LootItemCondition[]{LootTableIdCondition.builder(new ResourceLocation(lootType.toString())).build()},
                item));
    }
    protected void addEnchatable(LootModifier modifier, String resourceLocation, Item item){
        String itemName = item.toString();
        String name = itemName + "_" + modifier;
        add(name, new AddEnchantable(
                new LootItemCondition[]{LootTableIdCondition.builder(new ResourceLocation(resourceLocation)).build()},
                item));
    }
    protected void addEnchatable(LootType lootType,Item item){
        String itemName = item.toString();
        String name = itemName + "_" + lootType.getModifier();
        add(name, new AddEnchantable(
                new LootItemCondition[]{LootTableIdCondition.builder(new ResourceLocation(lootType.toString())).build()},
                item));
    }
    protected void addEnchatable(LootType lootType, RegistryObject<Item> item){
        String itemName = item.toString();
        String name = itemName + "_" + lootType.getModifier();
        add(name, new AddEnchantable(
                new LootItemCondition[]{LootTableIdCondition.builder(new ResourceLocation(lootType.toString())).build()},
                item.get()));
    }
    protected void addEnchatable(LootModifier modifier, String resourceLocation, RegistryObject<Item> item){
        String itemName = item.toString();
        String name = itemName + "_" + modifier;
        add(name, new AddEnchantable(
                new LootItemCondition[]{LootTableIdCondition.builder(new ResourceLocation(resourceLocation)).build()},
                item.get()));
    }
}
