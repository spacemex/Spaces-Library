package me.spacemex.spacelib.datagen.models;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

public class CustomBlockStateProvider extends BlockStateProvider {
    public CustomBlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
        this.modid=modid;
    }
    private final String modid;

    @Override
    protected void registerStatesAndModels() {

    }
    protected void makeCrop(CropBlock block, String modelName, String textureName){
        Function<BlockState, ConfiguredModel[]> function = state->states(state,block,modelName,textureName);
        getVariantBuilder(block).forAllStates(function);
    }
    protected ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName){
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0]=new ConfiguredModel(models().crop(modelName + state.getValue(block.getAgeProperty()),
                new ResourceLocation(modid,"block/"+textureName+state.getValue(block.getAgeProperty()))).renderType("cut_out"));

        return models;
    }
    protected ModelFile flowerPotCross(String name){
        return models().withExistingParent(name,"flower_pot_cross");
    }
    protected void simpleBlockItem(Block block) {
        String blockName = ForgeRegistries.BLOCKS.getKey(block).getPath();
        simpleBlock(block);
        itemModels().withExistingParent(blockName, new ResourceLocation(modid, "block/" + blockName));
    }
}
