package xyz.kohara.stellarity.registry;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import xyz.kohara.stellarity.Stellarity;

public class StellarityParticles {
	public static final SimpleParticleType CRITICAL_STRIKE = register("critical_strike", FabricParticleTypes.simple());
	public static final SimpleParticleType CREATIVE_SHOCK = register("creative_shock", FabricParticleTypes.simple(false));

	public static SimpleParticleType register(String name, SimpleParticleType particle) {
		return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Stellarity.id(name), particle);
	}

	public static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Particles");
	}
}
