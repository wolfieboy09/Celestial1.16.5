package fishcute.celestial;

import fishcute.celestialmain.version.independent.Instances;

public class Celestial
{
	public static final String MOD_ID = "celestial";

	public static void init() {
		initInstances();
	}

	public static void initInstances() {
		Instances.renderSystem = new VRenderSystem();
	}
}
