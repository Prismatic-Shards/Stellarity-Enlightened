package xyz.kohara.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import xyz.kohara.stellarity.Stellarity;

import java.util.List;

public class PlacedFeatureProvider {

	public static ResourceKey<PlacedFeature> MAIN_ISLAND_RING = id("main_island/ring");
	public static ResourceKey<PlacedFeature> MAIN_ISLAND_PORTAL_PLATFORM = id("main_island/portal_platform");

	public static ResourceKey<PlacedFeature> id(String name) {
		return Stellarity.key(Registries.PLACED_FEATURE, name);
	}

	public static void configure(HolderLookup.Provider provider, FabricDynamicRegistryProvider.Entries entries) {
		var lookup = provider.lookupOrThrow(Registries.PLACED_FEATURE);
		entries.add(lookup, MAIN_ISLAND_RING);
		entries.add(lookup, MAIN_ISLAND_PORTAL_PLATFORM);
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
