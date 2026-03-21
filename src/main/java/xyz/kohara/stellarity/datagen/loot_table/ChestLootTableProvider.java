package xyz.kohara.stellarity.datagen.loot_table;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;

import net.minecraft.world.item.Items;

import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import xyz.kohara.stellarity.Stellarity;

import java.util.function.BiConsumer;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;

import java.util.HashMap;

import static xyz.kohara.stellarity.utils.LootTableUtils.*;

public class ChestLootTableProvider extends SimpleFabricLootTableProvider {
	private final CompletableFuture<HolderLookup.Provider> registryLookup;

	public ChestLootTableProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup, LootContextParamSets.CHEST);
		this.registryLookup = registryLookup;

	}

	public static final HashMap<String, LootTable.Builder> LOOT_TABLES = new HashMap<>();

	public static void define(HolderLookup.Provider lookup) {
		LOOT_TABLES.put("exit_portal", lootTable()
			.withPool(pool().add(item(Items.END_CRYSTAL).apply(count(num(4)))))
			.withPool(pool().add(item(Items.BONE).apply(countAdd(range(2, 5)))))
			.withPool(pool().add(item(Items.STRING).apply(countAdd(range(2, 5)))))
			.withPool(pool().add(item(Items.GUNPOWDER).apply(countAdd(range(2, 5)))))
			.withPool(pool().setRolls(range(1, 2))
				.add(item(Items.OBSIDIAN).setWeight(2).apply(countAdd(range(1, 3))))
				.add(item(Items.CRYING_OBSIDIAN).setWeight(1).apply(countAdd(range(1, 3))))
			)
			.withPool(pool().add(item(Items.ENCHANTED_BOOK)))
		);
	}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
		define(registryLookup.join());
		for (var entry : LOOT_TABLES.entrySet()) {
			consumer.accept(Stellarity.key(Registries.LOOT_TABLE, entry.getKey()), entry.getValue());
		}
	}
}
