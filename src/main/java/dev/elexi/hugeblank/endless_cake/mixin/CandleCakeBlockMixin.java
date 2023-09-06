package dev.elexi.hugeblank.endless_cake.mixin;

import dev.elexi.hugeblank.endless_cake.Init;
import dev.elexi.hugeblank.endless_cake.block.EndlessCakeBlock;
import dev.elexi.hugeblank.endless_cake.block.EndlessCandleCakeBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CandleCakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.Block.dropStacks;

@Mixin(CandleCakeBlock.class)
class CandleCakeBlockMixin {

	@Inject(method = "onUse(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;Lnet/minecraft/util/hit/BlockHitResult;)Lnet/minecraft/util/ActionResult;",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/block/CakeBlock;tryEat(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/player/PlayerEntity;)Lnet/minecraft/util/ActionResult;"),
		cancellable = true)
	private void endless_cake$injectTryEatEndlessCandleCake(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
		if (state.getBlock() instanceof EndlessCandleCakeBlock) {
			ActionResult actionResult = EndlessCakeBlock.tryEatEndlessCake(world, pos, Init.ENDLESS_CAKE.getDefaultState(), player);
			if (actionResult.isAccepted()) {
				dropStacks(state, world, pos);
			}
			cir.setReturnValue(actionResult);
		}
	}
}
