package xyz.kohara.stellarity.registry;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.CritParticle;
import xyz.kohara.stellarity.Stellarity;

public class StellarityClientParticles {
    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Client Particles");

        ParticleFactoryRegistry.getInstance().register(StellarityParticles.CRITICAL_STRIKE, CritParticle.Provider::new);
    }
}
