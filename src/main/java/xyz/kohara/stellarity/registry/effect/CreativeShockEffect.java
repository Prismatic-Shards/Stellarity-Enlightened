package xyz.kohara.stellarity.registry.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import xyz.kohara.stellarity.registry.StellarityParticles;


public class CreativeShockEffect extends MobEffect {

	public static boolean extremeCreativeShock() {
		return true;
	}

	public CreativeShockEffect() {
		super(MobEffectCategory.HARMFUL, 0xFD3DB5, StellarityParticles.CREATIVE_SHOCK);
	}
}