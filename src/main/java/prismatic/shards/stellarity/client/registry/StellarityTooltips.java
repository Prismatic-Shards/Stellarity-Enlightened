package prismatic.shards.stellarity.client.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionContents;
import prismatic.shards.stellarity.Stellarity;

import java.util.Optional;

@Environment(EnvType.CLIENT)
public class StellarityTooltips {
	public static final Component EMPTY_LINE = Component.literal("");
	public static final Component STELLARITY = Component.translatable("Stellarity").withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.ITALIC);


	public static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Tooltips");

		ItemTooltipCallback.EVENT.register((
			itemStack, unused, unused2, list
		) -> {
			Item item = itemStack.getItem();
			boolean isStellarityPotion = item instanceof PotionItem &&
				Optional.ofNullable(itemStack.get(DataComponents.POTION_CONTENTS)).flatMap(PotionContents::potion).map(holder -> BuiltInRegistries.POTION.getKey(holder.value())).map((location) -> location.getNamespace().equals(Stellarity.MOD_ID)).orElse(false);

			if (!(BuiltInRegistries.ITEM.getKey(item).getNamespace().equals(Stellarity.MOD_ID) || isStellarityPotion)) {
				return;
			}


			list.add(EMPTY_LINE);
			list.add(STELLARITY);
		});
	}
}
