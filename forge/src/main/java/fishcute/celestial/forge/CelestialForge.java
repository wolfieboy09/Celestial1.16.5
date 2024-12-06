package fishcute.celestial.forge;


import com.mojang.blaze3d.platform.InputConstants;
import fishcute.celestial.Celestial;
import fishcute.celestialmain.util.ClientTick;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

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

    // THIS IS A TEST TO SEE IF IT WORKS AND WHAT IT RETURNS
    private static boolean printed = false;
    @SubscribeEvent
    public static void onEvent(TickEvent.PlayerTickEvent event) {
        if (!printed) {
            printed = true;
            PlayerStages.printTagsForPlayer(event.player);
        }
    }


    public CelestialForge() {
        try {
            Class.forName("kotlin.Unit");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Kotlin is not loaded. Please install Kotlin for Forge. \n https://modrinth.com/mod/kotlin-for-forge");
        }
        Celestial.init();
        ClientRegistry.registerKeyBinding(reloadSky);
        MinecraftForge.EVENT_BUS.register(CelestialForge.class);
    }
}