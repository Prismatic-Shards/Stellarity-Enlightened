package xyz.kohara.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import xyz.kohara.stellarity.tags.StellarityDamageTypeTags;
import xyz.kohara.stellarity.registry.StellarityDamageTypes;


import java.util.concurrent.CompletableFuture;

public class DamageTagProvider extends FabricTagsProvider<DamageType> {
	public DamageTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, Registries.DAMAGE_TYPE, registriesFuture);
	}


	@Override
	protected void addTags(HolderLookup.Provider provider) {

		builder(DamageTypeTags.BYPASSES_COOLDOWN).addOptional(
			StellarityDamageTypes.BRITTLE
		);

		builder(StellarityDamageTypeTags.MELEE).add(DamageTypes.PLAYER_ATTACK, DamageTypes.MOB_ATTACK, DamageTypes.MOB_ATTACK).addOptional(StellarityDamageTypes.PRISMEMBER);

		builder(DamageTypeTags.NO_KNOCKBACK).addOptional(
			StellarityDamageTypes.BRITTLE
		);

	}
}
