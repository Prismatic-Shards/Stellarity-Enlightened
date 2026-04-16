package prismatic.shards.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.level.biome.Biomes.*;
import static prismatic.shards.stellarity.key.StellarityBiomes.*;
import static prismatic.shards.stellarity.tags.StellarityBiomeTags.*;

public class BiomeTagProvider extends FabricTagsProvider<Biome> {

	public BiomeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, Registries.BIOME, registriesFuture);
	}


	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.@NonNull Provider provider) {
		builder(SNOWY).add(FROZEN_SPIKES, FROSTED_VALLEY, FROZEN_MARSH, HALLOWED_TUNDRA, FROZEN_SHRUBLAND);
		builder(ALL_STELLARITY).add(AMETHYST_FOREST, ASHFALL_DELTAS, CRYSTAL_CRAGS, END_SHRUBLAND, END_WILDS, ENDER_WASTES, ENDLESS_DUNES, FIERY_HILLS, FLESH_TUNDRA, FROSTED_VALLEY, FROZEN_MARSH, FROZEN_SHRUBLAND, FROZEN_SPIKES, HALLOWED_TUNDRA, PRISMARINE_FOREST, THE_HALLOW, THE_NEST, WARPED_MARSH);
		builder(ALL_OUTER).forceAddTag(ALL_STELLARITY).add(END_BARRENS, END_HIGHLANDS, END_MIDLANDS, SMALL_END_ISLANDS);
		builder(ConventionalBiomeTags.IS_END).forceAddTag(ALL_STELLARITY);
		builder(BiomeTags.IS_END).forceAddTag(ALL_STELLARITY);
	}
}
