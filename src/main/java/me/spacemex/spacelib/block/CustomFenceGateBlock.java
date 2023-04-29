package me.spacemex.spacelib.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.material.Material;

public class CustomFenceGateBlock extends FenceGateBlock {
    public CustomFenceGateBlock(Block block) {
        this(Properties.copy(block));
    }
    public CustomFenceGateBlock(Properties properties) {
        super(properties);
    }
    public CustomFenceGateBlock(Material material, SoundType sound, float hardness, float resistance) {
        this(Properties.of(material)
                .sound(sound)
                .strength(hardness, resistance)
        );
    }
    public CustomFenceGateBlock(Material material, SoundType sound, float hardness, float resistance, boolean tool) {
        this(Properties.of(material)
                .sound(sound)
                .strength(hardness, resistance)
                .requiresCorrectToolForDrops()
        );
    }
}