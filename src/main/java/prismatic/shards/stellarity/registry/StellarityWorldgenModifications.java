package prismatic.shards.stellarity.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.dimension.v1.DimensionEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import prismatic.shards.stellarity.Stellarity;

import java.util.Optional;
import java.util.function.Predicate;

import static net.minecraft.world.attribute.EnvironmentAttributes.*;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.*;
import static prismatic.shards.stellarity.key.StellarityPlacedFeatures.*;

public interface StellarityWorldgenModifications {

	static ResourceKey<PlacedFeature> mc(String id) {
		return Stellarity.mcKey(Registries.PLACED_FEATURE, id);
	}

	static void init() {
		DimensionEvents.MODIFY_ATTRIBUTES.register((dimension, attributes, registries) -> {
			if (!dimension.is(BuiltinDimensionTypes.END)) return;

			attributes.set(BED_RULE, new BedRule(BedRule.Rule.ALWAYS, BedRule.Rule.ALWAYS, false, Optional.empty()));
			attributes.set(RESPAWN_ANCHOR_WORKS, false);
			attributes.set(CAN_START_RAID, true);
			attributes.set(PIGLINS_ZOMBIFY, false);
			attributes.set(WATER_EVAPORATES, false);

			DimensionType dimensionType = dimension.value();
			dimensionType.logicalHeight = Math.max(dimensionType.logicalHeight, 384);
			dimensionType.height = Math.max(dimensionType.height, 384);
		});

		Predicate<BiomeSelectionContext> mainIsland = context -> context.getBiomeHolder().is(Biomes.THE_END);

		BiomeModifications.addFeature(mainIsland, RAW_GENERATION, GLOBAL_STALACTITES);
		BiomeModifications.addFeature(mainIsland, RAW_GENERATION, MAIN_ISLAND_HILLS);
		BiomeModifications.addFeature(mainIsland, UNDERGROUND_DECORATION, MAIN_ISLAND_OBSIDIAN);
		BiomeModifications.addFeature(mainIsland, FLUID_SPRINGS, MAIN_ISLAND_PATCHES);
		BiomeModifications.addFeature(mainIsland, VEGETAL_DECORATION, MAIN_ISLAND_CHORUS_PLANTS);
		BiomeModifications.addFeature(mainIsland, TOP_LAYER_MODIFICATION, MAIN_ISLAND_RING);
		BiomeModifications.addFeature(mainIsland, TOP_LAYER_MODIFICATION, MAIN_ISLAND_PORTAL_PLATFORM);

		BiomeModifications.create(Stellarity.id("replacements")).add(ModificationPhase.REPLACEMENTS, context -> context.getBiomeHolder().is(Biomes.THE_END), (selection, modification) -> {
				var attributes = modification.getAttributes();
				attributes.set(SKY_COLOR, 0x000000);
				attributes.set(FOG_COLOR, 0x000000);
				attributes.set(WATER_FOG_COLOR, 0x41307e);

				var effects = modification.getEffects();
				effects.setGrassColorOverride(0xdedede);
				effects.setFoliageColorOverride(0xc2c2c2);
				effects.setWaterColor(0x62529e);

				var mobSpawns = modification.getMobSpawnSettings();
				mobSpawns.removeSpawnsOfEntityType(EntityType.ENDERMAN);
				mobSpawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 4, 4), 10);
				mobSpawns.clearMobCharge(EntityType.ENDERMAN);
				mobSpawns.addMobCharge(EntityType.ENDERMAN, 0.75, 1);
			}
		);
	}
}
