package prismatic.shards.stellarity.registry;

import prismatic.shards.stellarity.Stellarity;

public interface StellarityNetworking {
	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Common Networking");
	}
}
