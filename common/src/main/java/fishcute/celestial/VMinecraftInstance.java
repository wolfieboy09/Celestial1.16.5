package fishcute.celestial;

import com.mojang.datafixers.util.Pair;
import com.mojang.math.Vector3f;
import fishcute.celestialmain.api.minecraft.IMcVector;
import fishcute.celestialmain.api.minecraft.IMinecraftInstance;
import fishcute.celestialmain.api.minecraft.wrappers.IResourceLocationWrapper;
import fishcute.celestialmain.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.CubicSampler;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

public class VMinecraftInstance implements IMinecraftInstance {
    private static final Minecraft minecraft = Minecraft.getInstance();
    public boolean doesLevelExist() {
        return minecraft.level != null;
    }
    public boolean doesPlayerExist() {
        return minecraft.player != null;
    }
    public String getLevelPath() {
        return minecraft.level.dimension().location().getPath();
    }
    public float getTickDelta() {
        return minecraft.getFrameTime();
    }
    public Vector getPlayerEyePosition() {
        return Vector.fromVec(minecraft.player.getEyePosition(getTickDelta()));
    }

    public void sendFormattedErrorMessage(String error, String type, String location) {
        minecraft.player.displayClientMessage(

                Component.nullToEmpty(ChatFormatting.DARK_RED +
                        "[Celestial] " + type +
                        ChatFormatting.GRAY + " at " +
                        ChatFormatting.YELLOW + location +
                        ChatFormatting.GRAY + ": " +
                        ChatFormatting.WHITE + error
                ), false);
    }
    public void sendInfoMessage(String i) {
        minecraft.player.displayClientMessage(Component.nullToEmpty(
                ChatFormatting.DARK_AQUA + "[Celestial] " +
                        ChatFormatting.AQUA + i), false);
    }
    public void sendErrorMessage(String i) {
        minecraft.player.displayClientMessage(
                Component.nullToEmpty(ChatFormatting.DARK_RED +
                        "[Celestial] " +
                        ChatFormatting.RED + i
                ), false);
    }
    public void sendRedMessage(String i) {
        minecraft.player.displayClientMessage(
                Component.nullToEmpty(ChatFormatting.RED + i
                ), false);
    }
    public InputStream getResource(String path) throws IOException {
        return minecraft.getResourceManager().getResource(new ResourceLocation(path)).getInputStream();
    }
    public boolean isGamePaused() {
        return minecraft.isPaused();
    }
    public void sendMessage(String text, boolean actionBar) {
        minecraft.player.displayClientMessage(Component.nullToEmpty(text), actionBar);
    }
    public double getPlayerX() {
        return minecraft.player.getX();
    }
    public double getPlayerY() {
        return minecraft.player.getY();
    }
    public double getPlayerZ() {
        return minecraft.player.getZ();
    }
    public double getRainLevel() {
        return minecraft.level.getRainLevel(getTickDelta());
    }
    public boolean isPlayerInWater() {
        return minecraft.player.isInWater();
    }
    public long getGameTime() {
        return minecraft.level.getGameTime();
    }
    public long getWorldTime() {
        return minecraft.level.dayTime();
    }
    public float getStarBrightness() {
        return minecraft.level.getStarBrightness(getTickDelta());
    }
    public float getTimeOfDay() {
        return minecraft.level.getTimeOfDay(getTickDelta());
    }
    public float getViewXRot() {
        return minecraft.player.getViewXRot(getTickDelta());
    }
    public float getViewYRot() {
        return minecraft.player.getViewYRot(getTickDelta());
    }
    public float getCameraLookVectorTwilight(float h) {
        return minecraft.gameRenderer.getMainCamera().getLookVector().dot(new Vector3f(h, 0.0F, 0.0F));
    }

    public BlockPos getPlayerBlockPosition() {
        return minecraft.player.blockPosition();
    }
    public float getRenderDistance() {
        return minecraft.options.renderDistance;
    }
    public float getMoonPhase() {
        return minecraft.level.getMoonPhase();
    }
    public float getSkyDarken() {
        return minecraft.level.getSkyDarken();
    }
    public float getBossSkyDarken() {
        return minecraft.gameRenderer.getDarkenWorldAmount(getTickDelta());
    }
    public float getSkyFlashTime() {
        return minecraft.level.getSkyFlashTime();
    }
    public float getThunderLevel() {
        return minecraft.level.getThunderLevel(getTickDelta());
    }
    public float getSkyLight() {
        return minecraft.level.getBrightness(LightLayer.SKY, getPlayerBlockPosition());
    }
    public float getBlockLight() {
        return minecraft.level.getBrightness(LightLayer.BLOCK, getPlayerBlockPosition());
    }
    public float getBiomeTemperature() {
        return minecraft.level.getBiome(getPlayerBlockPosition()).getBaseTemperature();
    }
    public float getBiomeDownfall() {
        // TODO: Experiment with this
        return minecraft.level.getBiome(getPlayerBlockPosition()).getDownfall() > 0 ? 1 : 0;
    }
    public boolean getBiomeSnow() {
        return minecraft.level.getBiome(getPlayerBlockPosition()).shouldSnow(minecraft.level, getPlayerBlockPosition());
    }
    public boolean isRightClicking() {
        return minecraft.mouseHandler.isRightPressed();
    }
    public boolean isLeftClicking() {
        return minecraft.mouseHandler.isLeftPressed();
    }
    public IResourceLocationWrapper getMainHandItemKey() {
        return (IResourceLocationWrapper) Registry.ITEM.getKey(minecraft.player.getMainHandItem().getItem());
    }
    public String getMainHandItemNamespace() {
        return ((ResourceLocation) getMainHandItemKey()).getNamespace();
    }
    public String getMainHandItemPath() {
        return ((ResourceLocation) getMainHandItemKey()).getPath();
    }

    // TODO: Experiment with biome stuff working
    HashMap<Biome, Pair<String, String>> biomeNameMap = new HashMap<>();

    public void addToBiomeMap(Biome biome, ResourceKey<Biome> b) {
        biomeNameMap.put(biome,
                new com.mojang.datafixers.util.Pair<>(
                        b.location().getPath() + ":" + b.location().getNamespace(),
                        b.location().getPath()
                ));
    }
    public boolean equalToBiome(IMcVector position, String... name) {
        Biome biome = minecraft.level.getBiome(position == null ? getPlayerBlockPosition() : ((Vector) position).toBlockPos());
        Optional<ResourceKey<Biome>> b = minecraft.level.getBiomeName(position == null ? getPlayerBlockPosition() : ((Vector) position).toBlockPos());

        String biomePath = b.get().location().getPath();
        String biomeNamespace  = b.get().location().getNamespace();

        if (!biomeNameMap.containsKey(biome))
            addToBiomeMap(biome, b.get());
        return Arrays.stream(name).collect(Collectors.toList()).contains(biomeNameMap.get(biome).getFirst())
                || Arrays.stream(name).collect(Collectors.toList()).contains(biomeNameMap.get(biome).getSecond());

        //return Arrays.stream(name).toList().contains(biomeNameMap.get(b.get().location().getPath())) || Arrays.stream(name).toList().contains(biomeNameMap.get(b.value()).getB());
    }
    public double[] getBiomeSkyColor() {
        double[] c = new double[3];
        Util.getRealSkyColor = true;
        Vec3 vec = CubicSampler.gaussianSampleVec3(minecraft.player.position(), (ix, jx, kx) -> {
            return Vec3.fromRGB24((minecraft.level.getBiome(new BlockPos(ix, jx, kx))).getSkyColor());
        });
        Util.getRealSkyColor = false;
        c[0] = vec.x;
        c[1] = vec.y;
        c[2] = vec.z;
        return c;
    }
    public double[] getBiomeFogColor() {
        double[] c = new double[3];
        Util.getRealFogColor = true;
        Vec3 vec = CubicSampler.gaussianSampleVec3(minecraft.player.position(), (ix, jx, kx) -> {
            return Vec3.fromRGB24((minecraft.level.getBiome(new BlockPos(ix, jx, kx))).getFogColor());
        });
        Util.getRealFogColor = false;
        c[0] = vec.x;
        c[1] = vec.y;
        c[2] = vec.z;
        return c;
    }

    public boolean disableFogChanges() {
        return minecraft.cameraEntity.isInWater() || minecraft.cameraEntity.isInLava() || minecraft.player.hasEffect(MobEffects.BLINDNESS);
    }
    public boolean isCameraInWater() {
        return minecraft.cameraEntity.isInWater();
    }
    public double getNightVisionModifier() {
        if (!doesPlayerExist() || !minecraft.player.hasEffect(MobEffects.NIGHT_VISION))
            return 0;
        return GameRenderer.getNightVisionScale(minecraft.player, getTickDelta());
    }
    public boolean isSneaking() {
        return minecraft.player.isShiftKeyDown();
    }

    public float getDarknessFogEffect(float fogStart) {
        // Darkness does not exist in 1.16
        return 0;
    }
    public boolean hasDarkness() {
        // Darkness does not exist in 1.16
        return false;
    }
}
