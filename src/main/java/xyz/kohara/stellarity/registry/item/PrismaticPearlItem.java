package xyz.kohara.stellarity.registry.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import net.minecraft.world.item.ItemStack;


import net.minecraft.world.InteractionResult;
import xyz.kohara.stellarity.registry.entity.ThrownPrismaticPearl;


import xyz.kohara.stellarity.registry.StellaritySounds;


public class PrismaticPearlItem extends Item {
	public PrismaticPearlItem(Properties properties) {
		super(properties);
	}

	public static final Properties PROPERTIES = new Item.Properties().stacksTo(1);


	@Override
	public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
		ItemStack itemStack = player.getItemInHand(interactionHand);

		if (level instanceof ServerLevel serverLevel) {
			player.getCooldowns().addCooldown(itemStack, 5 * 20);

			var pearl = new ThrownPrismaticPearl(level, player);
			pearl.setItem(itemStack);
			pearl.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F * 1.25F, 1.0F);
			level.addFreshEntity(pearl);

			serverLevel.playSound(null, player.blockPosition(), StellaritySounds.PRISMATIC_PEARL_THROW, SoundSource.NEUTRAL);
		}


		return InteractionResult.SUCCESS;

	}
}
