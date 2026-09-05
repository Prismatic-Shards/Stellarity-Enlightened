package dev.coder2195.stellarity.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;

public interface ConsecrationRecipe extends Recipe<ConsecrationRecipe.Input> {
	record Input(ItemStack itemStack) implements RecipeInput {

		@Override
		public ItemStack getItem(int index) {
			return itemStack;
		}

		@Override
		public int size() {
			return 1;
		}
	}
}
