//? 1.20.1 {

package xyz.kohara.stellarity.mixin.enchantments;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.interface_injection.ExtAbstractArrow;
import xyz.kohara.stellarity.registry.StellarityEnchantments;

@Mixin(BowItem.class)
public class BowItemMixin {
    @Inject(method = "releaseUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private void levitationShot(ItemStack itemStack, Level level, LivingEntity livingEntity, int i, CallbackInfo ci, @Local AbstractArrow abstractArrow) {
        int levitationShot = EnchantmentHelper.getItemEnchantmentLevel(StellarityEnchantments.LEVITATION_SHOT, itemStack);
        boolean voidShot = EnchantmentHelper.getItemEnchantmentLevel(StellarityEnchantments.VOID_SHOT, itemStack) > 0;

        if (abstractArrow instanceof ExtAbstractArrow arrow) {
            if (levitationShot > 0) arrow.stellarity$setLevitationShot(levitationShot);
            if (voidShot) arrow.stellarity$setVoidShot(true);
        }
    }
}

//? }