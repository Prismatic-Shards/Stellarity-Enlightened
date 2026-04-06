package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityPlacedFeatures {
	ResourceKey<PlacedFeature> NOTHING = id("nothing");
	ResourceKey<PlacedFeature> MAIN_ISLAND_RING = id("main_island/ring");
	ResourceKey<PlacedFeature> MAIN_ISLAND_PORTAL_PLATFORM = id("main_island/portal_platform");
	ResourceKey<PlacedFeature> GLOBAL_STALACTITES = id("global/stalactites");
	ResourceKey<PlacedFeature> MAIN_ISLAND_HILLS = id("main_island/hills");
	ResourceKey<PlacedFeature> MAIN_ISLAND_OBSIDIAN = id("main_island/obsidian");
	ResourceKey<PlacedFeature> MAIN_ISLAND_PATCHES = id("main_island/patches");
	ResourceKey<PlacedFeature> MAIN_ISLAND_CHORUS_PLANTS = id("main_island/chorus_plants");
	// Obsidian spikes have been skipped because of native implementation of crying obsidian tops


	static ResourceKey<PlacedFeature> id(String name) {
		return Stellarity.key(Registries.PLACED_FEATURE, name);
	}
}
