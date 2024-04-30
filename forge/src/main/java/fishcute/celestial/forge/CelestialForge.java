package fishcute.celestial.forge;


import com.mojang.blaze3d.platform.InputConstants;
import fishcute.celestial.Celestial;
import fishcute.celestialmain.util.ClientTick;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;

@Mod(Celestial.MOD_ID)
public class CelestialForge {
    private static KeyMapping reloadSky = new KeyMapping(
            "key.reload_sky",
            InputConstants.getKey("key.keyboard.f10").getValue(),
            "key.categories.misc"
    );

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            while (reloadSky.consumeClick()) {
                ClientTick.onReload();
            }
        }
    }
    public CelestialForge() {
        Celestial.init();
        ClientRegistry.registerKeyBinding(reloadSky);
        MinecraftForge.EVENT_BUS.register(CelestialForge.class);
    }
}