package xyz.kohara.stellarity.datagen.provider.loot_table;

//? if fabric {
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
//? }
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityBlocks;


//? > 1.21 {
/*import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;
*///? }

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static xyz.kohara.stellarity.utils.LootTableUtils.*;

//? if fabric {
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
//? } else {
/*public class BlockLootTableProvider extends BlockLootSubProvider {
    public BlockLootTableProvider() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }
*///? }

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
                item(StellarityBlocks.ENDER_GRASS_BLOCK).when(/*? 1.20.1 {*/HAS_SILK_TOUCH/*? } else { *//*hasSilkTouch()*//*? }*/),
                applyExplosionCondition(StellarityBlocks.ENDER_DIRT, item(StellarityBlocks.ENDER_DIRT))
            )
        )));

        add(StellarityBlocks.ALTAR_OF_THE_ACCURSED, lootTable()
            .withPool(pool().add(item(Items.CRYING_OBSIDIAN)))
            // todo: add satchel here
            .withPool(pool().add(item(Items.CRYING_OBSIDIAN)))
        );


    }
}
