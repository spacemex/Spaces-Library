package me.spacemex.spacelib.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("all")
public class TreeFellerItem extends DiggerItem implements Vanishable {
    private  int durability;
    public TreeFellerItem(Tier tier, int durability, CreativeModeTab creativeModeTab) {
        super(1, 2.8F, tier, BlockTags.LOGS, new Properties().durability(durability).tab(creativeModeTab));
        this.durability = durability;
    }
    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        if (itemStack.getMaxDamage() - itemStack.getDamageValue() <= 1) {
            return -1f;
        }
        return super.getDestroySpeed(itemStack, blockState);
    }
    @Deprecated
    private boolean actualIsCorrectToolForDrops(BlockState state) {
        int i = this.getTier().getLevel();
        if (i < 3 && state.is(BlockTags.NEEDS_DIAMOND_TOOL)) {
            return false;
        } else if (i < 2 && state.is(BlockTags.NEEDS_IRON_TOOL)) {
            return false;
        } else {
            return (i >= 1 || !state.is(BlockTags.NEEDS_STONE_TOOL)) && state.is(BlockTags.LOGS);
        }
    }
    @Override
    public boolean mineBlock(ItemStack fellerStack, Level level, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity) {
        if (level.isClientSide || blockState.getDestroySpeed(level, blockPos) == 0.0F) {
            return true;
        }
        HitResult pick = livingEntity.pick(20D, 1F, false);
        if (!(pick instanceof BlockHitResult && blockState.is(BlockTags.MINEABLE_WITH_AXE))) {
            return super.mineBlock(fellerStack, level, blockState, blockPos, livingEntity);
        }
        this.findAndBreakNearBlocks(pick, blockPos, fellerStack, level, livingEntity);
        return super.mineBlock(fellerStack, level, blockState, blockPos, livingEntity);
    }
    public void findAndBreakNearBlocks(HitResult pick, BlockPos blockPos, ItemStack fellerStack, Level level, LivingEntity livingEntity) {
        if (!(livingEntity instanceof ServerPlayer player)) return;
        Set<BlockPos> visited = new HashSet<>();
        Set<BlockPos> toVisit = new HashSet<>();
        toVisit.add(blockPos);
        int damage = 0;
        while (!toVisit.isEmpty()) {
            var currentPos = toVisit.iterator().next();
            toVisit.remove(currentPos);
            if (!visited.add(currentPos)) {
                continue;
            }
            var targetState = level.getBlockState(currentPos);
            if (!canDestroyb(targetState, level, currentPos) || !isCorrectToolForDrops(fellerStack, targetState)) {
                continue;
            }
            if (currentPos != blockPos) {
                if (damage >= (fellerStack.getMaxDamage() - fellerStack.getDamageValue())) {
                    break;
                }
                if (!player.isCreative()) {
                    boolean correctToolForDrops = fellerStack.isCorrectToolForDrops(targetState);
                    if (correctToolForDrops) {
                        targetState.spawnAfterBreak((ServerLevel) level, blockPos, fellerStack,false);
                        List<ItemStack> drops = Block.getDrops(targetState, (ServerLevel) level, blockPos, level.getBlockEntity(blockPos), livingEntity, fellerStack);
                        Direction popDirection = ((BlockHitResult) pick).getDirection();
                        for (ItemStack drop: drops){
                            Block.popResourceFromFace(level,currentPos,popDirection,drop);
                            level.destroyBlock(currentPos, false);
                        }
                    }
                }
                damage++;
            }
            for (Direction dir : Direction.values()) {
                var neighborPos = currentPos.relative(dir);
                if (!visited.contains(neighborPos) && level.getBlockState(neighborPos).is(targetState.getBlock())) {
                    toVisit.add(neighborPos);
                }
            }
        }
    }
    private boolean canDestroyb(BlockState blockState, Level level, BlockPos blockPos) {
        if (blockState.getDestroySpeed(level, blockPos) <= 0) {
            return false;
        }
        return level.getBlockEntity(blockPos) == null && blockState.is(BlockTags.LOGS);
    }
}