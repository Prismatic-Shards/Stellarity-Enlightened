package prismatic.shards.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.recipe.AltarDyeRecipe;
import prismatic.shards.stellarity.registry.recipe.AltarSimpleRecipe;
import prismatic.shards.stellarity.registry.recipe.AltarUpgradeRecipe;

public interface StellarityRecipeSerializers {
	RecipeSerializer<AltarSimpleRecipe> ALTAR_SIMPLE = registerSerializer("altar_of_the_accursed_simple", new RecipeSerializer<>(AltarSimpleRecipe.CODEC, AltarSimpleRecipe.STREAM_CODEC));
	RecipeSerializer<AltarUpgradeRecipe> ALTAR_UPGRADE = registerSerializer("altar_of_the_accursed_upgrade", new RecipeSerializer<>(AltarUpgradeRecipe.CODEC, AltarUpgradeRecipe.STREAM_CODEC));
	RecipeSerializer<AltarDyeRecipe> ALTAR_DYE = registerSerializer("altar_of_the_accursed_dye", new RecipeSerializer<>(AltarDyeRecipe.CODEC, AltarDyeRecipe.STREAM_CODEC));

	private static <T extends Recipe<?>> RecipeSerializer<T> registerSerializer(final String id, RecipeSerializer<T> serializer) {
		return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Stellarity.id(id), serializer);
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Recipe Serializers");
	}
}
