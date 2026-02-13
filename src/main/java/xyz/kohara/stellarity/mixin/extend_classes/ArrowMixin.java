package xyz.kohara.stellarity.mixin.extend_classes;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.interface_injection.ExtArrow;
import xyz.kohara.stellarity.registry.StellarityMobEffects;


@Mixin(Arrow.class)
public abstract class ArrowMixin extends Projectile implements ExtArrow {

    public ArrowMixin(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    @Unique
    private int levitationShot = 0;

    @Unique
    private boolean voidShot = false;

    @Override
    public int stellarity$levitationShot() {
        return levitationShot;
    }

    @Override
    public void stellarity$setLevitationShot(int levitationShot) {
        this.levitationShot = levitationShot;
    }

    @Override
    public void stellarity$setVoidShot(boolean voidShot) {
        this.voidShot = voidShot;
    }

    @Override
    public boolean stellarity$voidShot() {
        return voidShot;
    }

    @Inject(method = "doPostHurtEffects", at = @At("TAIL"))
    private void applyEffects(CallbackInfo ci, @Local(argsOnly = true) LivingEntity livingEntity) {
        if (levitationShot > 0 && random.nextFloat() < 0.1 * levitationShot) {
            // 4s (80ticks) to 7s (140ticks), with additional 10 ticks per enchantment level
            int time = random.nextIntBetweenInclusive(70 + 10 * levitationShot, 130 + 10 * levitationShot);
            livingEntity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, time));
        }
        if (voidShot) {
            livingEntity.addEffect(new MobEffectInstance(StellarityMobEffects.VOIDED, 160));
        }
    }
}
