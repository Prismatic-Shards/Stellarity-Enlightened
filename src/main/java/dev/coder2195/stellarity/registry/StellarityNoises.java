package dev.coder2195.stellarity.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityNoises {
	ResourceKey<NormalNoise> CLIMATE_CONTINENTALNESS = id("climate/continentalness");
	ResourceKey<NormalNoise> CLIMATE_EROSION = id("climate/erosion");
	ResourceKey<NormalNoise> CLIMATE_HUMIDITY = id("climate/humidity");
	ResourceKey<NormalNoise> CLIMATE_TEMPERATURE = id("climate/temperature");
	ResourceKey<NormalNoise> CLIMATE_WEIRDNESS = id("climate/weirdness");

	ResourceKey<NormalNoise> MAIN_ISLAND_EDGES_1 = id("main_island/edges_1");
	ResourceKey<NormalNoise> MAIN_ISLAND_EDGES_2 = id("main_island/edges_2");
	ResourceKey<NormalNoise> MAIN_ISLAND_EDGES_3 = id("main_island/edges_3");
	ResourceKey<NormalNoise> MAIN_ISLAND_N1 = id("main_island/n1");
	ResourceKey<NormalNoise> MAIN_ISLAND_N2 = id("main_island/n2");
	ResourceKey<NormalNoise> MAIN_ISLAND_N3 = id("main_island/n3");
	ResourceKey<NormalNoise> MAIN_ISLAND_N4 = id("main_island/n4");
	ResourceKey<NormalNoise> MAIN_ISLAND_SHAPER_1 = id("main_island/shaper_1");
	ResourceKey<NormalNoise> MAIN_ISLAND_SHAPER_2 = id("main_island/shaper_2");
	ResourceKey<NormalNoise> MAIN_ISLAND_SHIFT_X = id("main_island/shift_x");
	ResourceKey<NormalNoise> MAIN_ISLAND_SHIFT_Y = id("main_island/shift_y");
	ResourceKey<NormalNoise> MAIN_ISLAND_SHIFT_Z = id("main_island/shift_z");
	ResourceKey<NormalNoise> MAIN_ISLAND_SURFACE = id("main_island/surface");

	ResourceKey<NormalNoise> JAGGED = id("jagged");
	ResourceKey<NormalNoise> SURFACE = id("surface");
	ResourceKey<NormalNoise> SURFACE_2X = id("surface_2x");
	ResourceKey<NormalNoise> SURFACE_4X = id("surface_4x");

	static void bootstrap(BootstrapContext<NormalNoise> context) {
		context.register(SURFACE_2X, NormalNoise.createParity(-7, 1, 1, 1));
		context.register(SURFACE, NormalNoise.createParity(-6, 1, 1, 1));
		context.register(SURFACE_4X, NormalNoise.createParity(-8, 1, 1, 1));
		context.register(JAGGED, NormalNoise.createParity(-16, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		context.register(CLIMATE_EROSION, NormalNoise.createParity(-9, 1, 1, 0, 1, 1));
		context.register(CLIMATE_TEMPERATURE, NormalNoise.createParity(-10, 1.5, 0, 1, 0, 0, 0));
		context.register(CLIMATE_HUMIDITY, NormalNoise.createParity(-8, 1, 1, 0, 0, 0, 0));
		context.register(CLIMATE_CONTINENTALNESS, NormalNoise.createParity(-9, 1, 1, 2, 2, 2, 1, 1, 1, 1));
		context.register(CLIMATE_WEIRDNESS, NormalNoise.createParity(-7, 1, 2, 1, 0, 0, 0));
		context.register(MAIN_ISLAND_N1, NormalNoise.createParity(-14, 1.0));
		context.register(MAIN_ISLAND_SHIFT_X, NormalNoise.createParity(-6, 1));
		context.register(MAIN_ISLAND_N3, NormalNoise.createParity(-14, 1.0));
		context.register(MAIN_ISLAND_N2, NormalNoise.createParity(-14, 1.0));
		context.register(MAIN_ISLAND_N4, NormalNoise.createParity(-14, 1.0));
		context.register(MAIN_ISLAND_SURFACE, NormalNoise.createParity(-3, 1, 1, 1, 1, 1));
		context.register(MAIN_ISLAND_EDGES_3, NormalNoise.createParity(-5, -1, 0.5, 1.5, 0));
		context.register(MAIN_ISLAND_EDGES_1, NormalNoise.createParity(-7, 1.5, -0.2, 1, 0, 0));
		context.register(MAIN_ISLAND_SHIFT_Y, NormalNoise.createParity(-6, 1));
		context.register(MAIN_ISLAND_EDGES_2, NormalNoise.createParity(-6, 1, 2, 1, 0, 0));
		context.register(MAIN_ISLAND_SHAPER_1, NormalNoise.createParity(-4, 1));
		context.register(MAIN_ISLAND_SHIFT_Z, NormalNoise.createParity(-6, 1));
		context.register(MAIN_ISLAND_SHAPER_2, NormalNoise.createParity(-8, 1.0, 1.0, 1.0, 1.0));
	}

	private static ResourceKey<NormalNoise> id(String id) {
		return ResourceKey.create(Registries.NOISE, Stellarity.id(id));
	}
}
