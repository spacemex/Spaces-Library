package me.spacemex.spacelib.datagen.recipe;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class CustomRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public CustomRecipeProvider(DataGenerator generator, String modid) {
        super(generator);
        this.modid=modid;
    }
    private final String modid;

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipeConsumer) {

    }
    protected void ingotsToNuggets(RegistryObject<Item> resultItem,RegistryObject<Item> inputItem,String advancement,RegistryObject<Item> trigger,  Consumer<FinishedRecipe> recipeConsumer){
        ShapelessRecipeBuilder.shapeless(resultItem.get(),9).requires(inputItem.get())
                .unlockedBy(advancement, has(trigger.get()))
                .save(recipeConsumer, new ResourceLocation(modid,getItemName(resultItem.get())+"_nuggets_from_"+getItemName(inputItem.get())));
    }
    protected void ingotsToNuggets(Supplier<Item> resultItem, Supplier<Item> inputItem, String advancement, Supplier<Item> trigger, Consumer<FinishedRecipe> recipeConsumer){
        ShapelessRecipeBuilder.shapeless(resultItem.get(),9).requires(inputItem.get())
                .unlockedBy(advancement, has(trigger.get()))
                .save(recipeConsumer, new ResourceLocation(modid,getItemName(resultItem.get())+"_nuggets_from_"+getItemName(inputItem.get())));
    }
    protected void ingotsToNuggets(Item resultItem, Item inputItem, String advancement, Item trigger, Consumer<FinishedRecipe> recipeConsumer){
        ShapelessRecipeBuilder.shapeless(resultItem,9).requires(inputItem)
                .unlockedBy(advancement, has(trigger))
                .save(recipeConsumer, new ResourceLocation(modid,getItemName(resultItem)+"_nuggets_from_"+getItemName(inputItem)));
    }
    protected void ingotsToNuggets(RegistryObject<Item> resultItem,RegistryObject<Item> inputItem,String advancement,Item trigger,  Consumer<FinishedRecipe> recipeConsumer){
        ShapelessRecipeBuilder.shapeless(resultItem.get(),9).requires(inputItem.get())
                .unlockedBy(advancement, has(trigger))
                .save(recipeConsumer, new ResourceLocation(modid,getItemName(resultItem.get())+"_nuggets_from_"+getItemName(inputItem.get())));
    }
    protected void ingotsToNuggets(Supplier<Item> resultItem, Supplier<Item> inputItem, String advancement, Item trigger, Consumer<FinishedRecipe> recipeConsumer){
        ShapelessRecipeBuilder.shapeless(resultItem.get(),9).requires(inputItem.get())
                .unlockedBy(advancement, has(trigger))
                .save(recipeConsumer, new ResourceLocation(modid,getItemName(resultItem.get())+"_nuggets_from_"+getItemName(inputItem.get())));
    }

    protected void nuggetsToIngots(RegistryObject<Item> resultItem,RegistryObject<Item> inputItem,String advancement,RegistryObject<Item> trigger,  Consumer<FinishedRecipe> recipeConsumer){
        ShapedRecipeBuilder.shaped(resultItem.get())
                .define('#',inputItem.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(advancement,has(trigger.get()))
                .save(recipeConsumer,new ResourceLocation(modid,getItemName(resultItem.get())+"_ingot_from_"+getItemName(inputItem.get())));
    }
    protected void nuggetsToIngots(Supplier<Item> resultItem,Supplier<Item> inputItem,String advancement,Supplier<Item> trigger,  Consumer<FinishedRecipe> recipeConsumer){
        ShapedRecipeBuilder.shaped(resultItem.get())
                .define('#',inputItem.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(advancement,has(trigger.get()))
                .save(recipeConsumer,new ResourceLocation(modid,getItemName(resultItem.get())+"_ingot_from_"+getItemName(inputItem.get())));
    }
    protected void nuggetsToIngots(Item resultItem,Item inputItem,String advancement,Item trigger,  Consumer<FinishedRecipe> recipeConsumer){
        ShapedRecipeBuilder.shaped(resultItem)
                .define('#',inputItem)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(advancement,has(trigger))
                .save(recipeConsumer,new ResourceLocation(modid,getItemName(resultItem)+"_ingot_from_"+getItemName(inputItem)));
    }
    protected void nuggetsToIngots(RegistryObject<Item> resultItem,RegistryObject<Item> inputItem,String advancement,Item trigger,  Consumer<FinishedRecipe> recipeConsumer){
        ShapedRecipeBuilder.shaped(resultItem.get())
                .define('#',inputItem.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(advancement,has(trigger))
                .save(recipeConsumer,new ResourceLocation(modid,getItemName(resultItem.get())+"_ingot_from_"+getItemName(inputItem.get())));
    }
    protected void nuggetsToIngots(Supplier<Item> resultItem,Supplier<Item> inputItem,String advancement,Item trigger,  Consumer<FinishedRecipe> recipeConsumer){
        ShapedRecipeBuilder.shaped(resultItem.get())
                .define('#',inputItem.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(advancement,has(trigger))
                .save(recipeConsumer,new ResourceLocation(modid,getItemName(resultItem.get())+"_ingot_from_"+getItemName(inputItem.get())));
    }
}
