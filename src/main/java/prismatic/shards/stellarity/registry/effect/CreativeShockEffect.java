package prismatic.shards.stellarity.registry.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import prismatic.shards.stellarity.registry.StellarityParticleTypes;


public class CreativeShockEffect extends MobEffect {

	public static boolean extremeCreativeShock() {
		return true;
	}

	public CreativeShockEffect() {
		super(MobEffectCategory.HARMFUL, 0xFD3DB5, StellarityParticleTypes.CREATIVE_SHOCK);
	}
}