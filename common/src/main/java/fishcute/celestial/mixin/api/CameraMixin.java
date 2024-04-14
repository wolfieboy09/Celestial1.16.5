package fishcute.celestial.mixin.api;

import fishcute.celestialmain.api.minecraft.wrappers.ICameraWrapper;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Camera.class)
public class CameraMixin implements ICameraWrapper {
    @Override
    public boolean celestial$doesFogBlockSky() {
        Camera self = (Camera)(Object) this;
        Fluid type = self.getFluidInCamera().getType();
        return type != Fluids.FLOWING_LAVA || type != Fluids.LAVA;
    }

    @Override
    public boolean celestial$doesMobEffectBlockSky() {
        Entity entity = Minecraft.getInstance().getCameraEntity();
        if (!(entity instanceof LivingEntity)) {
            return false;
        } else {
            LivingEntity livingEntity = (LivingEntity) entity;
            return livingEntity.hasEffect(MobEffects.BLINDNESS);
        }
    }
}