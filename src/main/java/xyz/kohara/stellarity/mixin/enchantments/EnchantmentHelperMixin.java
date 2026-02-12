//? > 1.21 {
package xyz.kohara.stellarity.mixin.enchantments;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityEnchantments;
import xyz.kohara.stellarity.registry.StellarityParticles;
import xyz.kohara.stellarity.registry.StellaritySounds;

@Debug(export = true)
@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @Unique
    private static final RandomSource random = RandomSource.create();


    @WrapMethod(method = "method_60620")
    private static void criticalStrike(ServerLevel serverLevel, Entity entity, DamageSource damageSource, Holder<Enchantment> holder, int i, EnchantedItemInUse enchantedItemInUse, Operation<Void> original) {


        if (holder.is(StellarityEnchantments.CRITICAL_STRIKE) && entity instanceof LivingEntity target && target.level() instanceof ServerLevel level && random.nextFloat() < i * 0.1f) {
            target.hurt(target.getLastDamageSource(), target.lastHurt * 2);
            target.playSound(StellaritySounds.CRITICAL_STRIKE);
            float height = target.getBbHeight() * 0.7f;
            float width = target.getBbWidth() * 0.7f;
            level.sendParticles(StellarityParticles.CRITICAL_STRIKE, target.getX(), target.getY(0.5), target.getZ(), 50, width, height, width, 0.3);
            return;
        }

        original.call(serverLevel, entity, damageSource, holder, i, enchantedItemInUse);
    }
}
//? }
