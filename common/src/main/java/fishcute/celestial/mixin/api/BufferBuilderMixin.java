package fishcute.celestial.mixin.api;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.math.Matrix4f;
import fishcute.celestialmain.api.math.DrawMode;
import fishcute.celestialmain.api.minecraft.wrappers.IBufferBuilderWrapper;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BufferBuilder.class)
public class BufferBuilderMixin implements IBufferBuilderWrapper {
    @Override
    public void celestial$beginTriangleFan() {
        BufferBuilder self = (BufferBuilder)(Object) this;
        self.begin(DrawMode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);;
    }

    @Override
    public void celestial$beginObject() {
        BufferBuilder self = (BufferBuilder)(Object) this;
        self.begin(DrawMode.QUADS, DefaultVertexFormat.POSITION_TEX);
    }

    @Override
    public void celestial$beginColorObject() {
        BufferBuilder self = (BufferBuilder)(Object) this;
        self.begin(DrawMode.QUADS, DefaultVertexFormat.POSITION_COLOR);
    }

    @Override
    public void celestial$vertex(Object matrix4f, float x, float y, float z, float r, float g, float b, float a) {
        BufferBuilder self = (BufferBuilder)(Object) this;
        self.vertex((Matrix4f) matrix4f, x, y, z).color(r, g, b, a).endVertex();
    }

    @Override
    public void celestial$vertexUv(Object matrix4f, float x, float y, float z, float u, float v, float r, float g, float b, float a) {
        BufferBuilder self = (BufferBuilder)(Object) this;
        self.vertex((Matrix4f) matrix4f, x, y, z).uv(u, v).color(r, g, b, a).endVertex();
    }

    @Override
    public void celestial$upload() {
        BufferBuilder self = (BufferBuilder)(Object) this;
        self.end();
        BufferUploader.end(self);
    }

//    @Override
//    public IBufferBuilderWrapper celestial$init() {
//        return null;
//    }
}