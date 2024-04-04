package fishcute.celestial.mixin.api;

import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.math.Matrix4f;
import fishcute.celestialmain.api.math.DrawMode;
import fishcute.celestialmain.api.minecraft.wrappers.IShaderInstanceWrapper;
import fishcute.celestialmain.api.minecraft.wrappers.IVertexBufferWrapper;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(VertexBuffer.class)
public class VertexBufferMixin implements IVertexBufferWrapper {
    @Override
    public void celestial$bind() {
        ((VertexBuffer)(Object) this).bind();
    }

    //TODO: I Have no clue if this will work
    @Override
    public void celestial$drawWithShader(Object matrix, Object projectionMatrix, IShaderInstanceWrapper shader) {
        VertexBuffer self = ((VertexBuffer)(Object) this);

        Matrix4f mat = ((Matrix4f) projectionMatrix);
        mat.multiply((Matrix4f) matrix);

        self.bind();
        self.draw(mat, DrawMode.QUADS);
    }
}