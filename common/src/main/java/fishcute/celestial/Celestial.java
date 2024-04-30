package fishcute.celestial;

import fishcute.celestialmain.util.Util;
import fishcute.celestialmain.version.independent.Instances;

public class Celestial
{
	public static final String MOD_ID = "celestial";

	public static void init() {
		Util.log("Initializing Celestial");
		VInstances.setInstances();
	}
}
