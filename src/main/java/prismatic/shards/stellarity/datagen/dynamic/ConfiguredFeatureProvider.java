package prismatic.shards.stellarity.datagen.dynamic;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.EndSpikeFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.EndSpikeConfiguration;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.utils.Constants;

import java.util.List;

public interface ConfiguredFeatureProvider {
	ResourceKey<ConfiguredFeature<?, ?>> MAIN_ISLAND_RING = id("main_island/ring");
	ResourceKey<ConfiguredFeature<?, ?>> MAIN_ISLAND_PORTAL_PLATFORM = id("main_island/portal_platform");

	static ResourceKey<ConfiguredFeature<?, ?>> id(String name) {
		return Stellarity.key(Registries.CONFIGURED_FEATURE, name);
	}

	static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
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
