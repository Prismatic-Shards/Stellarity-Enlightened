package xyz.kohara.stellarity.registry.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
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
import xyz.kohara.stellarity.tags.StellarityBlockTags;
import com.mojang.serialization.MapCodec;
//? > 1.21.10 {
import net.minecraft.world.entity.InsideBlockEffectApplier;
 //? }

public class DuskberryBush extends BushBlock implements BonemealableBlock {
	public static final int MAX_AGE = 3;
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
	private static final VoxelShape SAPLING_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 8.0, 13.0);
	private static final VoxelShape MID_GROWTH_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

	public DuskberryBush(Properties properties) {
		super(properties);

		registerDefaultState(defaultBlockState().setValue(AGE, 0));
	}


	public static final MapCodec<BushBlock> CODEC = simpleCodec(DuskberryBush::new);

	@Override
	public MapCodec<BushBlock> codec() {
		return CODEC;
	}

	@Override
	protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
		return super.mayPlaceOn(blockState, blockGetter, blockPos) || blockState.is(StellarityBlockTags.DIRT);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	public static final Properties PROPERTIES = Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollision().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY);

	@Override
	public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
		return blockState.getValue(AGE) < MAX_AGE;
	}


	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
		VoxelShape var10000;
		switch (blockState.getValue(AGE)) {
			case 0 -> var10000 = SAPLING_SHAPE;
			case MAX_AGE -> var10000 = Shapes.block();
			default -> var10000 = MID_GROWTH_SHAPE;
		}

		return var10000;
	}


	@Override
	public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
		return true;
	}


	@Override
	public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity/*? > 1.21.10 >> ') {'*/, InsideBlockEffectApplier insideBlockEffectApplier, boolean bl) {
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
