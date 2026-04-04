package prismatic.shards.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import prismatic.shards.stellarity.Stellarity;

public class StellarityBiomeTags {
	public static final TagKey<Biome> SNOWY = bind("snowy");
	public static final TagKey<Biome> PLACEHOLDER_DONT_USE = bind("placeholder_dont_use");

	private static TagKey<Biome> bind(String id) {
		return TagKey.create(Registries.BIOME, Stellarity.id(id));
	}
}
