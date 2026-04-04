package prismatic.shards.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import prismatic.shards.stellarity.Stellarity;

public class StellarityBlockTags {
	public static final TagKey<Block> DUNE_SPEED_BLOCKS = bind("dune_speed_blocks");
	public static final TagKey<Block> DIRT = bind("dirt");

	private static TagKey<Block> bind(String id) {
		return TagKey.create(Registries.BLOCK, Stellarity.id(id));
	}


}
