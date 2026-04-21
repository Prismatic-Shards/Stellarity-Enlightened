package prismatic.shards.stellarity.datagen.loot_table;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static net.minecraft.world.item.Items.*;
import static prismatic.shards.stellarity.registry.StellarityItems.*;
import static prismatic.shards.stellarity.util.LootUtil.*;

public class ChestLootTableProvider extends SimpleFabricLootTableSubProvider {
	private final CompletableFuture<HolderLookup.Provider> registryLookup;

	public ChestLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup, LootContextParamSets.CHEST);
		this.registryLookup = registryLookup;

	}

	public static final HashMap<String, LootTable.Builder> LOOT_TABLES = new HashMap<>();

	public static void define(HolderLookup.Provider lookup) {
		var enchantments = lookup.lookupOrThrow(Registries.ENCHANTMENT);
		LOOT_TABLES.put("exit_portal", lootTable()
			.withPool(pool().add(item(END_CRYSTAL).apply(count(num(4)))))
			.withPool(pool().add(item(BONE).apply(countAdd(range(2, 5)))))
			.withPool(pool().add(item(STRING).apply(countAdd(range(2, 5)))))
			.withPool(pool().add(item(GUNPOWDER).apply(countAdd(range(2, 5)))))
			.withPool(pool().setRolls(range(1, 2))
				.add(item(OBSIDIAN).setWeight(2).apply(countAdd(range(1, 3))))
				.add(item(CRYING_OBSIDIAN).setWeight(1).apply(countAdd(range(1, 3))))
			)
			.withPool(pool().add(item(ENCHANTED_BOOK)))
		);

		LOOT_TABLES.put("dungeon", lootTable()
			.withPool(pool().add(item(ENDERITE_SHARD).apply(count(range(1, 2)))))
			.withPool(pool().setRolls(range(1, 2))
				.add(item(IRON_INGOT).setWeight(12).apply(count(range(1, 2))))
				.add(item(GOLD_INGOT).setWeight(9).apply(count(range(1, 2))))
			)
			.withPool(pool().setRolls(num(3))
				.add(item(END_CRYSTAL).setWeight(7))
				.add(item(ENDER_PEARL).setWeight(6).apply(count(range(1, 2))))
				.add(item(PHANTOM_ITEM_FRAME).setWeight(6).apply(count(range(1, 2))))
				.add(item(CHORUS_FRUIT).setWeight(8).apply(count(range(1, 2))))
				.add(item(BOOK).setWeight(5).apply(enchant(enchantments, 25, 35)))
				.add(empty().setWeight(2))
				.add(item(ENDER_EYE).setWeight(3).apply(count(range(1, 2))))
			)
			.withPool(pool().add(empty().setWeight(4)).add(item(SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE)))
			.withPool(pool().add(empty().setWeight(9)).add(item(ENDERITE_UPGRADE_SMITHING_TEMPLATE)))
		);
	}

	@Override
	public void generate(@NonNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
		define(registryLookup.join());
		for (var entry : LOOT_TABLES.entrySet()) {
			consumer.accept(Stellarity.key(Registries.LOOT_TABLE, entry.getKey()), entry.getValue());
		}
	}
}
