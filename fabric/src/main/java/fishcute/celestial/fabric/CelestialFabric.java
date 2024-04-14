package fishcute.celestial.fabric;


import com.mojang.blaze3d.platform.InputConstants;
import fishcute.celestial.Celestial;
import fishcute.celestialmain.util.ClientTick;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;

public class CelestialFabric implements ModInitializer {

    public static KeyMapping reloadSky;
    @Override
    public void onInitialize() {
        Celestial.init();

        reloadSky = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.reload_sky",
                InputConstants.getKey("key.keyboard.f10").getValue(),
                "key.categories.misc"
        ));

        ClientTickEvents.END_CLIENT_TICK.register((endTick -> {
            while (reloadSky.consumeClick()) {
                ClientTick.onReloadKey();
            }
        }));
    }
}