package xyz.kohara.stellarity.mixin.enchantments;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.registry.StellarityEnchantments;

@Mixin(BowItem.class)
public class BowItemMixin {
    @Inject(method = "releaseUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private void levitationShot(ItemStack itemStack, Level level, LivingEntity livingEntity, int i, CallbackInfo ci, @Local AbstractArrow abstractArrow) {
        int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(StellarityEnchantments.LEVITATION_SHOT, itemStack);
        var random = level.random;
        float chance = (15f + 10 * enchantmentLevel) / (100 + 12 * enchantmentLevel);

        if (random.nextFloat() > chance) return;

        // 4s (80ticks) to 7s (140ticks), with additional 10 ticks per enchantment level
        int time = random.nextIntBetweenInclusive(80 + 10 * enchantmentLevel, 140 + 10 * enchantmentLevel);
        if (abstractArrow instanceof Arrow arrow) {
            arrow.addEffect(new MobEffectInstance(MobEffects.LEVITATION, time));
        }
    }
}
