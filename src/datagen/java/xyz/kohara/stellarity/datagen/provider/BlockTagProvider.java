package xyz.kohara.stellarity.datagen.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
//? if forge {
/*import net.minecraftforge.common.data.ExistingFileHelper;
*///? }
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityBlocks;

import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends TagsProvider<Block> {
    public BlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture/*? if !fabric {*//*, ExistingFileHelper existingFileHelper*//*?}*/) {
        super(output, Registries.BLOCK, registriesFuture/*? if !fabric {*//*, Stellarity.MOD_ID, existingFileHelper*//*?}*/);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateRawBuilder(BlockTags.MINEABLE_WITH_SHOVEL)
            .addElement(BuiltInRegistries.BLOCK.getKey(StellarityBlocks.ENDER_DIRT_PATH))
            .addElement(BuiltInRegistries.BLOCK.getKey(StellarityBlocks.ENDER_DIRT))
            .addElement(BuiltInRegistries.BLOCK.getKey(StellarityBlocks.ENDER_GRASS_BLOCK))
            .addElement(BuiltInRegistries.BLOCK.getKey(StellarityBlocks.ROOTED_ENDER_DIRT));
        
        getOrCreateRawBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .addElement(BuiltInRegistries.BLOCK.getKey(StellarityBlocks.ALTAR_OF_THE_ACCURSED));
    }
}
