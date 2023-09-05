package dev.elexi.hugeblank.endless_cake.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.event.GameEvent;

public class EndlessCakeBlock extends CakeBlock {

    public EndlessCakeBlock(AbstractBlock.Settings settings) {
        super(Settings.copy(Blocks.CAKE).strength(0.5F).sounds(BlockSoundGroup.WOOL));
        this.setDefaultState(this.stateManager.getDefaultState().with(BITES, 0));
    }

    @Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        teleport(state, world, pos);
    }

    // Modified from net.minecraft.block.DragonEggBlock
    public static void teleport(BlockState state, WorldAccess world, BlockPos pos) {
        WorldBorder worldBorder = world.getWorldBorder();
        Random random = world.getRandom();
        for (int i = 0; i < 1000; ++i) {
            BlockPos blockPos = pos.add(random.nextInt(16) - random.nextInt(16), random.nextInt(8) - random.nextInt(8), random.nextInt(16) - random.nextInt(16));
            if (world.getBlockState(blockPos).isAir() && worldBorder.contains(blockPos)) {
                if (world.isClient()) {
                    for (int j = 0; j < 128; ++j) {
                        double d = random.nextDouble();
                        float f = (random.nextFloat() - 0.5F) * 0.2F;
                        float g = (random.nextFloat() - 0.5F) * 0.2F;
                        float h = (random.nextFloat() - 0.5F) * 0.2F;
                        double e = MathHelper.lerp(d, blockPos.getX(), pos.getX()) + (random.nextDouble() - 0.5) + 0.5;
                        double k = MathHelper.lerp(d, blockPos.getY(), pos.getY()) + random.nextDouble() - 0.5;
                        double l = MathHelper.lerp(d, blockPos.getZ(), pos.getZ()) + (random.nextDouble() - 0.5) + 0.5;
                        world.addParticle(ParticleTypes.PORTAL, e, k, l, f, g, h);
                    }
                } else {
                    world.setBlockState(blockPos, state.with(BITES, 0), 2);
                    world.removeBlock(pos, false);
                }
                return;
            }
        }
    }

    // Modified from net.minecraft.block.CakeBlock
    public static ActionResult tryEatEndlessCake(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        } else {
            player.incrementStat(Stats.EAT_CAKE_SLICE);
            player.getHungerManager().add(2, 0.1F);
            int i = state.get(BITES);
            world.emitGameEvent(player, GameEvent.EAT, pos);
            if (i < 6) {
                world.setBlockState(pos, state.with(BITES, i + 1), 3);
            } else {
                teleport(state, world, pos);
            }
            return ActionResult.SUCCESS;
        }
    }
}

