package fishcute.celestial.mixin;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexBuffer;
import fishcute.celestialmain.api.minecraft.wrappers.*;
import fishcute.celestialmain.sky.CelestialSky;
import fishcute.celestialmain.version.independent.VersionLevelRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
    @Shadow
    private VertexBuffer skyBuffer;
    @Shadow
    private VertexBuffer darkBuffer;

    @Shadow
    private ClientLevel level;

    @Inject(method = "drawStars", at = @At("HEAD"), cancellable = true)
    private void drawStars(BufferBuilder bufferBuilder, CallbackInfo ci) {
        bufferBuilder.begin(7, DefaultVertexFormat.POSITION);
        ci.cancel();
    }
    @Inject(method = "renderSky", at = @At("HEAD"), cancellable = true)
    private void renderSky(PoseStack matrices, float tickDelta, CallbackInfo info) {
        if (CelestialSky.doesDimensionHaveCustomSky()) {
            info.cancel();

            VersionLevelRenderer.renderLevel((Object) matrices.last().pose(),
                    (IPoseStackWrapper) matrices,
                    (IVertexBufferWrapper) skyBuffer,
                    (IVertexBufferWrapper) darkBuffer,
                    (ICameraWrapper) Minecraft.getInstance().gameRenderer.getMainCamera(),
                    (ILevelWrapper) level,
                    tickDelta
            );
        }
    }
}