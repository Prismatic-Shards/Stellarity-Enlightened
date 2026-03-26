package xyz.kohara.stellarity.registry.recipe;


import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;
import xyz.kohara.stellarity.interface_injection.ExtItemEntity;
import xyz.kohara.stellarity.registry.StellarityRecipeTypes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.minecraft.core.particles.ColorParticleOption;


public interface AltarRecipe extends Recipe<AltarRecipe.Input> {
	class Input extends SimpleContainer implements RecipeInput {
		@Override
		public int size() {
			return this.items.size();
		}
	}

	record Output(HashMap<ItemStack, Integer> remainders, ItemStack result) {
	}

	@Nullable Output craft(List<ItemStack> itemStacks);

	HashMap<Ingredient, Integer> ingredients();

	ItemStackTemplate result();

	Identifier id();

	@Override
	default @NonNull PlacementInfo placementInfo() {
		return PlacementInfo.NOT_PLACEABLE;
	}

	@Override
	default @NonNull RecipeBookCategory recipeBookCategory() {
		return RecipeBookCategories.CRAFTING_MISC;
	}


	@Override
	default @NonNull RecipeType<? extends Recipe<Input>> getType() {
		return StellarityRecipeTypes.ALTAR_RECIPE;
	}


	@Override
	default boolean matches(Input container, @NonNull Level level) {
		return craft(container.items) == null;
	}

	static void handleItems(ServerLevel serverLevel, double x, double y, double z, boolean locked) {

		List<ItemEntity> itemEntities = serverLevel.getEntitiesOfClass(ItemEntity.class, new AABB(
			x - 0.5, y + 0.75d - 0.5, z - 0.5,
			x + 0.5, y + 0.75d + 0.5, z + 0.5
		), entity -> entity.stellarity$getItemMode() != ExtItemEntity.ItemMode.RESULT);

		Player player = serverLevel.getNearestPlayer(x, y, z, 10, false);

		if (locked) {
			if (!itemEntities.isEmpty() && player instanceof ServerPlayer serverPlayer) {
				serverPlayer.connection.send(
					new ClientboundSetActionBarTextPacket(Component.translatable("message.stellarity.altar_of_the_accursed_locked").withStyle(ChatFormatting.DARK_PURPLE))
				);
			}


			return;
		}


		List<ItemStack> itemStacks = itemEntities.stream().map(ItemEntity::getItem).toList();
		ExtItemEntity.ItemMode itemMode = player != null && player.isCrouching() ? ExtItemEntity.ItemMode.DEFAULT : ExtItemEntity.ItemMode.CRAFTING;

		for (var entity : itemEntities) {
			if (!entity.stellarity$getItemMode().equals(itemMode)) entity.stellarity$setItemMode(itemMode);
		}

		if (itemEntities.isEmpty()) return;


		AltarRecipe.Output output = null;


		if (itemMode == ExtItemEntity.ItemMode.CRAFTING) {


			var allRecipes = serverLevel.getServer().getRecipeManager().getAllOfType(StellarityRecipeTypes.ALTAR_RECIPE);

			for (var recipeHolder : allRecipes) {
				var recipe = recipeHolder.value();


				output = recipe.craft(itemStacks);
				if (output != null) {
					break;
				}
			}
		}

		if (output == null) return;

		for (var entity : itemEntities) {
			entity.stellarity$updateResults(output.remainders());
		}

		ItemEntity resultItem = new ItemEntity(serverLevel, x, y + 0.75, z, output.result());
		resultItem.stellarity$setItemMode(ExtItemEntity.ItemMode.RESULT);
		serverLevel.addFreshEntity(resultItem);


		serverLevel.sendParticles(ColorParticleOption.create(ParticleTypes.FLASH, -1), x, y + 1, z, 1, 0, 0, 0, 0);

		serverLevel.sendParticles(ParticleTypes.END_ROD, x, y + 1, z, 17, 0, 0, 0, 0.13);
	}

	@Override
	default boolean showNotification() {
		return false;
	}

	MapCodec<Map.Entry<Ingredient, Integer>> INGREDIENT_CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			Ingredient.CODEC.fieldOf("ingredient").forGetter(Map.Entry::getKey),
			Codec.INT.optionalFieldOf("count", 1).forGetter(Map.Entry::getValue)
		).apply(instance, Map::entry)
	);


	@Override
	default @NonNull ItemStack assemble(Input recipeInput) {
		return result().create();
	}


	@Override
	default @NonNull String group() {
		return "";
	}


}
