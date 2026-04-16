package prismatic.shards.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.core.registries.Registries.*;

public class DynamicRegistriesProvider extends FabricDynamicRegistryProvider {
	public DynamicRegistriesProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void configure(HolderLookup.@NonNull Provider provider, @NonNull Entries entries) {
		for (var registry : List.of(NOISE, PAINTING_VARIANT, COW_VARIANT, FROG_VARIANT, CAT_VARIANT, WOLF_VARIANT, PIG_VARIANT, CHICKEN_VARIANT, CONFIGURED_FEATURE, PLACED_FEATURE, BIOME, VILLAGER_TRADE, TRADE_SET)) {
			entries.addAll(provider.lookupOrThrow(registry));
		}
	}

	@Override
	public @NonNull String getName() {
		return "All Dynamic Registries Provider";
	}
}
