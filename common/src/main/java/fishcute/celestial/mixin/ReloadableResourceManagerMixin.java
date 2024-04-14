package fishcute.celestial.mixin;

import fishcute.celestialmain.util.ClientTick;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.resources.ReloadInstance;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.minecraft.util.Unit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(ReloadableResourceManager.class)
public class ReloadableResourceManagerMixin {
    @Inject(method = "reload", at = @At("RETURN"))
    private void reload(Executor executor, Executor executor2, List<PackResources> list, CompletableFuture<Unit> completableFuture, CallbackInfoReturnable<CompletableFuture<Unit>> cir) {
        ClientTick.onReload();
    }
}