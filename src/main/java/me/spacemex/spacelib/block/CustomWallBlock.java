package me.spacemex.spacelib.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.material.Material;

public class CustomWallBlock extends WallBlock {
    public CustomWallBlock(Block block) {
        this(Properties.copy(block));
    }
    public CustomWallBlock(Properties properties) {
        super(properties);
    }
    public CustomWallBlock(Material material, SoundType sound, float hardness, float resistance) {
        this(Properties.of(material)
                .sound(sound)
                .strength(hardness, resistance)
        );
    }
    public CustomWallBlock(Material material, SoundType sound, float hardness, float resistance, boolean tool) {
        this(Properties.of(material)
                .sound(sound)
                .strength(hardness, resistance)
                .requiresCorrectToolForDrops()
        );
    }
}