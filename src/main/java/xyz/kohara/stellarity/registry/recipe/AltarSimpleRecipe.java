package xyz.kohara.stellarity.registry.recipe;


import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import org.jetbrains.annotations.Nullable;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityRecipeSerializers;

import java.util.*;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.core.HolderLookup;

//? 1.21.1 {
/*import net.minecraft.world.item.Item;
 *///? }

public record AltarSimpleRecipe(@Nullable Identifier id,
                                HashMap<Ingredient, Integer> ingredients,
                                ItemStack result) implements AltarRecipe {

	public AltarSimpleRecipe(@Nullable Identifier id, HashMap<Ingredient, Integer> ingredients,
	                         ItemStack result) {
		this.id = id;
		this.ingredients = ingredients;
		this.result = result;

		//? 1.21.1 {
		/*HashSet<Item> items = new HashSet<>();
		for (var entry : ingredients.keySet()) {
			for (ItemStack stack : entry.getItems()) {
				if (!items.add(stack.getItem())) {
					Stellarity.LOGGER.error("Ingredients are overlapping and may not work correctly. Altar Recipe ID: {}", id);
				}
			}
		}
		*///? } else {
		Stellarity.LOGGER.info("For the sake of convience, recipe validation is skipped. Please confirm on older versions!");
		//? }
	}

	public @Nullable Output craft(List<ItemStack> itemStacks) {
		HashMap<Ingredient, Integer> required = new HashMap<>(ingredients);
		HashMap<ItemStack, Integer> available = new HashMap<>();

		for (var itemStack : itemStacks) {
			int availableCount = itemStack.getCount();

			available.put(itemStack, itemStack.getCount());

			boolean exists = false;

			for (var requirement : ingredients.keySet()) {
				Integer requiredCount = required.get(requirement);

				if (!requirement.test(itemStack)) continue;
				exists = true;
				if (requiredCount == 0) break;
				if (availableCount == requiredCount) {
					required.put(requirement, 0);
					available.remove(itemStack);
					break;
				}

				if (availableCount > requiredCount) {
					required.put(requirement, 0);
					available.put(itemStack, availableCount - requiredCount);

					break;
				}

				required.put(requirement, requiredCount - availableCount);
				available.remove(itemStack);
			}

			if (!exists) return null;
		}

		for (var counts : required.values()) {
			if (counts > 0) return null;
		}

		return new Output(available, result.copy());

	}


	@Override
	public RecipeSerializer<? extends Recipe<Input>> getSerializer() {
		return StellarityRecipeSerializers.ALTAR_SIMPLE;
	}

	public static final StreamCodec<RegistryFriendlyByteBuf, AltarSimpleRecipe> STREAM_CODEC = StreamCodec.of(AltarSimpleRecipe::toNetwork, AltarSimpleRecipe::fromNetwork);
	public static final MapCodec<AltarSimpleRecipe> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			INGREDIENT_CODEC.codec().listOf().fieldOf("ingredients").forGetter((recipe) ->
				recipe.ingredients.entrySet().stream().toList()
			),
			ItemStack.CODEC.fieldOf("result").forGetter(AltarRecipe::result)

		).apply(instance, (ingredients, result) -> {
			HashMap<Ingredient, Integer> ingredientMap = new HashMap<>();

			for (var ingredient : ingredients) {
				ingredientMap.put(ingredient.getKey(), ingredient.getValue());
			}
			return new AltarSimpleRecipe(null, ingredientMap, result);
		}));

	public static AltarSimpleRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
		int size = buf.readInt();
		HashMap<Ingredient, Integer> ingredients = new HashMap<>();
		for (int i = 0; i < size; i++) {
			Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
			int count = buf.readInt();
			ingredients.put(ingredient, count);
		}

		ItemStack itemStack = ItemStack.STREAM_CODEC.decode(buf);
		return new AltarSimpleRecipe(null, ingredients, itemStack);
	}

	public static void toNetwork(RegistryFriendlyByteBuf buf, AltarSimpleRecipe recipe) {
		buf.writeInt(recipe.ingredients.size());
		for (var entry : recipe.ingredients.entrySet()) {
			Ingredient.CONTENTS_STREAM_CODEC.encode(buf, entry.getKey());
			buf.writeInt(entry.getValue());
		}

		ItemStack.STREAM_CODEC.encode(buf, recipe.result);
	}


	//? 1.21.1 {
	/*public static class Serializer implements RecipeSerializer<AltarSimpleRecipe> {
		@Override
		public MapCodec<AltarSimpleRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, AltarSimpleRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
	*///? }


}
