package me.spacemex.spacelib.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import java.util.function.Supplier;

public class CustomStairsBlock extends StairBlock {
    public CustomStairsBlock(Block block) {
        this(block, Properties.copy(block));
    }
    public CustomStairsBlock(Block block, Properties properties) {
        this(block::defaultBlockState, properties);
    }
    public CustomStairsBlock(Supplier<BlockState> state, Properties properties) {
        super(state, properties);
    }
    public CustomStairsBlock(Supplier<BlockState> state, Material material, SoundType sound, float hardness, float resistance) {
        this(state, Properties.of(material)
                .sound(sound)
                .strength(hardness, resistance)
        );
    }
    public CustomStairsBlock(Supplier<BlockState> state, Material material, SoundType sound, float hardness, float resistance, boolean tool) {
        this(state, Properties.of(material)
                .sound(sound)
                .strength(hardness, resistance)
                .requiresCorrectToolForDrops()
        );
    }
}