package xyz.kohara.stellarity.mixin.armor_effects;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.kohara.stellarity.registry.StellarityItems;

@Mixin(Player.class)
public abstract class PlayerMixin extends Avatar {

	protected PlayerMixin(EntityType<? extends LivingEntity> type, Level level) {
		super(type, level);
	}

	@Shadow
	public abstract @Nullable SlotAccess getSlot(int slot);

	@Shadow
	public abstract Inventory getInventory();

	@Inject(method = "hurtServer", at = @At("HEAD"))
	private void fullSetEffects(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
		SlotAccess headSlot = getSlot(Player.ARMOR_SLOT_OFFSET + 3);
		SlotAccess chestSlot = getSlot(Player.ARMOR_SLOT_OFFSET + 2);
		SlotAccess legsSlot = getSlot(Player.ARMOR_SLOT_OFFSET + 1);
		SlotAccess feetSlot = getSlot(Player.ARMOR_SLOT_OFFSET);
		if (headSlot == null || chestSlot == null || legsSlot == null || feetSlot == null) return;

		ItemStack head = headSlot.get();
		ItemStack chest = chestSlot.get();
		ItemStack legs = legsSlot.get();
		ItemStack feet = feetSlot.get();

		if (head.isEmpty() || chest.isEmpty() || legs.isEmpty() || feet.isEmpty()) return;

		var pos = this.position();

		var hostiles = level.getEntitiesOfClass(Mob.class, new AABB(pos.add(-5, -3, -5), pos.add(5, 3, 5)), (e) -> e.getType().getCategory().equals(MobCategory.MONSTER) || e.target == this);

		int size = hostiles.size();
		if (head.is(StellarityItems.SHULKER_HELMET) && chest.is(StellarityItems.SHULKER_CHESTPLATE) && legs.is(StellarityItems.SHULKER_LEGGINGS) && feet.is(StellarityItems.SHULKER_BOOTS)) {
			if (size >= 4) {
				addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 30 * 20));
			} else if (size < 3) {
				var moreHostiles = level.getEntitiesOfClass(Mob.class, new AABB(pos.add(-32, -8, -32), pos.add(32, 8, 32)), (e) -> e.getType().getCategory().equals(MobCategory.MONSTER) || e.target == this);
				var moreSize = moreHostiles.size();
				for (int i = size, j = 0; i < 3; i++, j++) {
					if (j >= size) break;
					hostiles.add(moreHostiles.get(j));
				}
			}

			var totalSize = hostiles.size();

			for (int i = 0; i < 3; i++) {
				if (totalSize == 0 || random.nextBoolean()) break;
				var shulkerBullet = new ShulkerBullet(level, this, hostiles.get(i % totalSize), null);
				shulkerBullet.setPos(position().add(0, getEyeHeight() * 0.6, 0));
				level.addFreshEntity(shulkerBullet);
			}
		}
	}


}
