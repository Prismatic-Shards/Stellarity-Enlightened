package xyz.kohara.stellarity.datagen.loot_table;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import xyz.kohara.stellarity.registry.StellarityBlocks;
import xyz.kohara.stellarity.registry.StellarityItems;


import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import static xyz.kohara.stellarity.utils.LootTableUtils.*;

public class BlockLootTableProvider extends FabricBlockLootTableProvider {

	public BlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);

	}


	private static final Block[] DROP_SELF = {
		StellarityBlocks.ASHEN_FROGLIGHT,
		StellarityBlocks.ENDER_DIRT,
		StellarityBlocks.ROOTED_ENDER_DIRT
	};

	@Override
	public void generate() {

		for (Block block : DROP_SELF) {
			dropSelf(block);
		}

		dropOther(StellarityBlocks.ENDER_DIRT_PATH, StellarityBlocks.ENDER_DIRT);


		add(StellarityBlocks.ENDER_GRASS_BLOCK, lootTable().withPool(pool().add(
			AlternativesEntry.alternatives(
				item(StellarityBlocks.ENDER_GRASS_BLOCK).when(hasSilkTouch()),
				applyExplosionCondition(StellarityBlocks.ENDER_DIRT, item(StellarityBlocks.ENDER_DIRT))
			)
		)));

		add(StellarityBlocks.ALTAR_OF_THE_ACCURSED, lootTable()
			.withPool(pool().add(item(Items.CRYING_OBSIDIAN)))
			.withPool(pool().add(item(StellarityItems.SATCHEL_OF_VOIDS)))
		);

		add(StellarityBlocks.DUSKBERRY_BUSH, lootTable().withPool(pool().add(
			item(StellarityItems.DUSKBERRY).apply(count(num(1)))
		)));


	}
}
