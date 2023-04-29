package me.spacemex.spacelib.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class CustomSlabBlock extends SlabBlock {
    public CustomSlabBlock(Block block) {
        this(Properties.copy(block));
    }
    public CustomSlabBlock(Properties properties) {
        super(properties);
    }
    public CustomSlabBlock(Material material, SoundType sound, float hardness, float resistance) {
        this(Properties.of(material)
                .sound(sound)
                .strength(hardness, resistance)
        );
    }
    public CustomSlabBlock(Material material, SoundType sound, float hardness, float resistance, boolean tool) {
        this(Properties.of(material)
                .sound(sound)
                .strength(hardness, resistance)
                .requiresCorrectToolForDrops()
        );
    }
}