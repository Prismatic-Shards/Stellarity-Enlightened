package prismatic.shards.stellarity.datagen.dynamic;

import net.minecraft.core.ClientAsset;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.animal.chicken.ChickenVariant;
import net.minecraft.world.entity.animal.cow.CowVariant;
import net.minecraft.world.entity.animal.feline.CatVariant;
import net.minecraft.world.entity.animal.frog.FrogVariant;
import net.minecraft.world.entity.animal.pig.PigVariant;
import net.minecraft.world.entity.animal.wolf.WolfVariant;
import net.minecraft.world.entity.variant.BiomeCheck;
import net.minecraft.world.entity.variant.ModelAndTexture;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.key.StellarityAnimalVariants;

public interface AnimalVariantProvider {
	static ClientAsset.ResourceTexture texture(String string) {
		return new ClientAsset.ResourceTexture(Stellarity.id("entity/" + string));
	}

	static <T> ModelAndTexture<T> texture(T model, String string) {
		return new ModelAndTexture<>(model, Stellarity.id("entity/" + string));
	}

	static void bootstrapChicken(BootstrapContext<ChickenVariant> context) {
		var biomes = context.lookup(Registries.BIOME);

		context.register(StellarityAnimalVariants.ENDER_CHICKEN, new ChickenVariant(
			texture(ChickenVariant.ModelType.NORMAL, "chicken/ender_chicken"),
			texture("chicken/ender_chicken_baby"),
			SpawnPrioritySelectors.single(new BiomeCheck(biomes.getOrThrow(BiomeTags.IS_END)), 5)
		));
	}

	static void bootstrapCow(BootstrapContext<CowVariant> context) {
		var biomes = context.lookup(Registries.BIOME);

		context.register(StellarityAnimalVariants.ENDER_COW, new CowVariant(
			texture(CowVariant.ModelType.NORMAL, "cow/ender_cow"),
			texture("cow/ender_cow_baby"),
			SpawnPrioritySelectors.single(new BiomeCheck(biomes.getOrThrow(BiomeTags.IS_END)), 5)
		));
	}

	static void bootstrapPig(BootstrapContext<PigVariant> context) {
		var biomes = context.lookup(Registries.BIOME);

		context.register(StellarityAnimalVariants.ENDER_PIG, new PigVariant(
			texture(PigVariant.ModelType.NORMAL, "pig/ender_pig"),
			texture("pig/ender_pig_baby"),
			SpawnPrioritySelectors.single(new BiomeCheck(biomes.getOrThrow(BiomeTags.IS_END)), 5)
		));
	}

	static void bootstrapFrog(BootstrapContext<FrogVariant> context) {
		var biomes = context.lookup(Registries.BIOME);

		context.register(StellarityAnimalVariants.ENDER_FROG, new FrogVariant(
			texture("frog/ender_frog"),
			SpawnPrioritySelectors.single(new BiomeCheck(biomes.getOrThrow(BiomeTags.IS_END)), 5)
		));
	}

	static void bootstrapWolf(BootstrapContext<WolfVariant> context) {
		var biomes = context.lookup(Registries.BIOME);

		context.register(StellarityAnimalVariants.ENDER_WOLF, new WolfVariant(
			wolfTexture("ender_wolf"),
			wolfBabyTexture("ender_wolf"),
			SpawnPrioritySelectors.single(new BiomeCheck(biomes.getOrThrow(BiomeTags.IS_END)), 5)
		));
	}

	static WolfVariant.AssetInfo wolfTexture(String name) {
		return new WolfVariant.AssetInfo(
			texture("wolf/" + name),
			texture("wolf/" + name + "_tame"),
			texture("wolf/" + name + "_angry")
		);
	}

	static WolfVariant.AssetInfo wolfBabyTexture(String name) {
		return new WolfVariant.AssetInfo(
			texture("wolf/" + name + "_baby"),
			texture("wolf/" + name + "_tame_baby"),
			texture("wolf/" + name + "_angry_baby")
		);
	}

	static void bootstrapCat(BootstrapContext<CatVariant> context) {
		var biomes = context.lookup(Registries.BIOME);

		context.register(StellarityAnimalVariants.ENDER_CAT, new CatVariant(
			texture("cat/ender_cat"),
			texture("cat/ender_cat_baby"),
			SpawnPrioritySelectors.single(new BiomeCheck(biomes.getOrThrow(BiomeTags.IS_END)), 5)
		));
	}


}
