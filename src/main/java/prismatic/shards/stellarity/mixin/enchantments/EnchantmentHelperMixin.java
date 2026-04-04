package prismatic.shards.stellarity.mixin.enchantments;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import prismatic.shards.stellarity.key.StellarityEnchantments;
import prismatic.shards.stellarity.registry.StellarityParticles;
import prismatic.shards.stellarity.registry.StellaritySounds;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
	@Unique
	private static final RandomSource random = RandomSource.create();


	@WrapMethod(method = "lambda$doPostAttackEffectsWithItemSourceOnBreak$0")
	private static void stellarityPostEnchantments(ServerLevel serverLevel, Entity entity, DamageSource damageSource, Holder<Enchantment> holder, int i, EnchantedItemInUse enchantedItemInUse, Operation<Void> original) {


		if (holder.is(StellarityEnchantments.CRITICAL_STRIKE) && entity instanceof LivingEntity target && target.level() instanceof ServerLevel level && random.nextFloat() < i * 0.1f && target.getLastDamageSource() != null) {
			target.hurtServer(serverLevel, target.getLastDamageSource(), target.lastHurt * 2);
			target.playSound(StellaritySounds.CRITICAL_STRIKE);
			float height = target.getBbHeight() * 0.7f;
			float width = target.getBbWidth() * 0.7f;
			level.sendParticles(StellarityParticles.CRITICAL_STRIKE, target.getX(), target.getY(0.5), target.getZ(), 50, width, height, width, 0.3);
			return;
		}

		original.call(serverLevel, entity, damageSource, holder, i, enchantedItemInUse);
	}
}
