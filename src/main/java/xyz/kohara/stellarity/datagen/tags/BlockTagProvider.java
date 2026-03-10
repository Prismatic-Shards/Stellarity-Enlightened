package xyz.kohara.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import xyz.kohara.stellarity.tags.StellarityBlockTags;
import xyz.kohara.stellarity.registry.StellarityBlocks;

import java.util.concurrent.CompletableFuture;
//? >= 1.21.9 {
/*import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
*///? }

public class BlockTagProvider extends FabricTagProvider.BlockTagProvider {
	public BlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	//? >= 1.21.9 {
	/*public TagAppender<Block, Block> getOrCreateTagBuilder(TagKey<Block> tagKey) {
		return this.valueLookupBuilder(tagKey);
	}
	*///?}


	@Override
	protected void addTags(HolderLookup.Provider provider) {
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_SHOVEL).add(StellarityBlocks.ENDER_DIRT_PATH, StellarityBlocks.ENDER_DIRT, StellarityBlocks.ENDER_GRASS_BLOCK, StellarityBlocks.ROOTED_ENDER_DIRT);

		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(StellarityBlocks.ALTAR_OF_THE_ACCURSED);

		getOrCreateTagBuilder(StellarityBlockTags.DUNE_SPEED_BLOCKS).forceAddTag(BlockTags.SAND)/*? > 1.21 >> '.add'*//*.forceAddTag(BlockTags.CONCRETE_POWDER)*/.add(Blocks.GRAVEL, Blocks.SUSPICIOUS_GRAVEL/*? 1.20.1 >> ')' */,
			Blocks.RED_CONCRETE_POWDER,
			Blocks.ORANGE_CONCRETE_POWDER,
			Blocks.YELLOW_CONCRETE_POWDER,
			Blocks.LIME_CONCRETE_POWDER,
			Blocks.GREEN_CONCRETE_POWDER,
			Blocks.LIGHT_BLUE_CONCRETE_POWDER,
			Blocks.CYAN_CONCRETE_POWDER,
			Blocks.BLUE_CONCRETE_POWDER,
			Blocks.MAGENTA_CONCRETE_POWDER,
			Blocks.PINK_CONCRETE_POWDER,
			Blocks.PURPLE_CONCRETE_POWDER,
			Blocks.BROWN_CONCRETE_POWDER,
			Blocks.WHITE_CONCRETE_POWDER,
			Blocks.GRAY_CONCRETE_POWDER,
			Blocks.LIGHT_GRAY_CONCRETE_POWDER,
			Blocks.BLACK_CONCRETE_POWDER
		);
		
	}
}
