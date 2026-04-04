package prismatic.shards.stellarity.datagen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import prismatic.shards.stellarity.Stellarity;

import java.util.List;

public class PlacedFeatureProvider {

	public static final ResourceKey<PlacedFeature> MAIN_ISLAND_RING = id("main_island/ring");
	public static final ResourceKey<PlacedFeature> MAIN_ISLAND_PORTAL_PLATFORM = id("main_island/portal_platform");

	public static ResourceKey<PlacedFeature> id(String name) {
		return Stellarity.key(Registries.PLACED_FEATURE, name);
	}

	public static void bootstrap(BootstrapContext<PlacedFeature> context) {

		HolderGetter<ConfiguredFeature<?, ?>> configured = context.lookup(Registries.CONFIGURED_FEATURE);

		context.register(
			MAIN_ISLAND_RING,
			new PlacedFeature(configured.getOrThrow(ConfiguredFeatureProvider.MAIN_ISLAND_RING), List.of(BiomeFilter.biome()))
		);

		context.register(
			MAIN_ISLAND_PORTAL_PLATFORM,
			new PlacedFeature(configured.getOrThrow(ConfiguredFeatureProvider.MAIN_ISLAND_PORTAL_PLATFORM), List.of(BiomeFilter.biome()))
		);
	}
}
