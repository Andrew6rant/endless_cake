package dev.elexi.hugeblank.endless_cake;

import dev.elexi.hugeblank.endless_cake.render.EndlessCakeBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class InitClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Init.ENDLESS_CAKE, RenderLayer.getCutout());
        BlockEntityRendererFactories.register(Init.ENDLESS_CAKE_BLOCK_ENTITY, EndlessCakeBlockEntityRenderer::new);
    }
}
