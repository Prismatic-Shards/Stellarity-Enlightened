package prismatic.shards.stellarity.registry;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityParticles {
	SimpleParticleType CRITICAL_STRIKE = register("critical_strike", FabricParticleTypes.simple());
	SimpleParticleType CREATIVE_SHOCK = register("creative_shock", FabricParticleTypes.simple(false));

	static SimpleParticleType register(String name, SimpleParticleType particle) {
		return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Stellarity.id(name), particle);
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Particles");
	}
}
