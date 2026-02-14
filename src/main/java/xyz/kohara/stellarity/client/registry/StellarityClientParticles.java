package xyz.kohara.stellarity.client.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.CritParticle;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.client.registry.particle.CriticalStrikeParticle;
import xyz.kohara.stellarity.registry.StellarityParticles;

@Environment(EnvType.CLIENT)
public class StellarityClientParticles {
    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Client Particles");

        ParticleFactoryRegistry.getInstance().register(StellarityParticles.CRITICAL_STRIKE, CriticalStrikeParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(StellarityParticles.CREATIVE_SHOCK, CriticalStrikeParticle.Provider::new);
    }
}
