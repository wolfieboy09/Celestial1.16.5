package fishcute.celestial;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexBuffer;
import fishcute.celestialmain.api.minecraft.IRenderSystem;
import fishcute.celestialmain.api.minecraft.wrappers.IResourceLocationWrapper;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.level.biome.Biome;

public class VRenderSystem implements IRenderSystem {
    public void setShaderFogStart(float start) {
        RenderSystem.fogStart(start);
        RenderSystem.setupNvFogDistance();
    }
    public void setShaderFogEnd(float end) {
        RenderSystem.fogEnd(end);
        RenderSystem.setupNvFogDistance();
    }
    public int getBiomeFogColor(Biome biome) {
        return biome.getSpecialEffects().getFogColor();
    }
    public void levelFogColor() {
        FogRenderer.levelFogColor();
    }
    public void setupNoFog() {
        FogRenderer.setupNoFog();
    }
    public void defaultBlendFunc() {
        RenderSystem.defaultBlendFunc();
    }

    public void depthMask(boolean enable) {
        RenderSystem.depthMask(enable);
    }
    public void setShaderColor(float f, float g, float h, float a) {
        //RenderSystem.setShaderColor(f, g, h, a);
    }
    public void clearColor(float f, float g, float h, float a) {
        RenderSystem.clearColor(f, g, h, a);
    }
    public void unbindVertexBuffer() {
        VertexBuffer.unbind();
    }
    public void toggleBlend(boolean enable) {
        if (enable)
            RenderSystem.enableBlend();
        else
            RenderSystem.disableBlend();
    }
    public void defaultBlendFunction() {
        RenderSystem.defaultBlendFunc();
    }
    public void setShaderPositionColor() {
        //RenderSystem.setShader(GameRenderer::getPositionColorShader);
    }
    public void setShaderPositionTex() {
        //RenderSystem.setShader(GameRenderer::getPositionTexShader);
    }

    public void toggleTexture(boolean texture) {
        // Apparently unused in 1.19.4+
    }
    public void blendFuncSeparate() {
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
    }

    public void setShaderTexture(int i, IResourceLocationWrapper j) {
        //RenderSystem.setShaderTexture(i, (ResourceLocation) j);
    }
}