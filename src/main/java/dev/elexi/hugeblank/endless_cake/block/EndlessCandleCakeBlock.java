package dev.elexi.hugeblank.endless_cake.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CandleBlock;
import net.minecraft.block.CandleCakeBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.HashMap;
import java.util.Map;

public class EndlessCandleCakeBlock extends CandleCakeBlock {
    private final EndlessCakeBlock cake;
    private static final Map<EndlessCakeBlock, Map<CandleBlock, EndlessCandleCakeBlock>> COMBINER = new HashMap<>();

    public EndlessCandleCakeBlock(Block candle, EndlessCakeBlock cake, Settings settings) {
        super(candle, settings);
        this.cake = cake;
        if (COMBINER.containsKey(cake)) {
            COMBINER.get(cake).put((CandleBlock) candle, this);
        } else {
            Map<CandleBlock, EndlessCandleCakeBlock> candleCakeBlockMap = new HashMap<>();
            candleCakeBlockMap.put((CandleBlock) candle, this);
            COMBINER.put(cake, candleCakeBlockMap);
        }

    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(cake);
    }
}

