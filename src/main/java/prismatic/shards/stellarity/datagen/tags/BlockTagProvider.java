package prismatic.shards.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.tags.StellarityBlockTags;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.tags.BlockTags.*;
import static net.minecraft.world.level.block.Blocks.*;
import static prismatic.shards.stellarity.registry.StellarityBlocks.*;
import static prismatic.shards.stellarity.tags.StellarityBlockTags.*;


public class BlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
	public BlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}


	@SafeVarargs
	public final TagAppender<Block, Block> addTags(TagKey<Block> tagKey, TagKey<Block>... tags) {
		var appender = this.valueLookupBuilder(tagKey);
		for (var tag : tags) {
			appender.forceAddTag(tag);
		}
		return appender;
	}


	@Override
	protected void addTags(HolderLookup.@NonNull Provider provider) {
		addTags(StellarityBlockTags.DIRT).add(ENDER_DIRT, ENDER_GRASS_BLOCK, ROOTED_ENDER_DIRT);
		addTags(MINEABLE_WITH_SHOVEL).add(ENDER_DIRT_PATH, ENDER_DIRT, ENDER_GRASS_BLOCK, ROOTED_ENDER_DIRT);
		addTags(MINEABLE_WITH_PICKAXE).add(ALTAR_OF_THE_ACCURSED);
		addTags(NEEDS_DIAMOND_TOOL).add(ALTAR_OF_THE_ACCURSED);
		addTags(DUNE_SPEED_BLOCKS, BlockTags.SAND, CONCRETE_POWDER).add(GRAVEL, SUSPICIOUS_GRAVEL);
		addTags(SOUL_FIRE_BASE_BLOCKS).add(BEDROCK, CRYING_OBSIDIAN).setReplace(false);
		addTags(WORLDGEN_REPLACEABLE_STALACTITE, BlockTags.TERRACOTTA, BASE_STONE_NETHER, BASE_STONE_OVERWORLD).add(END_STONE, Blocks.DIRT, TUFF, BASALT, MOSS_BLOCK, SMOOTH_BASALT, DEEPSLATE, COBBLED_DEEPSLATE, GRASS_BLOCK, SCULK, PACKED_MUD, SMOOTH_SANDSTONE, SNOW_BLOCK, Blocks.ICE, WHITE_CONCRETE_POWDER, CALCITE, MOSS_BLOCK, WARPED_WART_BLOCK);
		addTags(ANIMALS_SPAWNABLE_ON).add(ENDER_GRASS_BLOCK);
		addTags(WORLDGEN_REPLACEABLE_END_STONE).add(END_STONE);
		addTags(WORLDGEN_REPLACEABLE_AMETHYST_FOREST_BOTTOM).add(END_STONE, CALCITE, TUFF, GRASS_BLOCK, Blocks.DIRT, ENDER_DIRT, ENDER_GRASS_BLOCK, DIORITE);
		addTags(WORLDGEN_INVALID_AMETHYST_GEODE, BlockTags.AIR).add(OBSIDIAN);
		addTags(WORLDGEN_REPLACEABLE_GRASS_BLOCK).add(GRASS_BLOCK, ENDER_GRASS_BLOCK);
		addTags(SUPPORTS_VEGETATION).add(ENDER_DIRT, ENDER_GRASS_BLOCK);
	}
}
