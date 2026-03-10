package xyz.kohara.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class DynamicRegistriesProvider extends FabricDynamicRegistryProvider {
    public DynamicRegistriesProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }


    @Override
    public void configure(HolderLookup.Provider provider, Entries entries) {
        ProcessorListProvider.configure(provider, entries);
        ConfiguredFeatureProvider.configure(provider, entries);
        PlacedFeatureProvider.configure(provider, entries);
        BiomeProvider.configure(provider, entries);
    }

    @Override
    public String getName() {
        return "All Dynamic Registries Provider";
    }
}
