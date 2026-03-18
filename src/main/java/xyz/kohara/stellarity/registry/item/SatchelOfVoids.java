package xyz.kohara.stellarity.registry.item;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import xyz.kohara.stellarity.client.registry.gui.screen.ConfigScreen;
import xyz.kohara.stellarity.utils.StellarityConfig;

public class SatchelOfVoids extends Item {
	public SatchelOfVoids(Properties properties) {
		super(properties);
	}

	public static final Properties PROPERTIES = new Properties().stacksTo(1
	);


	@Override
	public InteractionResult useOn(UseOnContext useOnContext) {
		var prev = super.useOn(useOnContext);
		var player = useOnContext.getPlayer();
		if (player == null || !player.level().isClientSide()) return prev;
		Minecraft.getInstance().setScreen(new ConfigScreen(new StellarityConfig(true, true, true, true, 200)));
		player.displayClientMessage(Component.literal("Not implemented yet! Thanks for finding this :3"), true);
		return InteractionResult.SUCCESS;
	}
}
