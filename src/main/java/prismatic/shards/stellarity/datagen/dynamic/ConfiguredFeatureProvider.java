package prismatic.shards.stellarity.datagen.dynamic;

import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import prismatic.shards.stellarity.key.StellarityPlacedFeatures;
import prismatic.shards.stellarity.util.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static net.minecraft.core.Holder.direct;
import static net.minecraft.world.level.block.Blocks.*;
import static prismatic.shards.stellarity.key.StellarityConfiguredFeatures.*;
import static prismatic.shards.stellarity.registry.StellarityBlocks.*;
import static prismatic.shards.stellarity.tags.StellarityBlockTags.*;
import static prismatic.shards.stellarity.util.ValueUtil.num;
import static prismatic.shards.stellarity.util.WorldgenUtil.*;

public interface ConfiguredFeatureProvider {
	static void bootstrapEarly(BootstrapContext<ConfiguredFeature<?, ?>> context) {

	}

	static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		var placed = context.lookup(Registries.PLACED_FEATURE);

		final var NOTHING = placed.getOrThrow(StellarityPlacedFeatures.NOTHING);

		context.register(MAIN_ISLAND_RING, new ConfiguredFeature<>(Feature.END_SPIKE, new EndSpikeConfiguration(
			false, Constants.OBSIDIAN_SPIKES, null
		)));
		context.register(MAIN_ISLAND_PORTAL_PLATFORM, new ConfiguredFeature<>(Feature.END_SPIKE, new EndSpikeConfiguration(
			true, List.of(new EndSpikeFeature.EndSpike(
			0, 0, 16, 60, false
		)), null
		)));
		context.register(GLOBAL_STALACTITES, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_REPLACEABLE_STALACTITE, block(END_STONE),
			direct(new PlacedFeature(
				direct(new ConfiguredFeature<>(Feature.BLOCK_COLUMN, blockColumns(
					Direction.DOWN, matchBlocks(new Vec3i(0, 1, 0), AIR), true,
					columnLayer(num(1, 2), block(END_STONE))
				))), List.of()
			)),
			CaveSurface.CEILING, num(1), 0, 10, 1, num(3, 6), 0.5f
		)));
		context.register(END_BARRENS_HILLS, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_REPLACEABLE_STALACTITE, block(END_STONE),
			direct(new PlacedFeature(direct(new ConfiguredFeature<>(Feature.BLOCK_COLUMN, blockColumns(
				Direction.UP, matchBlocks(AIR), true,
				columnLayer(num(0), block(END_STONE)), columnLayer(num(1), block(END_STONE))
			))), List.of()
			)),
			CaveSurface.FLOOR, num(1), 0, 10, 1, num(3, 6), 0.5f
		)));
		context.register(MAIN_ISLAND_OBSIDIAN, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_REPLACEABLE_END_STONE, weightedBlocks(new Block[]{END_STONE, CRYING_OBSIDIAN, OBSIDIAN}, new int[]{5, 1, 3}),
			NOTHING,
			CaveSurface.FLOOR, num(1), 0, 1, 0.1f, num(0, 5), 0.1f
		)));
		context.register(MAIN_ISLAND_PATCH, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_REPLACEABLE_END_STONE, weightedBlocks(new Block[]{END_STONE, COBBLESTONE, ANDESITE, TUFF}, new int[]{77, 3, 1, 2}),
			direct(new PlacedFeature(
				direct(new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
					List.of(weightedPlaced(new PlacedFeature(
						direct(new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
							block(BONE_BLOCK),
							new ForkingTrunkPlacer(6, 1, 8),
							block(AIR),
							new BlobFoliagePlacer(num(0), num(0), 0),
							Optional.empty(),
							twoLayersSize(),
							block(BONE_BLOCK)
						).build())),
						List.of()
					), 0.0033f)),
					direct(new PlacedFeature(direct(new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(weightedBlocks(
						Arrays.stream(new Block[]{DEAD_FIRE_CORAL, DEAD_HORN_CORAL, DEAD_BUBBLE_CORAL, DEAD_BRAIN_CORAL, DEAD_TUBE_CORAL}).map(block -> block.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false)).toArray(BlockState[]::new),
						new int[]{1, 1, 1, 1, 1}
					)))), List.of()))
				))), List.of()
			)),
			CaveSurface.FLOOR, num(3), 0.334f, 10, 0.03f, num(5, 7), 0.556f
		)));

		context.register(AMETHYST_FOREST_CALCITE_BOTTOM, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_REPLACEABLE_END_STONE, weightedBlocks(new Block[]{CALCITE, DIORITE}, new int[]{2, 1}),
			direct(new PlacedFeature(
				direct(new ConfiguredFeature<>(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
					List.of(new BlockColumnConfiguration.Layer(num(1), weightedBlocks(new Block[]{CALCITE, DIORITE}, new int[]{2, 1}))),
					Direction.DOWN, matchBlocks(AIR), true
				))),
				List.of()
			)),
			CaveSurface.CEILING, num(3, 8), 0.5f, 20, 1, num(3, 8), 0.5f
		)));
		context.register(AMETHYST_FOREST_AMETHYST_GEODE, new ConfiguredFeature<>(Feature.GEODE, new GeodeConfiguration(
			new GeodeBlockSettings(block(AIR), block(AMETHYST_BLOCK), block(BUDDING_AMETHYST),
				weightedBlocks(new Block[]{CALCITE, POLISHED_DIORITE, DIORITE}, new int[]{1, 1, 1}),
				weightedBlocks(new Block[]{CALCITE, POLISHED_DIORITE, DIORITE}, new int[]{1, 1, 1}),
				List.of(
					SMALL_AMETHYST_BUD.defaultBlockState().setValue(BlockStateProperties.FACING, Direction.UP),
					MEDIUM_AMETHYST_BUD.defaultBlockState().setValue(BlockStateProperties.FACING, Direction.UP),
					LARGE_AMETHYST_BUD.defaultBlockState().setValue(BlockStateProperties.FACING, Direction.UP),
					AMETHYST_CLUSTER.defaultBlockState().setValue(BlockStateProperties.FACING, Direction.UP)
				), WORLDGEN_INVALID_AMETHYST_GEODE, WORLDGEN_INVALID_AMETHYST_GEODE
			),
			new GeodeLayerSettings(1.7, 2.2, 3.2, 3.2),
			new GeodeCrackSettings(0.65, 1.5, 2),
			0.2, 0.083, false, num(4, 6), num(3, 4), num(1, 2), -8, 8, 0.05, 1
		)));
		context.register(AMETHYST_FOREST_TUFF_ROCK, new ConfiguredFeature<>(Feature.BLOCK_BLOB, new BlockBlobConfiguration(TUFF.defaultBlockState(), all())));
		context.register(AMETHYST_FOREST_OBSIDIAN, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_REPLACEABLE_GRASS_BLOCK, weightedBlocks(new Block[]{ENDER_GRASS_BLOCK, CRYING_OBSIDIAN, OBSIDIAN}, new int[]{20, 1, 5}), NOTHING,
			CaveSurface.FLOOR, num(1), 0, 1, 0.1f, num(0, 5), 0.1f
		)));
		context.register(AMETHYST_FOREST_DIRT, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_REPLACEABLE_GRASS_BLOCK, weightedBlocks(new Block[]{ENDER_GRASS_BLOCK, ROOTED_ENDER_DIRT}, new int[]{21, 1}), NOTHING,
			CaveSurface.FLOOR, num(1), 0, 1, 0.1f, num(0, 5), 0.1f
		)));

	}
}
