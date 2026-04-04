package prismatic.shards.stellarity.mixin.enchantments;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import prismatic.shards.stellarity.key.StellarityEnchantments;

@Mixin(ProjectileWeaponItem.class)
public class ProjectileWeaponItemMixin {

	@WrapOperation(method = "shoot", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ProjectileWeaponItem;createProjectile(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;Z)Lnet/minecraft/world/entity/projectile/Projectile;"))
	private Projectile levitationShot(ProjectileWeaponItem instance, Level level, LivingEntity livingEntity, ItemStack itemStack, ItemStack itemStack2, boolean b, Operation<Projectile> original) {
		var projectile = original.call(instance, level, livingEntity, itemStack, itemStack2, b);

		if (projectile instanceof Arrow arrow) {
			for (var entry : itemStack.getEnchantments().entrySet()) {
				if (entry.getKey().is(StellarityEnchantments.LEVITATION_SHOT)) {
					var random = level.getRandom();
					var enchantmentLevel = entry.getIntValue();

					arrow.stellarity$setLevitationShot(enchantmentLevel);
				}
				if (entry.getKey().is(StellarityEnchantments.VOID_SHOT)) {
					arrow.stellarity$setVoidShot(true);
				}
			}
		}

		return projectile;
	}
}
