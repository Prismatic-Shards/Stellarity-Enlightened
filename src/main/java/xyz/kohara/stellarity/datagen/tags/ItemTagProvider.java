package xyz.kohara.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;

import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;
import xyz.kohara.stellarity.tags.StellarityItemTags;
import xyz.kohara.stellarity.registry.StellarityItems;

import java.util.concurrent.CompletableFuture;

import net.minecraft.tags.ItemTags;


import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;


public class ItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
	public ItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
		super(output, completableFuture, blockTagProvider);
	}

	public ItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(output, completableFuture);
	}


	public TagAppender<Item, Item> getOrCreateTagBuilder(TagKey<Item> tagKey) {
		return this.valueLookupBuilder(tagKey);
	}


	@Override
	public void addTags(HolderLookup.Provider provider) {
		getOrCreateTagBuilder(StellarityItemTags.FISHES).add(
			StellarityItems.AMETHYST_BUDFISH,
			StellarityItems.BUBBLEFISH,
			StellarityItems.CRIMSON_TIGERFISH,
			StellarityItems.ENDER_KOI,
			StellarityItems.FLAREFIN_KOI,
			StellarityItems.CRYSTAL_HEARTFISH

		);

		getOrCreateTagBuilder(ItemTags.FISHES).addTag(StellarityItemTags.FISHES);
		getOrCreateTagBuilder(StellarityItemTags.ELYTRA_ENCHANTABLE).add(Items.ELYTRA);

		getOrCreateTagBuilder(StellarityItemTags.RANGED_ENCHANTABLE)
			.forceAddTag(ItemTags.BOW_ENCHANTABLE)
			.forceAddTag(ItemTags.CROSSBOW_ENCHANTABLE);
	}
}
