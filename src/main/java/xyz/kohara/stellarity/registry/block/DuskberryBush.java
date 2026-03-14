package xyz.kohara.stellarity.registry.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.kohara.stellarity.registry.item.Duskberry;

// whole bunch of deprecated stuff that just ends up being undeprecated later on
//? 1.20.1
@SuppressWarnings("deprecation")
public class DuskberryBush extends BushBlock implements BonemealableBlock {
	public static final int MAX_AGE = 3;
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
	private static final VoxelShape SAPLING_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 8.0, 13.0);
	private static final VoxelShape MID_GROWTH_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

	public DuskberryBush(Properties properties) {
		super(properties);

		registerDefaultState(defaultBlockState().setValue(AGE, 0));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	public static final Properties PROPERTIES = Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY);

	@Override
	public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
		return blockState.getValue(AGE) < 3;
	}


	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
		VoxelShape var10000;
		switch (blockState.getValue(AGE)) {
			case 0 -> var10000 = SAPLING_SHAPE;
			case 3 -> var10000 = Shapes.block();
			default -> var10000 = MID_GROWTH_SHAPE;
		}

		return var10000;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
		return true;
	}


	@Override
	public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
		if (entity instanceof LivingEntity livingEntity) {
			livingEntity.makeStuckInBlock(blockState, new Vec3((double) 0.8F, (double) 0.75F, (double) 0.8F));
			for (var effect : Duskberry.debuffs(blockState.getValue(AGE))) {
				livingEntity.addEffect(effect);
			}
		}
	}

	@Override
	public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
		int i = Math.min(3, blockState.getValue(AGE) + 1);
		serverLevel.setBlock(blockPos, blockState.setValue(AGE, i), 2);
	}

	@Override
	public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
		int i = blockState.getValue(AGE);
		if (i < 3 && randomSource.nextInt(8) == 0) {
			BlockState growState = blockState.setValue(AGE, i + 1);
			serverLevel.setBlock(blockPos, growState, 2);
			serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(growState));
		}
	}


}
