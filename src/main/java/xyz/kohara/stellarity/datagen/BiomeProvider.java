package xyz.kohara.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import xyz.kohara.stellarity.Stellarity;
//? >= 1.21.11 {
/*import net.minecraft.world.attribute.EnvironmentAttributes;
*///? }


public class BiomeProvider {

	public static Holder<Biome> THE_END;

	public static MobSpawnSettings.Builder addSpawn(MobSpawnSettings.Builder builder, MobCategory category, EntityType<?> entityType, int weight, int min, int max) {
		return builder.addSpawn(category,
			/*? >= 1.21.11 {*/ /*weight, *//*?} */
			new MobSpawnSettings.SpawnerData(entityType,/*? < 1.21.11 {*/ weight, /*?} */ min, max)
		);
	}

	public static void configure(HolderLookup.Provider provider, FabricDynamicRegistryProvider.Entries entries) {
		int skyColor = 0x000000;
		int fogColor = 0x000000;
		int waterFogColor = 0x41307e;
		THE_END = entries.add(Stellarity.mcKey(Registries.BIOME, "the_end"), new Biome.BiomeBuilder()
			.temperature(0.8f)
			.downfall(0.4f)
			.hasPrecipitation(false)
			.specialEffects(new BiomeSpecialEffects.Builder()
				.grassColorOverride(0xdedede)
				.foliageColorOverride(0xc2c2c2)
				.waterColor(0x62529e)
				//? < 1.21.11 {
				.skyColor(skyColor)
				.fogColor(fogColor)
				.waterFogColor(waterFogColor)
				//? }
				.build())

			//? > 1.21.10 {
			/*.setAttribute(EnvironmentAttributes.SKY_COLOR, skyColor)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, fogColor)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, fogColor)
			*///? }
			.mobSpawnSettings(addSpawn(new MobSpawnSettings.Builder(), MobCategory.MONSTER, EntityType.ENDERMAN, 10, 4, 4)
				.addMobCharge(EntityType.ENDERMAN, 0.75, 1)
				.build())
			.generationSettings(new BiomeGenerationSettings.Builder(
				provider.lookupOrThrow(Registries.PLACED_FEATURE),
				provider.lookupOrThrow(Registries.CONFIGURED_CARVER)
			)
				.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, PlacedFeatureProvider.MAIN_ISLAND_RING)
				.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, PlacedFeatureProvider.MAIN_ISLAND_PORTAL_PLATFORM)
				.build())
			.build());
	}
}
