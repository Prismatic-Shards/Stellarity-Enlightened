package xyz.kohara.stellarity.datagen.loot_table;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import org.jetbrains.annotations.Nullable;
import xyz.kohara.stellarity.registry.StellarityBlocks;
import xyz.kohara.stellarity.registry.StellarityItems;


//? > 1.21 {
/*import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;
*///? }

import static xyz.kohara.stellarity.utils.LootTableUtils.*;

public class BlockLootTableProvider extends FabricBlockLootTableProvider {

	//? 1.20.1 {
	public BlockLootTableProvider(FabricDataOutput dataOutput) {
		super(dataOutput);
	}
	//? } else {

	/*public BlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);

	}
	*///? }


	private static final Block[] DROP_SELF = {
		StellarityBlocks.ASHEN_FROGLIGHT,
		StellarityBlocks.ENDER_DIRT,
		StellarityBlocks.ROOTED_ENDER_DIRT
	};

	//? 1.20.1
	public @Nullable Object registries = null;

	@Override
	public void generate() {

		for (Block block : DROP_SELF) {
			dropSelf(block);
		}

		dropOther(StellarityBlocks.ENDER_DIRT_PATH, StellarityBlocks.ENDER_DIRT);


		add(StellarityBlocks.ENDER_GRASS_BLOCK, lootTable().withPool(pool().add(
			AlternativesEntry.alternatives(
				item(StellarityBlocks.ENDER_GRASS_BLOCK).when(/*? 1.20.1 {*/HAS_SILK_TOUCH/*? } else { *//*hasSilkTouch()*//*? }*/),
				applyExplosionCondition(StellarityBlocks.ENDER_DIRT, item(StellarityBlocks.ENDER_DIRT))
			)
		)));

		add(StellarityBlocks.ALTAR_OF_THE_ACCURSED, lootTable()
			.withPool(pool().add(item(Items.CRYING_OBSIDIAN)))
			// todo: add satchel here
			.withPool(pool().add(item(StellarityItems.SATCHEL_OF_VOIDS)))
		);

		add(StellarityBlocks.DUSKBERRY_BUSH, lootTable().withPool(pool().add(
			item(StellarityItems.DUSKBERRY).apply(count(num(1)))
		)));


	}
}
