package dev.elexi.hugeblank.endless_cake.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import static dev.elexi.hugeblank.endless_cake.Init.ENDLESS_CAKE_BLOCK_ENTITY;
import static net.minecraft.block.CakeBlock.BITES;

public class EndlessCakeBlockEntity extends BlockEntity {
    protected EndlessCakeBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public EndlessCakeBlockEntity(BlockPos pos, BlockState state) {
        //if (state.get(BITES) != 0) {
            this(ENDLESS_CAKE_BLOCK_ENTITY, pos, state);
        //}
    }
}
