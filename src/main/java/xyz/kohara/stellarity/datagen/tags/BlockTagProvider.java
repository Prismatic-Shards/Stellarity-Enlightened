package xyz.kohara.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import xyz.kohara.stellarity.tags.StellarityBlockTags;
import xyz.kohara.stellarity.registry.StellarityBlocks;

import java.util.concurrent.CompletableFuture;
//? >= 1.21.9 {
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
//? }

public class BlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
	public BlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	//? >= 1.21.9 {
	public TagAppender<Block, Block> getOrCreateTagBuilder(TagKey<Block> tagKey) {
		return this.valueLookupBuilder(tagKey);
	}
	//?}


	@Override
	protected void addTags(HolderLookup.Provider provider) {
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_SHOVEL).add(StellarityBlocks.ENDER_DIRT_PATH, StellarityBlocks.ENDER_DIRT, StellarityBlocks.ENDER_GRASS_BLOCK, StellarityBlocks.ROOTED_ENDER_DIRT);

		getOrCreateTagBuilder(StellarityBlockTags.DIRT).add(StellarityBlocks.ENDER_DIRT, StellarityBlocks.ENDER_GRASS_BLOCK, StellarityBlocks.ROOTED_ENDER_DIRT);

		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(StellarityBlocks.ALTAR_OF_THE_ACCURSED);

		getOrCreateTagBuilder(StellarityBlockTags.DUNE_SPEED_BLOCKS).forceAddTag(BlockTags.SAND).forceAddTag(BlockTags.CONCRETE_POWDER).add(Blocks.GRAVEL, Blocks.SUSPICIOUS_GRAVEL);

		getOrCreateTagBuilder(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(Blocks.BEDROCK, Blocks.CRYING_OBSIDIAN).setReplace(false);

	}
}
