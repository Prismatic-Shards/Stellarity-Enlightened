package prismatic.shards.stellarity.client.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.client.registry.particle.CriticalStrikeParticle;
import prismatic.shards.stellarity.registry.StellarityParticles;

@Environment(EnvType.CLIENT)
public interface StellarityClientParticles {
	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Client Particles");

		ParticleProviderRegistry.getInstance().register(StellarityParticles.CRITICAL_STRIKE, CriticalStrikeParticle.Provider::new);
		ParticleProviderRegistry.getInstance().register(StellarityParticles.CREATIVE_SHOCK, CriticalStrikeParticle.Provider::new);
	}
}
