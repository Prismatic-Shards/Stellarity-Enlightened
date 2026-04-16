package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityPlacedFeatures {
	ResourceKey<PlacedFeature> NOTHING = id("nothing");
	ResourceKey<PlacedFeature> GLOBAL_STALACTITES = id("global/stalactites");

	ResourceKey<PlacedFeature> MAIN_ISLAND_RING = id("main_island/ring");
	ResourceKey<PlacedFeature> MAIN_ISLAND_PORTAL_PLATFORM = id("main_island/portal_platform");
	ResourceKey<PlacedFeature> MAIN_ISLAND_HILLS = id("main_island/hills");
	ResourceKey<PlacedFeature> MAIN_ISLAND_OBSIDIAN = id("main_island/obsidian");
	ResourceKey<PlacedFeature> MAIN_ISLAND_PATCHES = id("main_island/patches");
	ResourceKey<PlacedFeature> MAIN_ISLAND_CHORUS_PLANTS = id("main_island/chorus_plants");

	ResourceKey<PlacedFeature> AMETHYST_FOREST_CALCITE_BOTTOM = id("amethyst_forest/calcite_bottom");
	ResourceKey<PlacedFeature> AMETHYST_FOREST_AMETHYST_GEODES = id("amethyst_forest/amethyst_geodes");
	ResourceKey<PlacedFeature> AMETHYST_FOREST_TUFF_ROCKS = id("amethyst_forest/tuff_rocks");
	ResourceKey<PlacedFeature> AMETHYST_FOREST_OBSIDIAN = id("amethyst_forest/obsidian");
	ResourceKey<PlacedFeature> AMETHYST_FOREST_DIRT = id("amethyst_forest/dirt");
	ResourceKey<PlacedFeature> AMETHYST_FOREST_TREES = id("amethyst_forest/trees");
	ResourceKey<PlacedFeature> AMETHYST_FOREST_CRYSTAL_GRASS = id("amethyst_forest/crystal_grass");
	ResourceKey<PlacedFeature> AMETHYST_FOREST_FLOWERS = id("amethyst_forest/flowers");
	ResourceKey<PlacedFeature> AMETHYST_FOREST_ROOTS = id("amethyst_forest/roots");

	static ResourceKey<PlacedFeature> id(String name) {
		return Stellarity.key(Registries.PLACED_FEATURE, name);
	}
}
