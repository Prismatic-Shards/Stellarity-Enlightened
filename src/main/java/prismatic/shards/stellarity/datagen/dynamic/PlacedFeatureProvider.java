package prismatic.shards.stellarity.datagen.dynamic;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.key.StellarityConfiguredFeatures;

import java.util.List;

import static net.minecraft.world.level.block.Blocks.*;
import static prismatic.shards.stellarity.key.StellarityPlacedFeatures.*;
import static prismatic.shards.stellarity.registry.StellarityBlocks.*;
import static prismatic.shards.stellarity.util.ValueUtil.*;
import static prismatic.shards.stellarity.util.WorldgenUtil.*;


public interface PlacedFeatureProvider {
	static ResourceKey<ConfiguredFeature<?, ?>> mcConfig(String id) {
		return Stellarity.mcKey(Registries.CONFIGURED_FEATURE, id);
	}

	static void bootstrapEarly(BootstrapContext<PlacedFeature> context) {
		context.register(NOTHING, new PlacedFeature(Holder.direct(new ConfiguredFeature<>(Feature.NO_OP, NoneFeatureConfiguration.INSTANCE)), List.of()));
	}

	static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configured = context.lookup(Registries.CONFIGURED_FEATURE);
		HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);

		context.register(MAIN_ISLAND_RING, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.MAIN_ISLAND_RING), List.of(biome())));
		context.register(MAIN_ISLAND_PORTAL_PLATFORM, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.MAIN_ISLAND_PORTAL_PLATFORM), List.of(biome())));
		context.register(GLOBAL_STALACTITES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.GLOBAL_STALACTITES), List.of(
			countPlace(weighted(14, 100, 28, 50, 56, 25, 80, 1)),
			inSquare(),
			noisePlace(10, 55, 0),
			heightPlace(height(aboveBottom(0), belowTop(0))),
			envPlace(Direction.UP, solid(), matchBlocks(AIR), 32),
			biome()
		)));
		context.register(MAIN_ISLAND_HILLS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_BARRENS_HILLS), List.of(
			countPlace(weighted(30, 100, 35, 50, 40, 25, 45, 12)),
			inSquare(),
			noisePlace(3, 100, 0),
			heightPlace(height(aboveBottom(0), belowTop(0))),
			envPlace(Direction.DOWN, solid(), matchBlocks(AIR), 32),
			biome()
		)));
		context.register(MAIN_ISLAND_OBSIDIAN, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.MAIN_ISLAND_OBSIDIAN), List.of(
			biome(), countPlace(1), inSquare(), rarity(2), heightmap(Heightmap.Types.MOTION_BLOCKING)
		)));
		context.register(MAIN_ISLAND_PATCHES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.MAIN_ISLAND_PATCH), List.of(
			biome(), everyLayer(num(2))
		)));
		context.register(MAIN_ISLAND_CHORUS_PLANTS, new PlacedFeature(configured.getOrThrow(mcConfig("chorus_plant")), List.of(
			biome(), heightmap(Heightmap.Types.MOTION_BLOCKING), countPlace(weighted(0, 9, 1, 1)), inSquare()
		)));

		context.register(AMETHYST_FOREST_CALCITE_BOTTOM, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.AMETHYST_FOREST_CALCITE_BOTTOM), List.of(
			countPlace(40), inSquare(), noisePlace(10, 20, 0), heightPlace(height(aboveBottom(0), belowTop(0))),
			envPlace(Direction.UP, solid(), matchBlocks(AIR), 32), biome()
		)));
		context.register(AMETHYST_FOREST_AMETHYST_GEODES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.AMETHYST_FOREST_AMETHYST_GEODE), List.of(
			rarity(1), countPlace(1), inSquare(), heightPlace(height(aboveBottom(12), belowTop(12))), biome()
		)));
		context.register(AMETHYST_FOREST_TUFF_ROCKS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.AMETHYST_FOREST_TUFF_ROCK), List.of(
			everyLayer(num(1)), rarity(2), biome()
		)));
		context.register(AMETHYST_FOREST_OBSIDIAN, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.AMETHYST_FOREST_OBSIDIAN), List.of(
			rarity(3), inSquare(), heightmap(Heightmap.Types.WORLD_SURFACE_WG), countPlace(16), biome()
		)));
		context.register(AMETHYST_FOREST_DIRT, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.AMETHYST_FOREST_DIRT), List.of(
			rarity(3), inSquare(), heightmap(Heightmap.Types.WORLD_SURFACE_WG), countPlace(16), biome()
		)));
		context.register(AMETHYST_FOREST_TREES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.AMETHYST_FOREST_TREE), List.of(
			everyLayer(2), biome(), blockFilter(all(
				matchBlocks(AIR), wouldSurvive(DARK_OAK_SAPLING.defaultBlockState().setValue(BlockStateProperties.STAGE, 0))
			))
		)));
		context.register(AMETHYST_FOREST_CRYSTAL_GRASS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.AMETHYST_FOREST_CRYSTAL_GRASS), List.of(
			everyLayer(7), biome()
		)));
		context.register(AMETHYST_FOREST_FLOWERS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.AMETHYST_FOREST_FLOWER), List.of(
			everyLayer(1), biome(), countPlace(40), placeRandom(trapezoid(-5, 5, 0), trapezoid(-2, 2, 0)), blockFilter(matchBlocks(AIR))
		)));
		context.register(AMETHYST_FOREST_ROOTS, new PlacedFeature(Holder.direct(new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(block(HANGING_ROOTS)))), List.of(
			countPlace(87), inSquare(), heightPlace(height(aboveBottom(0), belowTop(0))), envPlace(Direction.UP, all(
				sturdyFace(Direction.DOWN), matchBlocks(ENDER_DIRT, ENDER_GRASS_BLOCK)
			), matchBlocks(AIR), 32), placeRandom(num(0), num(-1)), biome(), countPlace(24), placeRandom(
				trapezoid(-5, 5, 0), trapezoid(-5, 5, 0)
			), blockFilter(all(matchBlocks(AIR), matchBlocks(new Vec3i(0, 1, 0), ENDER_DIRT, ENDER_GRASS_BLOCK)))
		)));

		context.register(ASHFALL_DELTAS_WATER_DELTAS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ASHFALL_DELTAS_WATER_DELTA), List.of(
			everyLayer(35), biome()
		)));
		context.register(ASHFALL_DELTAS_GRASS_DELTAS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ASHFALL_DELTAS_GRASS_DELTA), List.of(
			everyLayer(3), biome()
		)));
		context.register(ASHFALL_DELTAS_BASALT_COLUMNS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ASHFALL_DELTAS_BASALT_COLUMNS), List.of(
			everyLayer(2), biome()
		)));
		context.register(ASHFALL_DELTAS_SEAGRASS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ASHFALL_DELTAS_SEAGRASS), List.of(
			everyLayer(150), blockFilter(all(matchBlocks(new Vec3i(0, -1, 0), BASALT, STONE, BLACKSTONE), matchBlocks(WATER), matchBlocks(new Vec3i(0, 1, 0), AIR, WATER))), biome()
		)));
		context.register(ASHFALL_DELTAS_VEGETATION, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ASHFALL_DELTAS_VEGETATION), List.of(
			everyLayer(30), biome(), countPlace(2), placeRandom(trapezoid(-5, 5, 0), trapezoid(-3, 3, 0)), blockFilter(BlockPredicate.allOf(matchBlocks(AIR), matchBlocks(new Vec3i(0, -1, 0), ENDER_GRASS_BLOCK, MUD)))
		)));
		context.register(ASHFALL_DELTAS_TREES, new PlacedFeature(Holder.direct(new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
			List.of(weightedPlaced(new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ASHFALL_DELTAS_TREE), List.of()), 0.8f)),
			placed.getOrThrow(NOTHING)
		))), List.of(everyLayer(2), biome(), rarity(5))));
		context.register(ASHFALL_DELTAS_GRASS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ASHFALL_DELTAS_GRASS), List.of(
			everyLayer(30), biome(), countPlace(20), placeRandom(trapezoid(-5, 5, 0), trapezoid(-3, 3, 0)), blockFilter(all(matchBlocks(AIR), wouldSurvive(SHORT_GRASS)))
		)));
		context.register(ASHFALL_DELTAS_MAGMA, new PlacedFeature(configured.getOrThrow(mcConfig("underwater_magma")), List.of(
			countPlace(200), inSquare(), heightPlace(height(aboveBottom(0), belowTop(0))), surfaceRelative(Heightmap.Types.OCEAN_FLOOR), biome()
		)));
		context.register(ASHFALL_DELTAS_ASH_PILES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ASHFALL_DELTAS_ASH_PILE), List.of(
			countPlace(25), inSquare(), heightPlace(height(aboveBottom(0), belowTop(0))), envPlace(Direction.DOWN, solid(), matchBlocks(AIR), 12), biome()
		)));

	}
}
