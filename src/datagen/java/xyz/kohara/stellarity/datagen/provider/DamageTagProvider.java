package xyz.kohara.stellarity.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import xyz.kohara.stellarity.registry.StellarityDamageTypes;

//? < 1.21.11 {
import net.minecraft.tags.TagKey;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.FabricTagBuilder;
//? }

import java.util.concurrent.CompletableFuture;

public class DamageTagProvider extends FabricTagProvider<DamageType> {
    public DamageTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.DAMAGE_TYPE, registriesFuture);
    }

    //? < 1.21.11 {
    public FabricTagBuilder builder(
        net.minecraft.tags.TagKey<DamageType> tag
    ) {
        return getOrCreateTagBuilder(tag);
    }
    //? }


    @Override
    protected void addTags(HolderLookup.Provider provider) {

        builder(DamageTypeTags.BYPASSES_COOLDOWN).addOptional(
            StellarityDamageTypes.BRITTLE
        );

//        builder(DamageTypeTags.BYPASSES_ARMOR).addOptional(
//            StellarityDamageTypes.BRITTLE
//        );

        //? > 1.20.1 {
        /*builder(DamageTypeTags.NO_KNOCKBACK).addOptional(
            StellarityDamageTypes.BRITTLE
        );
        *///? }

//        builder(DamageTypeTags.BYPASSES_ENCHANTMENTS).addOptional(
//            StellarityDamageTypes.BRITTLE
//        );

    }
}
