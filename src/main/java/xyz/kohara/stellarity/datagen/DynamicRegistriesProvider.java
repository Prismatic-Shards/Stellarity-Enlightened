package xyz.kohara.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class DynamicRegistriesProvider extends FabricDynamicRegistryProvider {
	public DynamicRegistriesProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}


	@Override
	public void configure(HolderLookup.@NonNull Provider provider, @NonNull Entries entries) {
		ProcessorListProvider.configure(provider, entries);
		ConfiguredFeatureProvider.configure(provider, entries);
		PlacedFeatureProvider.configure(provider, entries);
		BiomeProvider.configure(provider, entries);
	}

	@Override
	public @NonNull String getName() {
		return "All Dynamic Registries Provider";
	}
}
