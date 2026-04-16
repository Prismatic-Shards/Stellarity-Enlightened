package prismatic.shards.stellarity.datagen.dynamic;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.key.StellarityConfiguredFeatures;

import java.util.List;

import static net.minecraft.world.level.block.Blocks.AIR;
import static net.minecraft.world.level.block.Blocks.DARK_OAK_SAPLING;
import static prismatic.shards.stellarity.key.StellarityPlacedFeatures.*;
import static prismatic.shards.stellarity.util.ValueUtil.num;
import static prismatic.shards.stellarity.util.ValueUtil.weighted;
import static prismatic.shards.stellarity.util.WorldgenUtil.*;


public interface PlacedFeatureProvider {
	static ResourceKey<ConfiguredFeature<?, ?>> mcPlaced(String id) {
		return Stellarity.mcKey(Registries.CONFIGURED_FEATURE, id);
	}

	static void bootstrapEarly(BootstrapContext<PlacedFeature> context) {
		context.register(NOTHING, new PlacedFeature(Holder.direct(new ConfiguredFeature<>(Feature.NO_OP, NoneFeatureConfiguration.INSTANCE)), List.of()));
	}

	static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configured = context.lookup(Registries.CONFIGURED_FEATURE);

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
		context.register(MAIN_ISLAND_CHORUS_PLANTS, new PlacedFeature(configured.getOrThrow(mcPlaced("chorus_plant")), List.of(
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
	}
}
