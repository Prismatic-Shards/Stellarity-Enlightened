package prismatic.shards.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityBiomeTags {
	TagKey<Biome> SNOWY = id("snowy");
	TagKey<Biome> PLACEHOLDER_DONT_USE = id("placeholder_dont_use");

	static TagKey<Biome> id(String id) {
		return TagKey.create(Registries.BIOME, Stellarity.id(id));
	}
}
