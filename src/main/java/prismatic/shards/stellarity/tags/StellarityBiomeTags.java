package prismatic.shards.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityBiomeTags {
	TagKey<Biome> SNOWY = id("snowy");
	TagKey<Biome> ALL_STELLARITY = id("all");
	TagKey<Biome> ALL_OUTER = id("all_with_mc");

	static TagKey<Biome> id(String id) {
		return TagKey.create(Registries.BIOME, Stellarity.id(id));
	}
}
