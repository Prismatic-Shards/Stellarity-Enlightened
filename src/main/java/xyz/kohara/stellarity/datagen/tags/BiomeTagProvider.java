package xyz.kohara.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import xyz.kohara.stellarity.tags.StellarityBiomeTags;

import java.util.concurrent.CompletableFuture;

public class BiomeTagProvider extends FabricTagProvider<Biome> {

    public BiomeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BIOME, registriesFuture);
    }

    //? < 1.21.11 {
    public FabricTagBuilder builder(
        net.minecraft.tags.TagKey<Biome> tag
    ) {
        return getOrCreateTagBuilder(tag);
    }
    //? }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        builder(StellarityBiomeTags.SNOWY).add(new ResourceKey[]{});
    }
}
