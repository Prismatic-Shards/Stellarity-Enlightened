package xyz.kohara.stellarity.registry.effect;

//? if >= 1.21.9 {

import net.minecraft.server.level.ServerLevel;
	//?}

import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import xyz.kohara.stellarity.registry.StellarityDamageTypes;

public class FrostburnEffect extends MobEffect {
	public FrostburnEffect() {
		super(MobEffectCategory.HARMFUL, 0x10222f);
	}

	public boolean applyEffectTick(/*? > 1.21.9 {*/ ServerLevel serverLevel,  /*? } */LivingEntity livingEntity, int amplifier) {
		if (livingEntity.level().isClientSide()) return false;
		float damage = 1f;
		if (livingEntity./*? 1.21.1 >> 'is'*//*getType().*/is(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES)) damage *= 2;
		livingEntity./*? 1.21.1 {*//*hurt(*//*? } else {*/hurtServer(serverLevel, /*? } */
			livingEntity.damageSources().source(StellarityDamageTypes.FROSTBURN), damage);

		return true;
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return duration % 20 == 0;
	}
}