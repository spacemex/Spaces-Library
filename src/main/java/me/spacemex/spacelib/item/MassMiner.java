package me.spacemex.spacelib.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluids;

import javax.annotation.Nullable;

public class MassMiner extends Item {
    private  int length;
    private int width;
    private int height;
    private TagKey<Block> breakableTag;
    public MassMiner(int length, int width, int height,TagKey<Block> breakableTag,@Nullable CreativeModeTab creativeModeTab){
        super(new Properties().tab(creativeModeTab).stacksTo(1));
        this.length=length;
        this.width=width;
        this.height=height;
        this.breakableTag=breakableTag;
    }
    public InteractionResult useOn(UseOnContext context) {
        Direction direction = context.getHorizontalDirection();
        Level worldIn = context.getLevel();
        if (worldIn.isClientSide)
            return InteractionResult.FAIL;
        if (direction == Direction.UP || direction == Direction.DOWN)
            return InteractionResult.FAIL;
        BlockPos breakPos = context.getClickedPos();
        int targetY = breakPos.getY() % 8;
        while (targetY < 0) targetY += 8;
        Player playerIn = context.getPlayer();
        Vec3i facing = direction.getNormal();
        playerIn.playNotifySound(SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 1.5F);
        worldIn.addParticle(ParticleTypes.SMOKE.getType(), breakPos.getX(), breakPos.getY(), breakPos.getZ(), 0.25F,
                0.25F, 0.25F);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = -width; k <= width; k++) {
                    int lengthDelta = i * facing.getX() + k * facing.getZ();
                    int widthDelta = i * facing.getZ() + k * facing.getX();
                    BlockPos targetPos = breakPos.offset(lengthDelta,-targetY +j, widthDelta);
                    BlockState targetBlockState = worldIn.getBlockState(targetPos);
                    if (targetBlockState.is(breakableTag)
                            || targetBlockState.getFluidState().getType().equals(Fluids.LAVA)
                            || targetBlockState.getFluidState().getType().equals(Fluids.FLOWING_LAVA)
                            || targetBlockState.getFluidState().getType().equals(Fluids.WATER)
                            || targetBlockState.getFluidState().getType().equals(Fluids.FLOWING_WATER)){
                        this.placeWoodPillars(worldIn, targetPos, i, j, k);
                    }
                }
            }
        }
        context.getPlayer().awardStat(Stats.ITEM_USED.get(this));
        context.getItemInHand().shrink(1);
        context.getPlayer().getCooldowns().addCooldown(this, 20);
        return InteractionResult.SUCCESS;
    }
    private void placeWoodPillars(Level worldIn, BlockPos pos, int i, int j, int k) {
        if (i != 0 && i % 8 == 0) {
            if (k == -width || k == width) {
                if (j == height - 1) {
                    worldIn.setBlockAndUpdate(pos, Blocks.OAK_PLANKS.defaultBlockState());
                    return;
                }
                worldIn.setBlockAndUpdate(pos, Blocks.OAK_FENCE.defaultBlockState());
                return;
            }
            if (j == height -1) {
                if (k == 0) {
                    worldIn.setBlockAndUpdate(pos, Blocks.GLOWSTONE.defaultBlockState());
                    return;
                }
                worldIn.setBlockAndUpdate(pos, Blocks.STONE_BRICK_SLAB.defaultBlockState()
                        .setValue(BlockStateProperties.SLAB_TYPE, SlabType.TOP));
                return;
            }
        }
        worldIn.removeBlock(pos, false);
    }
}
