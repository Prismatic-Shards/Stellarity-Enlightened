package xyz.kohara.stellarity;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import org.jetbrains.annotations.Nullable;
import xyz.kohara.stellarity.datagen.*;
import xyz.kohara.stellarity.datagen.loot_table.*;
import xyz.kohara.stellarity.datagen.tags.*;

public class StellarityDatagen implements DataGeneratorEntrypoint {
	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		Stellarity.LOGGER.info("Bootstrapping configured features");
		registryBuilder.add(Registries.CONFIGURED_FEATURE, ConfiguredFeatureProvider::bootstrap);
		Stellarity.LOGGER.info("Bootstrapping placed features");
		registryBuilder.add(Registries.PLACED_FEATURE, PlacedFeatureProvider::bootstrap);
	}

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModelProvider::new);
		pack.addProvider(AdvancementProvider::new);
		pack.addProvider(ItemTagProvider::new);
		pack.addProvider(RecipeProvider::new);
		pack.addProvider(BlockLootTableProvider::new);
		pack.addProvider(BlockTagProvider::new);
		pack.addProvider(FishingLootTableProvider::new);
		pack.addProvider(DamageTagProvider::new);
		pack.addProvider(EntityTagProvider::new);
		pack.addProvider(BiomeTagProvider::new);
		pack.addProvider(ChestLootTableProvider::new);
		pack.addProvider(DynamicRegistriesProvider::new);


		//? 1.21.1{
		pack.addProvider((fabricDataOutput, completableFuture) -> new EndonomiconBookProvider(fabricDataOutput, fabricDataGenerator, completableFuture.join(), "endonomicon"));
		//? }
	}

	@Override
	public @Nullable String getEffectiveModId() {
		return "stellarity";
	}
}
