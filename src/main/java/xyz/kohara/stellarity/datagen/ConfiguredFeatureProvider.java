package xyz.kohara.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SpikeConfiguration;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.utils.Constants;

import java.util.concurrent.CompletableFuture;

public class ConfiguredFeatureProvider extends FabricDynamicRegistryProvider {
    public ConfiguredFeatureProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static Holder<ConfiguredFeature<?, ?>> MAIN_ISLAND_RING;

    public static ResourceKey<ConfiguredFeature<?, ?>> id(String name) {
        return Stellarity.key(Registries.CONFIGURED_FEATURE, name);
    }

    @Override
    protected void configure(HolderLookup.Provider provider, Entries entries) {
        MAIN_ISLAND_RING = entries.add(
            id("main_island/ring"),
            new ConfiguredFeature<>(
                Feature.END_SPIKE,
                new SpikeConfiguration(false, Constants.OBSIDIAN_SPIKES, null)
            )
        );
    }

    @Override
    public String getName() {
        return "stellarity-configured-feature-provider";
    }
}
