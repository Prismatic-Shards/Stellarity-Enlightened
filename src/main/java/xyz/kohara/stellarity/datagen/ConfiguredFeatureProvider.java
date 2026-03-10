package xyz.kohara.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SpikeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.SpikeConfiguration;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.utils.Constants;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ConfiguredFeatureProvider {
    public static Holder<ConfiguredFeature<?, ?>> MAIN_ISLAND_RING;
    public static Holder<ConfiguredFeature<?, ?>> MAIN_ISLAND_PORTAL_PLATFORM;

    public static ResourceKey<ConfiguredFeature<?, ?>> id(String name) {
        return Stellarity.key(Registries.CONFIGURED_FEATURE, name);
    }

    public static void configure(HolderLookup.Provider provider, FabricDynamicRegistryProvider.Entries entries) {
        entries.addAll(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE));
    }

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        MAIN_ISLAND_RING = context.register(id("main_island/ring"),
            new ConfiguredFeature<>(
                Feature.END_SPIKE,
                new SpikeConfiguration(false, Constants.OBSIDIAN_SPIKES, null)
            ));

        MAIN_ISLAND_PORTAL_PLATFORM = context.register(id("main_island/portal_platform"), new ConfiguredFeature<>(
            Feature.END_SPIKE,
            new SpikeConfiguration(true, List.of(new SpikeFeature.EndSpike(
                0, 0, 16, 70, false
            )), null)
        ));
    }

}
