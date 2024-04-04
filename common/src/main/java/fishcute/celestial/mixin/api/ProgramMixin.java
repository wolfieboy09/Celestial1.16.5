package fishcute.celestial.mixin.api;

import com.mojang.blaze3d.shaders.Program;
import fishcute.celestialmain.api.minecraft.wrappers.IShaderInstanceWrapper;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Program.class)
public class ProgramMixin implements IShaderInstanceWrapper {
}