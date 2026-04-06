package prismatic.shards.stellarity.datagen.dynamic;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import prismatic.shards.stellarity.Stellarity;

import static net.minecraft.world.level.biome.Biomes.THE_END;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.*;
import static prismatic.shards.stellarity.key.StellarityPlacedFeatures.*;


public interface BiomeProvider {
	static MobSpawnSettings.Builder addSpawn(MobSpawnSettings.Builder builder, MobCategory category, EntityType<?> entityType, int weight, int min, int max) {
		return builder.addSpawn(category,
			weight,
			new MobSpawnSettings.SpawnerData(entityType, min, max)
		);
	}

	static ResourceKey<PlacedFeature> mc(String id) {
		return Stellarity.mcKey(Registries.PLACED_FEATURE, id);
	}

	static void configure(HolderLookup.Provider provider, FabricDynamicRegistryProvider.Entries entries) {
		int skyColor = 0x000000;
		int fogColor = 0x000000;
		int waterFogColor = 0x41307e;
		entries.add(THE_END, new Biome.BiomeBuilder()
			.temperature(0.8f)
			.downfall(0.4f)
			.hasPrecipitation(false)
			.specialEffects(new BiomeSpecialEffects.Builder()
				.grassColorOverride(0xdedede)
				.foliageColorOverride(0xc2c2c2)
				.waterColor(0x62529e)
				.build())
			.setAttribute(EnvironmentAttributes.SKY_COLOR, skyColor)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, fogColor)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, waterFogColor)
			.mobSpawnSettings(addSpawn(new MobSpawnSettings.Builder(), MobCategory.MONSTER, EntityType.ENDERMAN, 10, 4, 4)
				.addMobCharge(EntityType.ENDERMAN, 0.75, 1)
				.build())
			.generationSettings(new BiomeGenerationSettings.Builder(
				provider.lookupOrThrow(Registries.PLACED_FEATURE),
				provider.lookupOrThrow(Registries.CONFIGURED_CARVER)
			)
				.addFeature(RAW_GENERATION, GLOBAL_STALACTITES)
				.addFeature(RAW_GENERATION, MAIN_ISLAND_HILLS)
				.addFeature(UNDERGROUND_DECORATION, MAIN_ISLAND_OBSIDIAN)
				.addFeature(FLUID_SPRINGS, MAIN_ISLAND_PATCHES)
				.addFeature(VEGETAL_DECORATION, MAIN_ISLAND_CHORUS_PLANTS)
				.addFeature(TOP_LAYER_MODIFICATION, MAIN_ISLAND_RING)
				.addFeature(TOP_LAYER_MODIFICATION, MAIN_ISLAND_PORTAL_PLATFORM)
				.addFeature(TOP_LAYER_MODIFICATION, mc("end_platform"))
				.build())
			.build());
	}

	static void bootstrap(BootstrapContext<Biome> context) {

	}
}
