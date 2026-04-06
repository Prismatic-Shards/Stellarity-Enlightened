package prismatic.shards.stellarity.datagen.dynamic;

import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
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
import static prismatic.shards.stellarity.tags.StellarityBlockTags.WORLDGEN_REPLACEABLE_END_STONE;
import static prismatic.shards.stellarity.tags.StellarityBlockTags.WORLDGEN_REPLACEABLE_STALACTITE;
import static prismatic.shards.stellarity.util.ValueUtil.num;
import static prismatic.shards.stellarity.util.WorldgenUtil.*;

public interface ConfiguredFeatureProvider {
	static void bootstrapEarly(BootstrapContext<ConfiguredFeature<?, ?>> context) {

	}

	static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		var placed = context.lookup(Registries.PLACED_FEATURE);

		context.register(MAIN_ISLAND_RING, new ConfiguredFeature<>(Feature.END_SPIKE, new EndSpikeConfiguration(
			false, Constants.OBSIDIAN_SPIKES, null
		)));
		context.register(MAIN_ISLAND_PORTAL_PLATFORM, new ConfiguredFeature<>(Feature.END_SPIKE, new EndSpikeConfiguration(
			true, List.of(new EndSpikeFeature.EndSpike(
			0, 0, 16, 70, false
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
			placed.getOrThrow(StellarityPlacedFeatures.NOTHING),
			CaveSurface.FLOOR, num(1), 0, 1, 0.1f, num(0, 5), 0.1f
		)));
		context.register(MAIN_ISLAND_PATCHES, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
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
	}
}
