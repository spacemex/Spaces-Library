package me.spacemex.spacelib.block;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.function.Supplier;

public class CustomCropBlock extends CropBlock {
    public  static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    private final Supplier<Item> cropItem;
    private final Supplier<Item> seedItem;
    public CustomCropBlock(Properties properties, Supplier<Item> cropItem, Supplier<Item> seedItem){
        super(properties);
        this.cropItem=cropItem;
        this.seedItem=seedItem;
    }
    protected ItemLike getCropItem(){
        return cropItem.get();
    }
    protected ItemLike getSeedItem(){
        return seedItem.get();
    }
    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }
    @Override
    protected ItemLike getBaseSeedId() {
        return getSeedItem();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(AGE);
    }
}
