package me.spacemex.spacelib.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import java.util.function.Function;

public class CustomGrassBlock extends GlassBlock {
    public CustomGrassBlock(Material material, Function<Properties, Properties> properties) {
        super(properties.apply(Properties.of(material))
                .noOcclusion()
                .isValidSpawn(CustomGrassBlock::never)
                .isRedstoneConductor(CustomGrassBlock::never)
                .isSuffocating(CustomGrassBlock::never)
                .isViewBlocking(CustomGrassBlock::never)
        );
    }
    public CustomGrassBlock(Material material, SoundType sound, float hardness, float resistance) {
        super(Properties.of(material)
                .sound(sound)
                .strength(hardness, resistance)
                .noOcclusion()
                .isValidSpawn(CustomGrassBlock::never)
                .isRedstoneConductor(CustomGrassBlock::never)
                .isSuffocating(CustomGrassBlock::never)
                .isViewBlocking(CustomGrassBlock::never)
        );
    }
    public CustomGrassBlock(Material material, SoundType sound, float hardness, float resistance, boolean tool) {
        super(Properties.of(material)
                .sound(sound)
                .strength(hardness, resistance)
                .requiresCorrectToolForDrops()
                .noOcclusion()
                .isValidSpawn(CustomGrassBlock::never)
                .isRedstoneConductor(CustomGrassBlock::never)
                .isSuffocating(CustomGrassBlock::never)
                .isViewBlocking(CustomGrassBlock::never)
        );
    }
    private static boolean never(BlockState state, BlockGetter level, BlockPos pos, EntityType<?> entity) {
        return false;
    }
    private static boolean never(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }
}