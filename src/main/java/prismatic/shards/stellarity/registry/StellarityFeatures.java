package prismatic.shards.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.feature.DungeonFeature;
import prismatic.shards.stellarity.registry.feature.FreezeWaterFeature;
import prismatic.shards.stellarity.registry.feature.SpikeFeature;
import prismatic.shards.stellarity.registry.feature.configuration.DungeonFeatureConfiguration;
import prismatic.shards.stellarity.registry.feature.configuration.SpikeFeatureConfiguration;

public interface StellarityFeatures {
	DungeonFeature DUNGEON = register("dungeon", new DungeonFeature(DungeonFeatureConfiguration.CODEC));
	FreezeWaterFeature FREEZE_WATER = register("freeze_water", new FreezeWaterFeature());
	SpikeFeature SPIKE = register("spike", new SpikeFeature(SpikeFeatureConfiguration.CODEC));

	static <T extends Feature<?>> T register(String id, T feature) {
		return Registry.register(BuiltInRegistries.FEATURE, Stellarity.id(id), feature);
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Features");
	}
}
