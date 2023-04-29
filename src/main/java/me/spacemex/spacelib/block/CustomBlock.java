package me.spacemex.spacelib.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

import java.util.function.Function;

public class CustomBlock  extends Block {
    public CustomBlock(Material material, Function<Properties, Properties> properties) {
        super(properties.apply(Properties.of(material)));
    }
    public CustomBlock(Material material, SoundType sound, float hardness, float resistance) {
        super(Properties.of(material)
                .sound(sound)
                .strength(hardness, resistance)
        );
    }
    public CustomBlock(Material material, SoundType sound, float hardness, float resistance, boolean tool) {
        super(Properties.of(material)
                .sound(sound)
                .strength(hardness, resistance)
                .requiresCorrectToolForDrops()
        );
    }
}
