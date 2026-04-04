package prismatic.shards.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityBlockTags {
	TagKey<Block> DUNE_SPEED_BLOCKS = id("dune_speed_blocks");
	TagKey<Block> DIRT = id("dirt");

	static TagKey<Block> id(String id) {
		return TagKey.create(Registries.BLOCK, Stellarity.id(id));
	}


}
