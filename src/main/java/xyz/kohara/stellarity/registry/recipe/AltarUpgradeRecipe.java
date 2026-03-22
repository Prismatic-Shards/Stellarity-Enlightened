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
import net.minecraft.core.component.DataComponentType;

//? 1.21.1 {
/*import net.minecraft.world.item.Item;
 *///? }


import java.util.*;

public record AltarUpgradeRecipe(@Nullable Identifier id, Ingredient equipment,
                                 HashMap<Ingredient, Integer> ingredients,
                                 ItemStack result) implements AltarRecipe {

	public AltarUpgradeRecipe(@Nullable Identifier id, Ingredient equipment, HashMap<Ingredient, Integer> ingredients,
	                          ItemStack result) {
		this.id = id;
		this.ingredients = ingredients;
		this.result = result;
		this.equipment = equipment;

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
		var patched = availableEquipment.getComponentsPatch();
		for (var component : patched.entrySet()) {
			var value = component.getValue();
			if (value.isEmpty()) continue;
			//noinspection unchecked
			returning.set((DataComponentType<Object>) component.getKey(), component.getValue().get());
		}

		return new Output(available, returning);

	}


	@Override
	public RecipeSerializer<? extends Recipe<Input>> getSerializer() {
		return StellarityRecipeSerializers.ALTAR_UPGRADE;
	}

	public static final StreamCodec<RegistryFriendlyByteBuf, AltarUpgradeRecipe> STREAM_CODEC = StreamCodec.of(AltarUpgradeRecipe::toNetwork, AltarUpgradeRecipe::fromNetwork);

	public static final MapCodec<AltarUpgradeRecipe> CODEC = RecordCodecBuilder.mapCodec(

		instance -> instance.group(
			INGREDIENT_CODEC.codec().listOf().fieldOf("ingredients").forGetter((recipe) ->
				recipe.ingredients.entrySet().stream().toList()
			),
			Ingredient.CODEC.fieldOf("equipment").forGetter(AltarUpgradeRecipe::equipment),
			ItemStack.CODEC.fieldOf("result").forGetter(AltarRecipe::result)
		).apply(instance, (ingredients, equipment, result) -> {
			HashMap<Ingredient, Integer> ingredientMap = new HashMap<>();

			for (var ingredient : ingredients) {
				ingredientMap.put(ingredient.getKey(), ingredient.getValue());
			}
			return new AltarUpgradeRecipe(null, equipment, ingredientMap, result);
		}));

	public static AltarUpgradeRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
		int size = buf.readInt();
		HashMap<Ingredient, Integer> ingredients = new HashMap<>();
		for (int i = 0; i < size; i++) {
			Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
			int count = buf.readInt();
			ingredients.put(ingredient, count);
		}
		Ingredient equipment = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
		ItemStack itemStack = ItemStack.STREAM_CODEC.decode(buf);
		return new AltarUpgradeRecipe(null, equipment, ingredients, itemStack);
	}

	public static void toNetwork(RegistryFriendlyByteBuf buf, AltarUpgradeRecipe recipe) {
		buf.writeInt(recipe.ingredients.size());
		for (var entry : recipe.ingredients.entrySet()) {
			Ingredient.CONTENTS_STREAM_CODEC.encode(buf, entry.getKey());
			buf.writeInt(entry.getValue());
		}
		Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.equipment);
		ItemStack.STREAM_CODEC.encode(buf, recipe.result);
	}

	//? 1.21.1 {
	/*public static class Serializer implements RecipeSerializer<AltarUpgradeRecipe> {
		@Override
		public MapCodec<AltarUpgradeRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, AltarUpgradeRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
	*///? }

}
