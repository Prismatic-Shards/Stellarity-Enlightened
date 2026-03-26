package xyz.kohara.stellarity.mixin.extend_classes;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.interface_injection.ExtAbstractArrow;
import xyz.kohara.stellarity.registry.StellarityMobEffects;


import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.ValueInput;


@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin extends Projectile implements ExtAbstractArrow {
	public AbstractArrowMixin(EntityType<? extends Projectile> entityType, Level level) {
		super(entityType, level);
	}


	@Inject(method = "doPostHurtEffects", at = @At("TAIL"))
	private void applyEffects(CallbackInfo ci, @Local(argsOnly = true) LivingEntity livingEntity) {
		int levitationShot = stellarity$levitationShot();
		boolean voidShot = stellarity$voidShot();
		if (levitationShot > 0 && random.nextFloat() < 0.1 * levitationShot) {

			int time = random.nextIntBetweenInclusive(70 + 10 * levitationShot, 130 + 10 * levitationShot);
			livingEntity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, time));
		}
		if (voidShot) {
			livingEntity.addEffect(new MobEffectInstance(StellarityMobEffects.VOIDED, 160));
		}
	}
}
