package me.spacemex.spacelib.item;

import me.spacemex.spacelib.enums.MiningSize;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ExcavatorItem extends DiggerItem implements Vanishable {
    private int depth;
    private int radius;
    private int durability;

    public ExcavatorItem(Tier tier, MiningSize miningSize, int durability, @Nullable CreativeModeTab creativeModeTab){
        super(1,2.8F, tier, BlockTags.MINEABLE_WITH_SHOVEL,new Properties().durability(durability).tab(creativeModeTab));
        this.depth= miningSize.getDepth();
        this.radius= miningSize.getRadius();
        this.durability=durability;
    }

    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        if (itemStack.getMaxDamage() - itemStack.getDamageValue() <= 0 ){
            return -1f;
        }
        return super.getDestroySpeed(itemStack,blockState);
    }
    @Override
    public boolean mineBlock(ItemStack excavatorStack, Level level, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity) {
        if (level.isClientSide || blockState.getDestroySpeed(level, blockPos) == 0.0F) {
            return true;
        }
        HitResult pick = livingEntity.pick(20D, 1F, false);
        if (!(pick instanceof BlockHitResult && blockState.is(BlockTags.MINEABLE_WITH_SHOVEL))) {
            return super.mineBlock(excavatorStack, level, blockState, blockPos, livingEntity);
        }
        this.findAndBreakNearBlocks(pick, blockPos, excavatorStack, level, livingEntity);
        return super.mineBlock(excavatorStack, level, blockState, blockPos, livingEntity);
    }
    public void findAndBreakNearBlocks(HitResult pick, BlockPos blockPos, ItemStack excavatorStack, Level level, LivingEntity livingEntity) {
        if (!(livingEntity instanceof ServerPlayer player)) return;
        var size = (radius / 2);
        var offset = size - 1;
        Direction direction = ((BlockHitResult) pick).getDirection();
        var boundingBox = switch (direction) {
            case DOWN, UP -> new BoundingBox(blockPos.getX() - size, blockPos.getY() - (direction == Direction.UP ? depth - 1 : 0), blockPos.getZ() - size, blockPos.getX() + size, blockPos.getY() + (direction == Direction.DOWN ? depth - 1 : 0), blockPos.getZ() + size);
            case NORTH, SOUTH -> new BoundingBox(blockPos.getX() - size, blockPos.getY() - size + offset, blockPos.getZ() - (direction == Direction.SOUTH ? depth - 1 : 0), blockPos.getX() + size, blockPos.getY() + size + offset, blockPos.getZ() + (direction == Direction.NORTH ? depth - 1 : 0));
            case WEST, EAST -> new BoundingBox(blockPos.getX() - (direction == Direction.EAST ? depth - 1 : 0), blockPos.getY() - size + offset, blockPos.getZ() - size, blockPos.getX() + (direction == Direction.WEST ? depth - 1 : 0), blockPos.getY() + size + offset, blockPos.getZ() + size);
        };
        int damage = 0;
        Iterator<BlockPos> iterator = BlockPos.betweenClosedStream(boundingBox).iterator();
        Set<BlockPos> removedPos = new HashSet<>();
        while (iterator.hasNext()) {
            var pos = iterator.next();
            if (damage >= (excavatorStack.getMaxDamage() - excavatorStack.getDamageValue() - 1)) {
                break;
            }
            BlockState targetState = level.getBlockState(pos);
            if (pos == blockPos || removedPos.contains(pos) || !canDestroyBlock(targetState, level, pos) || !isCorrectToolForDrops(excavatorStack,targetState)) {
                continue;
            }
            removedPos.add(pos);
            level.destroyBlock(pos, false, livingEntity);
            if (!player.isCreative()) {
                boolean correctToolForDrops = excavatorStack.isCorrectToolForDrops(targetState);
                if (correctToolForDrops) {
                    targetState.spawnAfterBreak((ServerLevel) level, pos, excavatorStack,false);
                    List<ItemStack> drops = Block.getDrops(targetState, (ServerLevel) level, pos, level.getBlockEntity(pos), livingEntity, excavatorStack);
                    drops.forEach(e -> Block.popResourceFromFace(level, pos, ((BlockHitResult) pick).getDirection(), e));
                }
            }
            damage ++;
        }
        if (damage != 0 && !player.isCreative()) {
            excavatorStack.hurtAndBreak(damage, livingEntity, (livingEntityX) -> {
                livingEntityX.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }
    }
    private boolean canDestroyBlock(BlockState blockState, Level level, BlockPos blockPos) {
        if (blockState.getDestroySpeed(level, blockPos) <= 0) {
            return false;
        }
        return level.getBlockEntity(blockPos) == null && blockState.is(BlockTags.MINEABLE_WITH_SHOVEL);
    }

}
