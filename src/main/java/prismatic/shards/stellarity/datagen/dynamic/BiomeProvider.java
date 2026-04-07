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
	static void configure(HolderLookup.Provider provider, FabricDynamicRegistryProvider.Entries entries) {

	}

	static void bootstrap(BootstrapContext<Biome> context) {

	}
}
