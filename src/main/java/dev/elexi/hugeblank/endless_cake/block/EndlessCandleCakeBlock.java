package dev.elexi.hugeblank.endless_cake.block;

import com.google.common.collect.Maps;
import dev.elexi.hugeblank.endless_cake.Init;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CandleCakeBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.Map;

public class EndlessCandleCakeBlock extends CandleCakeBlock {
    public static final Map<Block, EndlessCandleCakeBlock> CANDLES_TO_ENDLESS_CANDLE_CAKES;

    public EndlessCandleCakeBlock(Block candle, AbstractBlock.Settings settings) {
        super(candle, settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LIT, false));
        CANDLES_TO_ENDLESS_CANDLE_CAKES.put(candle, this);
    }

    public static BlockState getEndlessCandleCakeFromCandle(Block candle) {
        return CANDLES_TO_ENDLESS_CANDLE_CAKES.get(candle).getDefaultState();
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(Init.ENDLESS_CAKE);
    }

    static {
        CANDLES_TO_ENDLESS_CANDLE_CAKES = Maps.newHashMap();
    }
}

