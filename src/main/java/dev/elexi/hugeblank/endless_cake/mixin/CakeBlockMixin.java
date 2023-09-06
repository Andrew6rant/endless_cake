package dev.elexi.hugeblank.endless_cake.mixin;

import dev.elexi.hugeblank.endless_cake.block.EndlessCakeBlock;
import dev.elexi.hugeblank.endless_cake.block.EndlessCandleCakeBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.Block.getBlockFromItem;

@Mixin(CakeBlock.class)
public class CakeBlockMixin {
    @Inject(method = "onUse(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;Lnet/minecraft/util/hit/BlockHitResult;)Lnet/minecraft/util/ActionResult;",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/block/CakeBlock;tryEat(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/player/PlayerEntity;)Lnet/minecraft/util/ActionResult;"),
        cancellable = true)
    private void endless_cake$injectTryEatEndlessCake(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (state.getBlock() instanceof EndlessCakeBlock) {
            cir.setReturnValue(EndlessCakeBlock.tryEatEndlessCake(world, pos, state, player));
        }
    }

    @Inject(method = "onUse(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;Lnet/minecraft/util/hit/BlockHitResult;)Lnet/minecraft/util/ActionResult;",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z"),
        cancellable = true)
    private void endless_cake$injectSetEndlessCandleCakeBlockState(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (state.getBlock() instanceof EndlessCakeBlock) {
            Item candleItem = player.getStackInHand(hand).getItem();
            Block candleBlock = getBlockFromItem(candleItem);
            world.setBlockState(pos, EndlessCandleCakeBlock.getEndlessCandleCakeFromCandle(candleBlock));
            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            player.incrementStat(Stats.USED.getOrCreateStat(candleItem));
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
}
