package prismatic.shards.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class DynamicRegistriesProvider extends FabricDynamicRegistryProvider {
	public DynamicRegistriesProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}


	@Override
	public void configure(HolderLookup.@NonNull Provider provider, @NonNull Entries entries) {
		entries.addAll(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE));
		entries.addAll(provider.lookupOrThrow(Registries.PLACED_FEATURE));
		entries.addAll(provider.lookupOrThrow(Registries.BIOME));
		BiomeProvider.configure(provider, entries);
		entries.addAll(provider.lookupOrThrow(Registries.VILLAGER_TRADE));
		entries.addAll(provider.lookupOrThrow(Registries.TRADE_SET));
	}

	@Override
	public @NonNull String getName() {
		return "All Dynamic Registries Provider";
	}
}
