package xyz.kohara.stellarity.registry;


import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.effect.*;
import net.minecraft.core.Holder;

public class StellarityMobEffects {

	public static final Holder<MobEffect> VOIDED = register("voided", new VoidedEffect());
	public static final Holder<MobEffect> JINX = register("jinx", new JinxEffect());

	public static final Holder<MobEffect> BRITTLE = register("brittle", new BrittleEffect());
	public static final Holder<MobEffect> CREATIVE_SHOCK = register("creative_shock", new CreativeShockEffect());
	public static final Holder<MobEffect> FROSTBURN = register("frostburn", new FrostburnEffect());
	public static final Holder<MobEffect> PRISMATIC_INFERNO = register("prismatic_inferno", new PrismaticInfernoEffect());


	public static Holder<MobEffect> register(String name, MobEffect effect) {
		return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Stellarity.id(name), effect);
	}

	public static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Mob Effects");
	}
}
