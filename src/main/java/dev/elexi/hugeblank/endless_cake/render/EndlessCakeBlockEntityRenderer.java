package dev.elexi.hugeblank.endless_cake.render;

import dev.elexi.hugeblank.endless_cake.block.EndlessCakeBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;

import static net.minecraft.block.CakeBlock.BITES;

@Environment(EnvType.CLIENT)
public class EndlessCakeBlockEntityRenderer <T extends EndlessCakeBlockEntity> implements BlockEntityRenderer<T> {
    @Override
    public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        float bites = entity.getCachedState().get(BITES);
        this.renderVertices(matrix4f, vertexConsumers.getBuffer(this.getLayer()), (bites * 0.125F)+0.064F, (bites * 0.125F)+0.064F);
    }

    private void renderVertices(Matrix4f model, VertexConsumer vertices, float x1, float x2) {
            vertices.vertex(model, x1, (float) 0.0, (float) 0.0625).next();
            vertices.vertex(model, x2, (float) 0.0, (float) 0.9375).next();
            vertices.vertex(model, x2, (float) 0.5, (float) 0.9375).next();
            vertices.vertex(model, x1, (float) 0.5, (float) 0.0625).next();
    }

    protected RenderLayer getLayer() {
        return RenderLayer.getEndPortal();
    }

    public EndlessCakeBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    }
}
