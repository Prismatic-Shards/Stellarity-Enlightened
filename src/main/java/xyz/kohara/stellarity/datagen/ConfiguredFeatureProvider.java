package xyz.kohara.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.EndSpikeFeature;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.utils.Constants;


import net.minecraft.world.level.levelgen.feature.configurations.EndSpikeConfiguration;


import java.util.List;

public class ConfiguredFeatureProvider {
	public static final ResourceKey<ConfiguredFeature<?, ?>> MAIN_ISLAND_RING = id("main_island/ring");
	public static final ResourceKey<ConfiguredFeature<?, ?>> MAIN_ISLAND_PORTAL_PLATFORM = id("main_island/portal_platform");

	public static ResourceKey<ConfiguredFeature<?, ?>> id(String name) {
		return Stellarity.key(Registries.CONFIGURED_FEATURE, name);
	}

	public static void configure(HolderLookup.Provider provider, FabricDynamicRegistryProvider.Entries entries) {
		var lookup = provider.lookupOrThrow(Registries.CONFIGURED_FEATURE);
		entries.add(lookup, MAIN_ISLAND_RING);
		entries.add(lookup, MAIN_ISLAND_PORTAL_PLATFORM);
	}

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		context.register(MAIN_ISLAND_RING,
			new ConfiguredFeature<>(
				Feature.END_SPIKE,
				new EndSpikeConfiguration(false, Constants.OBSIDIAN_SPIKES, null)
			));
		context.register(MAIN_ISLAND_PORTAL_PLATFORM, new ConfiguredFeature<>(
			Feature.END_SPIKE,
			new EndSpikeConfiguration(true, List.of(new EndSpikeFeature.EndSpike(
				0, 0, 16, 70, false
			)), null)
		));
	}

}
