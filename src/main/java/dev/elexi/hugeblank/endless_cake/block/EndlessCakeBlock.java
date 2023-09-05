package dev.elexi.hugeblank.endless_cake.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.event.GameEvent;

public class EndlessCakeBlock extends CakeBlock {
    /*protected static final VoxelShape[] BITES_TO_SHAPE;

    static {
        BITES_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(3.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(5.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(7.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(9.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(11.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(13.0, 0.0, 1.0, 15.0, 8.0, 15.0)
        };
    }*/

    public EndlessCakeBlock(AbstractBlock.Settings settings) {
        super(Settings.copy(Blocks.CAKE).strength(0.5F).sounds(BlockSoundGroup.WOOL));
        this.setDefaultState(this.stateManager.getDefaultState());
    }

    // Modified from net.minecraft.block.DragonEggBlock
    public static void teleport(BlockState state, WorldAccess world, BlockPos pos) {
        WorldBorder worldBorder = world.getWorldBorder();
        Random random = world.getRandom();
        for (int i = 0; i < 1000; ++i) {
            BlockPos blockPos = pos.add(random.nextInt(16) - random.nextInt(16), random.nextInt(8) - random.nextInt(8), random.nextInt(16) - random.nextInt(16));
            if (world.getBlockState(blockPos).isAir() && worldBorder.contains(blockPos)) {
                if (world.isClient()) {
                    for(int j = 0; j < 128; ++j) {
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

    /*public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BITES_TO_SHAPE[state.get(BITES)];
    }*/

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (itemStack.isIn(ItemTags.CANDLES)) {
            Block block = Block.getBlockFromItem(item);
            if (block instanceof CandleBlock) {
                if (!player.isCreative()) {
                    itemStack.decrement(1);
                }
                world.playSound(null, pos, SoundEvents.BLOCK_CAKE_ADD_CANDLE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.setBlockState(pos, EndlessCandleCakeBlock.getCandleCakeFromCandle(this, (CandleBlock) block));
                world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                return ActionResult.SUCCESS;
            }
        }

        if (world.isClient) {
            if (tryEat(world, pos, state, player).isAccepted()) {
                return ActionResult.SUCCESS;
            }
            if (itemStack.isEmpty()) {
                return ActionResult.CONSUME;
            }
        }
        return tryEat(world, pos, state, player);
    }

    // Modified from net.minecraft.block.CakeBlock
    protected static ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
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

