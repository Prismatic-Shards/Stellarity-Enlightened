package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityConfiguredFeatures {
	ResourceKey<ConfiguredFeature<?, ?>> MAIN_ISLAND_RING = id("main_island/ring");
	ResourceKey<ConfiguredFeature<?, ?>> MAIN_ISLAND_PORTAL_PLATFORM = id("main_island/portal_platform");
	ResourceKey<ConfiguredFeature<?, ?>> GLOBAL_STALACTITES = id("global/stalactites");
	ResourceKey<ConfiguredFeature<?, ?>> END_BARRENS_HILLS = id("end_barrens/hills");
	ResourceKey<ConfiguredFeature<?, ?>> MAIN_ISLAND_OBSIDIAN = id("main_island/obsidian");
	ResourceKey<ConfiguredFeature<?, ?>> MAIN_ISLAND_PATCHES = id("main_island/patches");

	static ResourceKey<ConfiguredFeature<?, ?>> id(String name) {
		return Stellarity.key(Registries.CONFIGURED_FEATURE, name);
	}
}
