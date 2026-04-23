package prismatic.shards.stellarity.datagen.loot_table;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.util.LootUtil;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static net.minecraft.world.item.Items.*;
import static prismatic.shards.stellarity.key.StellarityLootTables.*;
import static prismatic.shards.stellarity.registry.StellarityItems.*;
import static prismatic.shards.stellarity.util.LootUtil.*;

public class FishingLootTableProvider extends SimpleFabricLootTableSubProvider {


	private final CompletableFuture<HolderLookup.Provider> registryLookup;

	public FishingLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup, LootContextParamSets.FISHING);
		this.registryLookup = registryLookup;

	}


	public static final HashMap<ResourceKey<LootTable>, LootTable.Builder> LOOT_TABLES = new HashMap<>();


	public static void define(HolderLookup.Provider lookup) {
		LOOT_TABLES.put(VOID_FISHING_FISH, lootTable().withPool(pool()
			.add(item(ENDER_KOI).setWeight(15).apply(count(range(2, 4))))
			.add(item(CRYSTAL_HEARTFISH).setWeight(4)
			)));


		LOOT_TABLES.put(VOID_FISHING_JUNK, lootTable().withPool(pool()
			.add(item(CRYING_OBSIDIAN).apply(count(range(1, 3))))
			.add(item(OBSIDIAN).apply(count(range(1, 4))))
			.add(item(END_STONE).apply(count(range(3, 9))))
			.add(item(CHORUS_FRUIT).apply(count(range(1, 5))))
			.add(item(POPPED_CHORUS_FRUIT).setWeight(2).apply(count(range(2, 5))))
			.add(item(PURPUR_BLOCK).apply(count(range(6, 12))))
			.add(item(PHANTOM_MEMBRANE).setWeight(3).apply(count(range(1, 3))))
			.add(item(ENDER_PEARL).setWeight(3).apply(count(range(1, 2))))
			.add(item(SHULKER_SHELL).setWeight(5).apply(count(range(1, 2))))
			.add(item(FIREWORK_STAR).apply(
				component(DataComponents.FIREWORK_EXPLOSION, new FireworkExplosion(
					FireworkExplosion.Shape.SMALL_BALL, new IntArrayList(new int[]{8073150}), new IntArrayList(new int[]{12801229}), false, false
				))
			))
			.add(item(FIREWORK_STAR).apply(component(DataComponents.FIREWORK_EXPLOSION, new FireworkExplosion(
				FireworkExplosion.Shape.SMALL_BALL, new IntArrayList(new int[]{8073150}), new IntArrayList(new int[]{12801229}), false, true
			))))
			.add(item(FIREWORK_STAR).apply(component(DataComponents.FIREWORK_EXPLOSION, new FireworkExplosion(
				FireworkExplosion.Shape.SMALL_BALL, new IntArrayList(new int[]{12801229}), new IntArrayList(new int[]{8073150}), true, false
			))))
			.add(item(PAPER).setWeight(3).apply(count(range(1, 5))))
			.add(item(END_ROD).setWeight(3).apply(count(range(1, 5))))
			.add(item(MAGENTA_DYE))
			.add(item(PURPLE_DYE))
			.add(item(BLACK_DYE))
		));

		LOOT_TABLES.put(VOID_FISHING_TREASURE, lootTable().withPool(pool()
			.add(item(EYE_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(7).apply(count(range(1, 2))))
			.add(item(SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(7).apply(count(range(1, 2))))
			.add(item(END_CRYSTAL).setWeight(5).apply(count(range(1, 2))))
			.add(item(END_CRYSTAL).setWeight(3).apply(count(range(2, 3))))
			.add(item(ENDERITE_UPGRADE_SMITHING_TEMPLATE).setWeight(11))
			.add(item(WINGED_KEY).setWeight(11))
			.add(item(BOOK).setWeight(2).apply(LootUtil.enchant(lookup, 30, 40).withOptions(lookup.lookup(Registries.ENCHANTMENT).orElseThrow().getOrThrow(EnchantmentTags.ON_RANDOM_LOOT))
			))
			.add(item(BOOK).setWeight(5).apply(LootUtil.enchant(lookup, 17, 29).withOptions(lookup.lookup(Registries.ENCHANTMENT).orElseThrow().getOrThrow(EnchantmentTags.ON_RANDOM_LOOT))
			))
		));

		LOOT_TABLES.put(VOID_FISHING_FISHER_OF_VOIDS, lootTable().withPool(pool().add(item(FISHER_OF_VOIDS)
			.apply(damage(0.15f, 0.75f))
		)));

		LOOT_TABLES.put(VOID_FISHING_EVENT, lootTable().withPool(pool()
			.add(lootTable(VOID_FISHING_JUNK).setWeight(15).setQuality(-2))
			.add(lootTable(VOID_FISHING_TREASURE).setWeight(4).setQuality(2))
			.add(lootTable(VOID_FISHING_FISH).setWeight(70).setQuality(-1))
		));
	}


	@Override
	public void generate(@NonNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
		define(registryLookup.join());
		for (var entry : LOOT_TABLES.entrySet()) {
			consumer.accept(entry.getKey(), entry.getValue());
		}
	}


}
