package xyz.kohara.stellarity.registry.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import xyz.kohara.stellarity.Stellarity;
//? > 1.21.10 {
import net.minecraft.server.level.ServerLevel;
 //? }

public class VoidedEffect extends MobEffect {
	public VoidedEffect() {
		super(MobEffectCategory.HARMFUL, 0x6a3885);
		addAttributeModifier(Attributes.MAX_HEALTH, Stellarity.id("voided_effect"), -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
	}


	@Override
	public boolean shouldApplyEffectTickThisTick(int i, int j) {
		return true;
	}


	@Override
	public boolean applyEffectTick(/*? > 1.21.10 >> ' Li'*/ ServerLevel level, LivingEntity livingEntity, int i) {
		if (livingEntity.getHealth() > livingEntity.getMaxHealth()) {
			livingEntity.setHealth(livingEntity.getMaxHealth());
		}


		return super.applyEffectTick(/*? > 1.21.10 >> ' li' */ level, livingEntity, i);
	}

}
