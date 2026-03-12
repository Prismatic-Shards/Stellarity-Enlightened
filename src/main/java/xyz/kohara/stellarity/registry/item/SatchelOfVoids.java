package xyz.kohara.stellarity.registry.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class SatchelOfVoids extends Item {
	public SatchelOfVoids(Properties properties) {
		super(properties);
	}

	public static Properties properties() {
		return new Properties();
	}

	@Override
	public InteractionResult useOn(UseOnContext useOnContext) {
		var prev = super.useOn(useOnContext);
		var player = useOnContext.getPlayer();
		if (player == null || !player.level().isClientSide()) return prev;
		player.displayClientMessage(Component.literal("Not implemented yet! Thanks for finding this :3"), true);
		return InteractionResult.SUCCESS;
	}
}
