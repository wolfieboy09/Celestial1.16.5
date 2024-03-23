package fishcute.celestial.forge;


import fishcute.celestial.Celestial;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Celestial.MOD_ID)
public class CelestialForge {
    public CelestialForge() {
        Celestial.init();
    }
}