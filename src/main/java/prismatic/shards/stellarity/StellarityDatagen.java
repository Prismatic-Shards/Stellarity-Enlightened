package prismatic.shards.stellarity;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import org.jetbrains.annotations.Nullable;
import prismatic.shards.stellarity.datagen.*;
import prismatic.shards.stellarity.datagen.dynamic.*;
import prismatic.shards.stellarity.datagen.loot_table.BlockLootTableProvider;
import prismatic.shards.stellarity.datagen.loot_table.ChestLootTableProvider;
import prismatic.shards.stellarity.datagen.loot_table.FishingLootTableProvider;
import prismatic.shards.stellarity.datagen.tags.*;

public class StellarityDatagen implements DataGeneratorEntrypoint {
	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.BIOME, BiomeProvider::bootstrap);
		registryBuilder.add(Registries.CONFIGURED_FEATURE, ConfiguredFeatureProvider::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, PlacedFeatureProvider::bootstrap);
		registryBuilder.add(Registries.VILLAGER_TRADE, VillagerTradeProvider::bootstrap);
		registryBuilder.add(Registries.TRADE_SET, TradeSetProvider::bootstrap);
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
		pack.addProvider((FabricDataGenerator.Pack.Factory<EquipmentAssetProvider>) EquipmentAssetProvider::new);
		pack.addProvider(VillageTradeTagProvider::new);


	}

	@Override
	public @Nullable String getEffectiveModId() {
		return "stellarity";
	}
}
