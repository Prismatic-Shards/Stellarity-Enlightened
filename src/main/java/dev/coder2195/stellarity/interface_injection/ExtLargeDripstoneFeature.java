package dev.coder2195.stellarity.interface_injection;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.LargeDripstoneFeature;

import static dev.coder2195.stellarity.util.ValueUtil.from;

public interface ExtLargeDripstoneFeature extends ExtLargeDripstone {
	static LargeDripstoneFeature apply(LargeDripstoneFeature feature, BlockState blockState) {
		feature.stellarity$setBlockState(blockState);
		return feature;
	}

	static LargeDripstoneFeature applyDefaults(LargeDripstoneFeature config) {
		return apply(config, from(Blocks.DRIPSTONE_BLOCK));
	}
}
