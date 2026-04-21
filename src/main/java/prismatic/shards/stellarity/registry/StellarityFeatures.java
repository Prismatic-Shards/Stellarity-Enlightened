package prismatic.shards.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.feature.DungeonFeature;

public interface StellarityFeatures {
	DungeonFeature DUNGEON = register("dungeon", new DungeonFeature());

	static <T extends Feature<?>> T register(String id, T feature) {
		return Registry.register(BuiltInRegistries.FEATURE, Stellarity.id(id), feature);
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Features");
	}
}
