package dev.elexi.hugeblank.endless_cake.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

import static dev.elexi.hugeblank.endless_cake.Init.ENDLESS_CAKE_BLOCK_ENTITY;

public class EndlessCakeBlockEntity extends BlockEntity {
    protected EndlessCakeBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public EndlessCakeBlockEntity(BlockPos pos, BlockState state) {
        this(ENDLESS_CAKE_BLOCK_ENTITY, pos, state);
    }
}
