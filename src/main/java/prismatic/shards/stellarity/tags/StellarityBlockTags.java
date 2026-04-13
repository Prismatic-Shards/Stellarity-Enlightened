package prismatic.shards.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityBlockTags {
	TagKey<Block> DUNE_SPEED_BLOCKS = id("dune_speed_blocks");
	TagKey<Block> DIRT = id("dirt");
	TagKey<Block> WORLDGEN_REPLACEABLE_STALACTITE = id("worldgen/replaceable/stalactite");
	TagKey<Block> WORLDGEN_REPLACEABLE_END_STONE = id("worldgen/replaceable/end_stone");
	TagKey<Block> WORLDGEN_REPLACEABLE_AMETHYST_FOREST_BOTTOM = id("worldgen/replaceable/amethyst_forest/bottom");

	static TagKey<Block> id(String id) {
		return TagKey.create(Registries.BLOCK, Stellarity.id(id));
	}


}
