package dev.coder2195.stellarity.block;

import dev.coder2195.stellarity.block_entity.ColoredBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class ColoredLeavesBlock extends TintedParticleLeavesBlock implements EntityBlock {
	public static final int DEFAULT_COLOR = 0x00ff00;

	public ColoredLeavesBlock(Properties properties) {
		super(1f, properties);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
	}

	@Override
	protected boolean triggerEvent(final BlockState state, final Level level, final BlockPos pos, final int b0, final int b1) {
		super.triggerEvent(state, level, pos, b0, b1);
		BlockEntity blockEntity = level.getBlockEntity(pos);
		return blockEntity != null && blockEntity.triggerEvent(b0, b1);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
		return new ColoredBlockEntity(worldPosition, blockState, DEFAULT_COLOR);
	}
}
