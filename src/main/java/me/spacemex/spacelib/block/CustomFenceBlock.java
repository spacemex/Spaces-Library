package me.spacemex.spacelib.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.material.Material;

public class CustomFenceBlock extends FenceBlock {
    public CustomFenceBlock(Block block) {
        this(Properties.copy(block));
    }
    public CustomFenceBlock(Properties properties) {
        super(properties);
    }
    public CustomFenceBlock(Material material, SoundType sound, float hardness, float resistance) {
        this(Properties.of(material)
                .sound(sound)
                .strength(hardness, resistance)
        );
    }
    public CustomFenceBlock(Material material, SoundType sound, float hardness, float resistance, boolean tool) {
        this(Properties.of(material)
                .sound(sound)
                .strength(hardness, resistance)
                .requiresCorrectToolForDrops()
        );
    }
}