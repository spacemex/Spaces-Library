package me.spacemex.spacelib.datagen.models;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class CustomItemModelProvider extends ItemModelProvider {
    public CustomItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
        this.modid=modid;
    }
    private final String modid;

    @Override
    protected void registerModels() {

    }
    protected ItemModelBuilder simpleItem(RegistryObject<Item>item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(modid, "item/"+item.getId().getPath()));
    }
    protected ItemModelBuilder handheldItem(RegistryObject<Item>item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(modid, "item/"+item.getId().getPath()));
    }
}
