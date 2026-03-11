package xyz.kohara.stellarity.mixin.exit_portal;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.EndPodiumFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import xyz.kohara.stellarity.Stellarity;

//? > 1.20.1 {
/*import net.minecraft.core.registries.Registries;
 *///? }

@Mixin(EndPodiumFeature.class)
public abstract class EndPodiumFeatureMixin extends Feature<NoneFeatureConfiguration> {
	@Shadow
	@Final
	private boolean active;

	public EndPodiumFeatureMixin(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Unique
	private void printRow(WorldGenLevel worldGenLevel, BlockState[] palette, int[][] blueprint, BlockPos start) {
		for (int i = 0; i < blueprint.length; i++) {
			int[] row = blueprint[i];
			for (int j = 0; j < row.length; j++) {
				this.setBlock(worldGenLevel, start.offset(i, 0, j), palette[row[j]]);
			}
		}
	}

	@WrapMethod(method = "place")
	private boolean stellarityPortal(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext, Operation<Boolean> original) {
		BlockPos blockPos = featurePlaceContext.origin();
		WorldGenLevel level = featurePlaceContext.level();

		for (int dx = -6; dx <= 6; dx++) {
			for (int dz = -6; dz <= 6; dz++) {
				setBlock(level, blockPos.offset(dx, 0, dz), Blocks.OBSIDIAN.defaultBlockState());
			}
		}

		for (EndCrystal crystal : level.getEntitiesOfClass(EndCrystal.class, new AABB(blockPos.offset(-1, 0, -1).getCenter(), blockPos.offset(1, 3, 1).getCenter()))) {
			crystal.discard();
		}


		int p = active ? 4 : 0;
		printRow(level, new BlockState[]{
				Blocks.AIR.defaultBlockState(),
				Blocks.OBSIDIAN.defaultBlockState(),
				Blocks.CRYING_OBSIDIAN.defaultBlockState().getBlock().defaultBlockState(),
				Blocks.BEDROCK.defaultBlockState(),
				Blocks.END_PORTAL.defaultBlockState(),
				Blocks.REINFORCED_DEEPSLATE.defaultBlockState(),
			}, new int[][]{
				{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
				{0, 1, 0, 0, 1, 1, 2, 1, 1, 0, 0, 1, 0},
				{0, 0, 2, 1, 3, 3, 3, 3, 3, 1, 2, 0, 0},
				{0, 0, 1, 5, p, p, 3, p, p, 5, 1, 0, 0},
				{0, 1, 3, p, p, p, 3, p, p, p, 3, 1, 0},
				{0, 1, 3, p, p, 5, 3, 5, p, p, 3, 1, 0},
				{1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 1},
				{0, 1, 3, p, p, 5, 3, 5, p, p, 3, 1, 0},
				{0, 1, 3, p, p, p, 3, p, p, p, 3, 1, 0},
				{0, 0, 1, 5, p, p, 3, p, p, 5, 1, 0, 0},
				{0, 0, 2, 1, 3, 3, 3, 3, 3, 1, 2, 0, 0},
				{0, 1, 0, 0, 1, 1, 2, 1, 1, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			}, blockPos.offset(-6, 1, -6)
		);


		/*
		North <-
		South ->
		West ^
		East v
		Make sure the directions on the stairs are opposites because mc views the stairs as the way u walk towards
		 */
		printRow(level, new BlockState[]{
				Blocks.AIR.defaultBlockState(),
				Blocks.BEDROCK.defaultBlockState(),
				Blocks.REINFORCED_DEEPSLATE.defaultBlockState(),
				Blocks.COBBLED_DEEPSLATE_SLAB.defaultBlockState(),
				Blocks.COBBLED_DEEPSLATE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.NORTH),
				Blocks.COBBLED_DEEPSLATE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.SOUTH),
				Blocks.COBBLED_DEEPSLATE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.EAST),
				Blocks.COBBLED_DEEPSLATE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.WEST),
			}, new int[][]{

				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 3, 5, 1, 4, 3, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0},
				{0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0},
				{0, 0, 6, 0, 0, 2, 1, 2, 0, 0, 6, 0, 0},
				{0, 5, 1, 4, 3, 1, 1, 1, 3, 5, 1, 4, 0},
				{0, 0, 7, 0, 0, 2, 1, 2, 0, 0, 7, 0, 0},
				{0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0},
				{0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 3, 5, 1, 4, 3, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			}, blockPos.offset(-6, 2, -6)
		);

		for (int dy = 3; dy <= 6; dy++)
			for (int dx = -6; dx <= 6; dx++) {
				for (int dz = -6; dz <= 6; dz++) {
					setBlock(level, blockPos.offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
				}
			}

		printRow(level, new BlockState[]{
			Blocks.BEDROCK.defaultBlockState(),
			Blocks.REINFORCED_DEEPSLATE.defaultBlockState(),
		}, new int[][]{
			{1, 0, 1},
			{0, 0, 0},
			{1, 0, 1}
		}, blockPos.offset(-1, 3, -1));

		printRow(level, new BlockState[]{
			Blocks.BEDROCK.defaultBlockState(),
			Blocks.AIR.defaultBlockState(),
			Blocks.COBBLED_DEEPSLATE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.NORTH),
			Blocks.COBBLED_DEEPSLATE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.SOUTH),
			Blocks.COBBLED_DEEPSLATE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.EAST),
			Blocks.COBBLED_DEEPSLATE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.WEST),
		}, new int[][]{
			{1, 4, 1},
			{3, 0, 2},
			{1, 5, 1}
		}, blockPos.offset(-1, 4, -1));

		setBlock(level, blockPos.above(5), Blocks.BEDROCK.defaultBlockState());
		setBlock(level, blockPos.above(6), Blocks.BEDROCK.defaultBlockState());

		var chestPos = blockPos.offset(7, 1, 0);
		setBlock(level, chestPos, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.EAST));
		var entity = level.getBlockEntity(chestPos);
		if (entity instanceof ChestBlockEntity chestEntity) {
			chestEntity.setLootTable(/*? 1.20.1 { */Stellarity.id("exit_portal") /*? } else {*/ /*Stellarity.key(Registries.LOOT_TABLE, "exit_portal")*//*? }*/, level.getSeed());
		}


		return true;
	}
}
