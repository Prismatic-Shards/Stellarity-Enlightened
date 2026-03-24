package xyz.kohara.stellarity.registry.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import org.jspecify.annotations.NonNull;

public class SatchelOfVoids extends Item {
	public SatchelOfVoids(Properties properties) {
		super(properties);
	}

	public static final Properties PROPERTIES = new Properties().stacksTo(1
	);


	@Override
	public @NonNull InteractionResult useOn(@NonNull UseOnContext useOnContext) {
		var prev = super.useOn(useOnContext);
		var player = useOnContext.getPlayer();
		if (player == null || !player.level().isClientSide()) return prev;

		Component msg = Component.literal("Not implemented yet! Thanks for finding this :3");


		player.sendOverlayMessage(msg);

		return InteractionResult.SUCCESS;
	}
}
