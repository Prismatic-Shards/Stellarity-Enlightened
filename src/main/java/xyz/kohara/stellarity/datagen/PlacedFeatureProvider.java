package xyz.kohara.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import xyz.kohara.stellarity.Stellarity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PlacedFeatureProvider extends FabricDynamicRegistryProvider {
    public PlacedFeatureProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static Holder<PlacedFeature> MAIN_ISLAND_RING;

    public static ResourceKey<PlacedFeature> id(String name) {
        return Stellarity.key(Registries.PLACED_FEATURE, name);
    }

    @Override
    protected void configure(HolderLookup.Provider provider, Entries entries) {
        MAIN_ISLAND_RING = entries.add(
            id("main_island/ring"),
            new PlacedFeature(ConfiguredFeatureProvider.MAIN_ISLAND_RING, List.of(BiomeFilter.biome()))
        );
    }

    @Override
    public String getName() {
        return "stellarity-placed-feature-provider";
    }
}
