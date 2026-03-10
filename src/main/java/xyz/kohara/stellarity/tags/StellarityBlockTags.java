package xyz.kohara.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import xyz.kohara.stellarity.Stellarity;

public class StellarityBlockTags {
	public static final TagKey<Block> DUNE_SPEED_BLOCKS = bind("dune_speed_blocks");

	private static TagKey<Block> bind(String id) {
		return TagKey.create(Registries.BLOCK, Stellarity.id(id));
	}


}
