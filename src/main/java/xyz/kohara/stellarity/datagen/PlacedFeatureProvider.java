package xyz.kohara.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import xyz.kohara.stellarity.Stellarity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PlacedFeatureProvider {

    public static Holder<PlacedFeature> MAIN_ISLAND_RING;
    public static Holder<PlacedFeature> MAIN_ISLAND_PORTAL_PLATFORM;

    public static ResourceKey<PlacedFeature> id(String name) {
        return Stellarity.key(Registries.PLACED_FEATURE, name);
    }

    public static void configure(HolderLookup.Provider provider, FabricDynamicRegistryProvider.Entries entries) {
        entries.addAll(provider.lookupOrThrow(Registries.PLACED_FEATURE));
    }

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        MAIN_ISLAND_RING = context.register(
            id("main_island/ring"),
            new PlacedFeature(ConfiguredFeatureProvider.MAIN_ISLAND_RING, List.of(BiomeFilter.biome()))
        );

        MAIN_ISLAND_PORTAL_PLATFORM = context.register(
            id("main_island/portal_platform"),
            new PlacedFeature(ConfiguredFeatureProvider.MAIN_ISLAND_PORTAL_PLATFORM, List.of(BiomeFilter.biome()))
        );
    }
}
