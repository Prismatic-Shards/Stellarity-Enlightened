package prismatic.shards.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryDataLoader;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.datagen.dynamic.BiomeProvider;

import java.util.concurrent.CompletableFuture;

public class DynamicRegistriesProvider extends FabricDynamicRegistryProvider {
	public DynamicRegistriesProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}


	@Override
	public void configure(HolderLookup.@NonNull Provider provider, @NonNull Entries entries) {
		entries.addAll(provider.lookupOrThrow(Registries.PAINTING_VARIANT));
		entries.addAll(provider.lookupOrThrow(Registries.COW_VARIANT));
		entries.addAll(provider.lookupOrThrow(Registries.PIG_VARIANT));
		entries.addAll(provider.lookupOrThrow(Registries.FROG_VARIANT));
		entries.addAll(provider.lookupOrThrow(Registries.CAT_VARIANT));
		entries.addAll(provider.lookupOrThrow(Registries.WOLF_VARIANT));
		entries.addAll(provider.lookupOrThrow(Registries.CHICKEN_VARIANT));
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
