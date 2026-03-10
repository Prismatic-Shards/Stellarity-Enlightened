package xyz.kohara.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import xyz.kohara.stellarity.Stellarity;

public class StellarityItemTags {
	public static final TagKey<Item> FISHES = bind("fishes");
	public static final TagKey<Item> ELYTRA_ENCHANTABLE = bind("enchantable/elytra");
	public static final TagKey<Item> RANGED_ENCHANTABLE = bind("enchantable/ranged");


	private static TagKey<Item> bind(String id) {
		return TagKey.create(Registries.ITEM, Stellarity.id(id));
	}

}
