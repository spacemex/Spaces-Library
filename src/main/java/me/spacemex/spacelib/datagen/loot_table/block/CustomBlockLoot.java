package me.spacemex.spacelib.datagen.loot_table.block;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CustomBlockLoot extends BlockLoot {
    @Override
    protected void addTables() {

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return null;
    }
    protected void addCrop(RegistryObject<Block> crop, RegistryObject<Item> cropDrop, RegistryObject<Item> seedDrop, LootItemCondition.Builder builder){
        add(crop.get(),createCropDrops(crop.get(), cropDrop.get(),seedDrop.get(), builder));
    }
    protected void addCrop(Supplier<Block> crop, Supplier<Item> cropDrop, Supplier<Item> seedDrop, LootItemCondition.Builder builder){
        add(crop.get(),createCropDrops(crop.get(), cropDrop.get(),seedDrop.get(), builder));
    }
    protected void addCrop(Block crop,Item cropDrop, Item seedDrop, LootItemCondition.Builder builder){
        add(crop,createCropDrops(crop, cropDrop,seedDrop, builder));
    }
    protected LootItemCondition.Builder buildCustom(RegistryObject<Block> crop){
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(crop.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7));
    }
    protected LootItemCondition.Builder buildCustom(Block crop){
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(crop).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7));
    }
    protected LootItemCondition.Builder buildCustom(Supplier<Block> crop){
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(crop.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7));
    }
}
