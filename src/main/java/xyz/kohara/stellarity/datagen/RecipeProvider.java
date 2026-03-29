package xyz.kohara.stellarity.datagen;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import net.minecraft.world.item.ItemStackTemplate;

import static net.minecraft.world.item.Items.*;

import net.minecraft.world.item.crafting.Ingredient;
import org.jspecify.annotations.NonNull;
import xyz.kohara.stellarity.Stellarity;

import static xyz.kohara.stellarity.registry.StellarityItems.*;

import xyz.kohara.stellarity.registry.recipe.*;

import java.util.LinkedHashMap;

import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import net.minecraft.data.recipes.RecipeOutput;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;


public class RecipeProvider extends FabricRecipeProvider {

	public RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	public static void altarOfTheAccursed(RecipeOutput output, AltarRecipe recipe) {
		output.accept(


			ResourceKey.create(Registries.RECIPE, recipe.id()),
			recipe, null);
	}


	@Override
	public net.minecraft.data.recipes.@NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider provider, @NonNull RecipeOutput recipeOutput) {
		return new net.minecraft.data.recipes.RecipeProvider(provider, recipeOutput) {
			@Override
			public void buildRecipes() {

				provider.allRegistriesLifecycle().add(Lifecycle.stable());
				RecipeProvider.this.buildRecipes(provider, output);
			}
		};
	}


	public void buildRecipes(HolderLookup.Provider provider, RecipeOutput output) {
		altarOfTheAccursed(output, new AltarSimpleRecipe(
			Stellarity.id("altar_of_the_accursed/lapis_to_amethyst"),
			new LinkedHashMap<>() {{
				put(Ingredient.of(DIAMOND), 1);
				put(Ingredient.of(LAPIS_LAZULI), 1);
			}},
			new ItemStackTemplate(AMETHYST_SHARD)
		));

		altarOfTheAccursed(output, new AltarSimpleRecipe(
			Stellarity.id("altar_of_the_accursed/chorus_plating"),
			new LinkedHashMap<>() {{
				put(Ingredient.of(IRON_INGOT), 1);
				put(Ingredient.of(POPPED_CHORUS_FRUIT), 2);
			}},
			new ItemStackTemplate(CHORUS_PLATING)
		));

		altarOfTheAccursed(output, new AltarSimpleRecipe(
			Stellarity.id("altar_of_the_accursed/enderite_upgrade_smithing_template"),
			new LinkedHashMap<>() {{
				put(Ingredient.of(ENDERITE_UPGRADE_SMITHING_TEMPLATE), 1);
				put(Ingredient.of(ENDERITE_SHARD), 5);
				put(Ingredient.of(PURPUR_BLOCK), 9);
			}},
			new ItemStackTemplate(ENDERITE_UPGRADE_SMITHING_TEMPLATE, 2)
		));

		altarOfTheAccursed(output, new AltarSimpleRecipe(
			Stellarity.id("altar_of_the_accursed/endonomicon"),
			new LinkedHashMap<>() {{
				put(Ingredient.of(ENCHANTED_BOOK), 1);
			}},
			new ItemStackTemplate(ENDONOMICON, 1)
		));

		altarOfTheAccursed(output, new AltarSimpleRecipe(
			Stellarity.id("altar_of_the_accursed/satchel_of_voids"),
			new LinkedHashMap<>() {{
				put(Ingredient.of(BUNDLE), 1);
				put(Ingredient.of(NETHER_STAR), 2);
				put(Ingredient.of(NETHERITE_INGOT), 4);
				put(Ingredient.of(ENDERITE_SHARD), 64);
				put(Ingredient.of(STARLIGHT_SOOT), 64);
			}},
			new ItemStackTemplate(SATCHEL_OF_VOIDS, 1)
		));

		altarOfTheAccursed(output, new AltarSimpleRecipe(
			Stellarity.id("altar_of_the_accursed/satchel_of_voids_alternative"),
			new LinkedHashMap<>() {{
				put(Ingredient.of(LEATHER), 1);
				put(Ingredient.of(STRING), 1);
				put(Ingredient.of(NETHER_STAR), 2);
				put(Ingredient.of(NETHERITE_INGOT), 4);
				put(Ingredient.of(ENDERITE_SHARD), 64);
				put(Ingredient.of(STARLIGHT_SOOT), 64);
			}},
			new ItemStackTemplate(SATCHEL_OF_VOIDS, 1)
		));

		altarOfTheAccursed(output, new AltarUpgradeRecipe(
			Stellarity.id("altar_of_the_accursed/shulker_helmet"),
			Ingredient.of(NETHERITE_HELMET),
			new LinkedHashMap<>() {{
				put(Ingredient.of(ENDERITE_UPGRADE_SMITHING_TEMPLATE), 1);
				put(Ingredient.of(SHULKER_SHELL), 4);
			}},
			new ItemStackTemplate(SHULKER_HELMET)
		));

		altarOfTheAccursed(output, new AltarUpgradeRecipe(
			Stellarity.id("altar_of_the_accursed/shulker_chestplate"),
			Ingredient.of(NETHERITE_CHESTPLATE),
			new LinkedHashMap<>() {{
				put(Ingredient.of(ENDERITE_UPGRADE_SMITHING_TEMPLATE), 1);
				put(Ingredient.of(SHULKER_SHELL), 4);
			}},
			new ItemStackTemplate(SHULKER_CHESTPLATE)
		));

		altarOfTheAccursed(output, new AltarUpgradeRecipe(
			Stellarity.id("altar_of_the_accursed/shulker_leggings"),
			Ingredient.of(NETHERITE_LEGGINGS),
			new LinkedHashMap<>() {{
				put(Ingredient.of(ENDERITE_UPGRADE_SMITHING_TEMPLATE), 1);
				put(Ingredient.of(SHULKER_SHELL), 4);
			}},
			new ItemStackTemplate(SHULKER_LEGGINGS)
		));

		altarOfTheAccursed(output, new AltarUpgradeRecipe(
			Stellarity.id("altar_of_the_accursed/shulker_boots"),
			Ingredient.of(NETHERITE_BOOTS),
			new LinkedHashMap<>() {{
				put(Ingredient.of(ENDERITE_UPGRADE_SMITHING_TEMPLATE), 1);
				put(Ingredient.of(SHULKER_SHELL), 4);
			}},
			new ItemStackTemplate(SHULKER_BOOTS)
		));
	}

	@Override
	public @NonNull String getName() {
		return Stellarity.MOD_ID;
	}

}
