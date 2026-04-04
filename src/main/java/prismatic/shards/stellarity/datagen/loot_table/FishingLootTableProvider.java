package prismatic.shards.stellarity.datagen.loot_table;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.StellarityItems;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static prismatic.shards.stellarity.utils.LootTableUtils.*;

public class FishingLootTableProvider extends SimpleFabricLootTableSubProvider {


	private final CompletableFuture<HolderLookup.Provider> registryLookup;

	public FishingLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup, LootContextParamSets.FISHING);
		this.registryLookup = registryLookup;

	}


	public static final HashMap<String, LootTable.Builder> LOOT_TABLES = new HashMap<>();


	public static void define(HolderLookup.Provider lookup) {
		LOOT_TABLES.put("void_fishing/fish", lootTable().withPool(pool()
			.add(item(StellarityItems.ENDER_KOI).setWeight(15).apply(count(range(2, 4))))
			.add(item(StellarityItems.CRYSTAL_HEARTFISH).setWeight(4)
			)));


		LOOT_TABLES.put("void_fishing/junk", lootTable().withPool(pool()
			.add(item(Items.CRYING_OBSIDIAN).apply(count(range(1, 3))))
			.add(item(Items.OBSIDIAN).apply(count(range(1, 4))))
			.add(item(Items.END_STONE).apply(count(range(3, 9))))
			.add(item(Items.CHORUS_FRUIT).apply(count(range(1, 5))))
			.add(item(Items.POPPED_CHORUS_FRUIT).setWeight(2).apply(count(range(2, 5))))
			.add(item(Items.PURPUR_BLOCK).apply(count(range(6, 12))))
			.add(item(Items.PHANTOM_MEMBRANE).setWeight(3).apply(count(range(1, 3))))
			.add(item(Items.ENDER_PEARL).setWeight(3).apply(count(range(1, 2))))
			.add(item(Items.SHULKER_SHELL).setWeight(5).apply(count(range(1, 2))))
			.add(item(Items.FIREWORK_STAR).apply(
				component(DataComponents.FIREWORK_EXPLOSION, new FireworkExplosion(
					FireworkExplosion.Shape.SMALL_BALL, new IntArrayList(new int[]{8073150}), new IntArrayList(new int[]{12801229}), false, false
				))
			))
			.add(item(Items.FIREWORK_STAR).apply(component(DataComponents.FIREWORK_EXPLOSION, new FireworkExplosion(
				FireworkExplosion.Shape.SMALL_BALL, new IntArrayList(new int[]{8073150}), new IntArrayList(new int[]{12801229}), false, true
			))))
			.add(item(Items.FIREWORK_STAR).apply(component(DataComponents.FIREWORK_EXPLOSION, new FireworkExplosion(
				FireworkExplosion.Shape.SMALL_BALL, new IntArrayList(new int[]{12801229}), new IntArrayList(new int[]{8073150}), true, false
			))))
			.add(item(Items.PAPER).setWeight(3).apply(count(range(1, 5))))
			.add(item(Items.END_ROD).setWeight(3).apply(count(range(1, 5))))
			.add(item(Items.MAGENTA_DYE))
			.add(item(Items.PURPLE_DYE))
			.add(item(Items.BLACK_DYE))
		));

		LOOT_TABLES.put("void_fishing/treasure", lootTable().withPool(pool()
			.add(item(Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(7).apply(count(range(1, 2))))
			.add(item(Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(7).apply(count(range(1, 2))))
			.add(item(Items.END_CRYSTAL).setWeight(5).apply(count(range(1, 2))))
			.add(item(Items.END_CRYSTAL).setWeight(3).apply(count(range(2, 3))))
			.add(item(StellarityItems.ENDERITE_UPGRADE_SMITHING_TEMPLATE).setWeight(11))
			.add(item(StellarityItems.WINGED_KEY).setWeight(11))
			.add(item(Items.BOOK).setWeight(2).apply(enchantLevels(lookup, 30, 40).withOptions(lookup.lookup(Registries.ENCHANTMENT).orElseThrow().getOrThrow(EnchantmentTags.ON_RANDOM_LOOT))
			))
			.add(item(Items.BOOK).setWeight(5).apply(enchantLevels(lookup, 17, 29).withOptions(lookup.lookup(Registries.ENCHANTMENT).orElseThrow().getOrThrow(EnchantmentTags.ON_RANDOM_LOOT))
			))
		));

		LOOT_TABLES.put("void_fishing/fisher_of_voids", lootTable().withPool(pool().add(item(StellarityItems.FISHER_OF_VOIDS)
			.apply(damage(0.15f, 0.75f))
		)));

		LOOT_TABLES.put("void_fishing/event", lootTable().withPool(pool()
			.add(lootTable(Stellarity.id("void_fishing/junk")).setWeight(15).setQuality(-2))
			.add(lootTable(Stellarity.id("void_fishing/treasure")).setWeight(4).setQuality(2))
			.add(lootTable(Stellarity.id("void_fishing/fish")).setWeight(70).setQuality(-1))
		));
	}


	@Override
	public void generate(@NonNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
		define(registryLookup.join());
		for (var entry : LOOT_TABLES.entrySet()) {
			consumer.accept(Stellarity.key(Registries.LOOT_TABLE, entry.getKey()), entry.getValue());
		}
	}


}
