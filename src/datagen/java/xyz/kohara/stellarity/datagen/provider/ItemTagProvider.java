package xyz.kohara.stellarity.datagen.provider;

import net.minecraft.core.HolderLookup;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.Item;
//? if forge {
import net.minecraftforge.common.data.ExistingFileHelper;
//? }
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityItemTags;
import xyz.kohara.stellarity.registry.StellarityItems;

import java.util.concurrent.CompletableFuture;

import net.minecraft.tags.ItemTags;

public class ItemTagProvider extends TagsProvider<Item> {
    public ItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture/*? if !fabric {*/, ExistingFileHelper existingFileHelper/*?}*/) {
        super(output, Registries.ITEM, registriesFuture, Stellarity.MOD_ID/*? if !fabric {*/, existingFileHelper/*?}*/);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        getOrCreateRawBuilder(StellarityItemTags.FISHES)
            .addTag(BuiltInRegistries.ITEM.getKey(StellarityItems.AMETHYST_BUDFISH))
            .addTag(BuiltInRegistries.ITEM.getKey(StellarityItems.BUBBLEFISH))
            .addTag(BuiltInRegistries.ITEM.getKey(StellarityItems.CRIMSON_TIGERFISH))
            .addTag(BuiltInRegistries.ITEM.getKey(StellarityItems.ENDER_KOI))
            .addTag(BuiltInRegistries.ITEM.getKey(StellarityItems.FLAREFIN_KOI))
            .addTag(BuiltInRegistries.ITEM.getKey(StellarityItems.CRYSTAL_HEARTFISH));

        getOrCreateRawBuilder(ItemTags.FISHES).addTag(StellarityItemTags.FISHES.location());
    }
}
