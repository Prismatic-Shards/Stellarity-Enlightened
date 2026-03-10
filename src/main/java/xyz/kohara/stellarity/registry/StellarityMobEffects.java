package xyz.kohara.stellarity.registry;


import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.effect.*;
//? 1.20.1 {

//? } else {
/*import net.minecraft.core.Holder;
 *///? }

public class StellarityMobEffects {

	public static final /*? 1.20.1 { */ MobEffect/*? } else { */ /*Holder<MobEffect> *//*? }*/  VOIDED = register("voided", new VoidedEffect());
	public static final /*? 1.20.1 { */ MobEffect/*? } else { */ /*Holder<MobEffect> *//*? }*/ JINX = register("jinx", new JinxEffect());

	public static final /*? 1.20.1 { */ MobEffect/*? } else { */ /*Holder<MobEffect> *//*? }*/ BRITTLE = register("brittle", new BrittleEffect());
	public static final /*? 1.20.1 { */ MobEffect/*? } else { */ /*Holder<MobEffect> *//*? }*/ CREATIVE_SHOCK = register("creative_shock", new CreativeShockEffect());
	public static final /*? 1.20.1 { */ MobEffect/*? } else { */ /*Holder<MobEffect> *//*? }*/ FROSTBURN = register("frostburn", new FrostburnEffect());
	public static final /*? 1.20.1 { */ MobEffect/*? } else { */ /*Holder<MobEffect> *//*? }*/ PRISMATIC_INFERNO = register("prismatic_inferno", new PrismaticInfernoEffect());


	public static /*? 1.20.1 { */MobEffect/*? } else { */ /*Holder<MobEffect> *//*? }*/ register(String name, MobEffect effect) {
		return Registry./*? 1.20.1 { */register/*? } else { */ /*registerForHolder*//*? }*/(BuiltInRegistries.MOB_EFFECT, Stellarity.id(name), effect);
	}

	public static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Mob Effects");
	}
}
