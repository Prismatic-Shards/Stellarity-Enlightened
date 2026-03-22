package xyz.kohara.stellarity.registry.effect;

import net.minecraft.tags.EntityTypeTags;
//? > 1.21.9 {
import net.minecraft.server.level.ServerLevel;
	//? }
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class PrismaticInfernoEffect extends MobEffect {
	public PrismaticInfernoEffect() {
		super(MobEffectCategory.HARMFUL, 0xff4d00);
	}


	@Override

	public boolean applyEffectTick(/*? > 1.21.9 >> ' Li'*/ ServerLevel serverLevel, LivingEntity livingEntity, int amplifier) {

		Level level = livingEntity.level();
		if (level.isClientSide()) return false;
		boolean isInDaylight = level.canSeeSky(livingEntity.blockPosition()) &&
			level.dimension() == Level.OVERWORLD &&
			(!level.isRaining() && !level.isThundering() && level./*? 1.21.1 {*/ /*isDay() *//*? } else { */ isBrightOutside() /*? } */);

		boolean isUndead = livingEntity./*? 1.21.1 >> 'is'*//*getType().*/is(EntityTypeTags.UNDEAD);

		float damage = 1f;
		if (isUndead) damage *= 2;
		if (isInDaylight) damage *= 2;

		livingEntity./*? 1.21.1 {*/ /*hurt( *//*? } else { */ hurtServer(serverLevel, /*? }*/livingEntity.damageSources().inFire(), damage);
		livingEntity.setRemainingFireTicks(25);
		return true;
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return duration % 20 == 0;
	}
}