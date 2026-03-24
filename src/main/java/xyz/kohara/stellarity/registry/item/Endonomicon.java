package xyz.kohara.stellarity.registry.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;


public class Endonomicon extends Item {
	public Endonomicon(Properties properties) {
		super(properties);
	}

	public static final Properties PROPERTIES = new Properties().stacksTo(1);


	@Override
	public @NonNull InteractionResult use(@NonNull Level level, @NonNull Player player, @NonNull InteractionHand interactionHand) {
		var result = super.use(level, player, interactionHand);
		
		player.sendOverlayMessage(Component.literal("Blame Patchouli for not supplying modern support"));

		return result;
	}
}
