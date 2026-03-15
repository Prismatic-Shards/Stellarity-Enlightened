package xyz.kohara.stellarity.registry.recipe;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.Nullable;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityRecipeSerializers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
//? }

public record AltarUpgradeRecipe(@Nullable ResourceLocation id, Ingredient equipment,
                                 HashMap<Ingredient, Integer> ingredients,
                                 ItemStack result) implements AltarRecipe {

	public AltarUpgradeRecipe(@Nullable ResourceLocation id, Ingredient equipment, HashMap<Ingredient, Integer> ingredients,
	                          ItemStack result) {
		this.id = id;
		this.ingredients = ingredients;
		this.result = result;
		this.equipment = equipment;

		//? < 1.21.9 {
		HashSet<Item> items = new HashSet<>();
		for (var entry : ingredients.keySet()) {
			for (ItemStack stack : entry.getItems()) {
				if (!items.add(stack.getItem())) {
					Stellarity.LOGGER.error("Ingredients are overlapping and may not work correctly. Altar Recipe ID: {}", id);
				}
			}
		}
		//? } else {
		/*Stellarity.LOGGER.info("For the sake of convience, recipe validation is skipped. Please confirm on older versions!");
		 *///? }
	}

	public @Nullable Output craft(List<ItemStack> itemStacks) {
		HashMap<Ingredient, Integer> required = new HashMap<>(ingredients);
		HashMap<ItemStack, Integer> available = new HashMap<>();

		ItemStack availableEquipment = null;
		List<ItemStack> temp = new ArrayList<>(itemStacks.size());
		for (int i = 0; i < itemStacks.size(); i++) {
			ItemStack stack = itemStacks.get(i);

			if (availableEquipment == null && equipment.test(stack)) {
				availableEquipment = stack;
				int count = availableEquipment.getCount();
				if (count > 1) available.put(availableEquipment, count - 1);
				continue;
			}

			temp.add(stack);

		}

		if (availableEquipment == null) return null;

		itemStacks = temp;

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

		var returning = result.copy();
		returning.setTag(availableEquipment.getTag());

		return new Output(available, returning);

	}


	@Override
	public RecipeSerializer<? extends Recipe<Input>> getSerializer() {
		return StellarityRecipeSerializers.ALTAR_UPGRADE;
	}

	//? 1.20.1 {


	@Override
	public void toJson(JsonObject jsonObject) {
		AltarRecipe.super.toJson(jsonObject);
		jsonObject.add("equipment", equipment.toJson());
	}

	//? }

	public static class Serializer implements RecipeSerializer<AltarUpgradeRecipe> {
		//? 1.20.1 {
		@Override
		public AltarUpgradeRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
			HashMap<Ingredient, Integer> ingredients = AltarRecipe.ingredientsFromJson(GsonHelper.getAsJsonArray(jsonObject, "ingredients"));
			ItemStack itemStack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
			Ingredient equipment = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "equipment"));

			return new AltarUpgradeRecipe(resourceLocation, equipment, ingredients, itemStack);
		}


		@Override
		public AltarUpgradeRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf buf) {
			int size = buf.readInt();
			HashMap<Ingredient, Integer> ingredients = new HashMap<>();
			for (int i = 0; i < size; i++) {
				Ingredient ingredient = Ingredient.fromNetwork(buf);
				int count = buf.readInt();
				ingredients.put(ingredient, count);
			}
			Ingredient equipment = Ingredient.fromNetwork(buf);
			ItemStack itemStack = buf.readItem();

			return new AltarUpgradeRecipe(resourceLocation, equipment, ingredients, itemStack);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buf, AltarUpgradeRecipe recipe) {
			buf.writeInt(recipe.ingredients.size());
			for (var entry : recipe.ingredients.entrySet()) {
				entry.getKey().toNetwork(buf);
				buf.writeInt(entry.getValue());
			}
			recipe.equipment.toNetwork(buf);
			buf.writeItem(recipe.result);
		}

		//? } else {
	/*private static final MapCodec<Map.Entry<Ingredient, Integer>> INGREDIENT_CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
		Ingredient.CODEC.fieldOf("ingredient").forGetter(Map.Entry::getKey),
		Codec.INT.optionalFieldOf("count", 1).forGetter(Map.Entry::getValue)
		).apply(instance, Map::entry)
	);

	public static final StreamCodec<RegistryFriendlyByteBuf, AltarSimpleRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

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


	@Override
	public MapCodec<AltarSimpleRecipe> codec() {
		return CODEC;
	}

	@Override
	public StreamCodec<RegistryFriendlyByteBuf, AltarSimpleRecipe> streamCodec() {
		return STREAM_CODEC;
	}

	private static AltarSimpleRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
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

	private static void toNetwork(RegistryFriendlyByteBuf buf, AltarSimpleRecipe recipe) {
		buf.writeInt(recipe.ingredients.size());
		for (var entry : recipe.ingredients.entrySet()) {
		Ingredient.CONTENTS_STREAM_CODEC.encode(buf, entry.getKey());
		buf.writeInt(entry.getValue());
		}

		ItemStack.STREAM_CODEC.encode(buf, recipe.result);
	}
	*///? }
	}

	//? < 1.21 {
	//? } else {

	/*@Override
	public ItemStack assemble(Input recipeInput, HolderLookup.Provider provider) {
	return getResultItem(provider);
	}

	*///? }
}
