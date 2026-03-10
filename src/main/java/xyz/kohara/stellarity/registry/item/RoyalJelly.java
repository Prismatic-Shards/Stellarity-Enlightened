package xyz.kohara.stellarity.registry.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

//? < 1.21.9 {

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

//? }

public class RoyalJelly extends Item {
	public RoyalJelly(Properties properties) {
		super(properties);
	}

	public static Properties properties() {
		return new Properties().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
		super.finishUsingItem(itemStack, level, livingEntity);
		if (livingEntity instanceof ServerPlayer serverPlayer) {
			CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, itemStack);
			serverPlayer.awardStat(Stats.ITEM_USED.get(this));
		}

		if (!level.isClientSide()) {
			var effects = livingEntity.getActiveEffects().stream().toList();

			for (var effect : effects) {
				if (effect.getEffect()/*? > 1.21 >> '.get' *//*.value()*/.getCategory() == MobEffectCategory.HARMFUL) {
					livingEntity.removeEffect(effect.getEffect());
				}
			}
		}

		if (itemStack.isEmpty()) {
			return new ItemStack(Items.GLASS_BOTTLE);
		} else {
			if (livingEntity instanceof Player player && !(player.getAbilities().instabuild)) {
				ItemStack itemStack2 = new ItemStack(Items.GLASS_BOTTLE);
				if (!player.getInventory().add(itemStack2)) {
					player.drop(itemStack2, false);
				}
			}

			return itemStack;
		}
	}

	@Override
	public int getUseDuration(ItemStack itemStack/*? > 1.21 >> ') {'*//*, LivingEntity livingEntity*/) {
		return 40;
	}

	//? < 1.21.9 {

	@Override
	public UseAnim getUseAnimation(ItemStack itemStack) {
		return UseAnim.DRINK;
	}

	@Override
	public SoundEvent getDrinkingSound() {
		return SoundEvents.HONEY_DRINK;
	}

	@Override
	public SoundEvent getEatingSound() {
		return SoundEvents.HONEY_DRINK;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
		return ItemUtils.startUsingInstantly(level, player, interactionHand);
	}
	//? }
}
