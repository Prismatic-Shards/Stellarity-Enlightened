package xyz.kohara.stellarity.registry;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ItemLike;
import xyz.kohara.stellarity.Stellarity;


import java.util.function.Supplier;

import static net.minecraft.core.registries.BuiltInRegistries.CREATIVE_MODE_TAB;
import static xyz.kohara.stellarity.registry.StellarityItems.*;

public class StellarityCreativeModeTabs {
	public static final ItemLike[] BLOCKS_ITEMS = new ItemLike[]{
		ASHEN_FROGLIGHT,
		ENDER_DIRT,
		ENDER_GRASS_BLOCK,
		ROOTED_ENDER_DIRT,
		ENDER_DIRT_PATH,
		ALTAR_OF_THE_ACCURSED,
		PHANTOM_ITEM_FRAME,
	};

	public static final ItemLike[] FOOD_ITEMS = new ItemLike[]{
		CRYSTAL_HEARTFISH,
		SUSHI,
		GOLDEN_CHORUS_FRUIT,
		FRIED_CHORUS_FRUIT,
		FROZEN_CARPACCIO,
		ENDERMAN_FLESH,
		GRILLED_ENDERMAN_FLESH,
		FLAREFIN_KOI,
		AMETHYST_BUDFISH,
		CRIMSON_TIGERFISH,
		ENDER_KOI,
		FLESHY_PIRANHA,
		BUBBLEFISH,
		PRISMITE,
		OVERGROWN_COD,
		SHULKER_BODY,
		PRISMATIC_SUSHI,
		SHEPHERDS_PIE,
		CHORUS_PIE,
		PHO,
		ROYAL_JELLY,
		ROYAL_JELLY_II,
		DUSKBERRY
	};


	public static final Supplier<ItemStack>[] FOOD_ITEMSTACKS = new Supplier[]{
		AMARENE_POTION,
		BLIND_RAGE_POTION,
		LONG_BLIND_RAGE_POTION,
		ENDURANCE_POTION,
		LONG_ENDURANCE_POTION,
		STRONG_ENDURANCE_POTION,
		ENTANGLEMENT_POTION,
		LONG_ENTANGLEMENT_POTION,
		STRONG_ENTANGLEMENT_POTION,
		FROST_CLOUD_POTION,
		HELLFIRE_TREADER_POTION,
		LONG_HELLFIRE_TREADER_POTION,
		STRONG_HELLFIRE_TREADER_POTION,
		LIFEFORCE_POTION,
		LONG_LIFEFORCE_POTION,
		STRONG_LIFEFORCE_POTION,
		SPELUNKER_POTION,
		LONG_SPELUNKER_POTION,
		STRONG_SPELUNKER_POTION,
		POSEIDONS_NECTAR_POTION,
		RED_POTION,
		REGENERAGA_POTION,
		LONG_REGENERAGA_POTION,
		STRONG_REGENERAGA_POTION,
		LUCK_POTION,
	};


	public static final ItemLike[] EQUIPMENT_ITEMS = new ItemLike[]{
		CALL_OF_THE_VOID,
		FISHER_OF_VOIDS,
		TAMARIS,
		SHULKER_HELMET,
		SHULKER_CHESTPLATE,
		SHULKER_LEGGINGS,
		SHULKER_BOOTS
	};

	public static final ItemLike[] INGREDIENT_ITEMS = new ItemLike[]{
		CHORUS_PLATING,
		ENDERITE_SHARD,
		ENDERITE_UPGRADE_SMITHING_TEMPLATE,
		HALLOWED_INGOT,
		SAND_RUNE,
		STARLIGHT_SOOT,
		GILDED_PURPUR_KEY,
		PURPUR_KEY,
		WINGED_KEY,
		MUSIC_DISC_DEVIANTS_LIGHT_MUSIC_BOX,
		MUSIC_DISC_FIRES_OF_HOKKAI,
		MUSIC_DISC_PRECIPICE_STEREO
	};

	public static final ItemLike[] TRINKET_ITEMS = new ItemLike[]{
		PRISMATIC_PEARL,
		ENDONOMICON,
		SATCHEL_OF_VOIDS,
		DUSKBERRY
	};

	public static final ResourceKey<CreativeModeTab> FOOD_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "food");
	public static final ResourceKey<CreativeModeTab> BLOCKS_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "building_blocks");
	public static final ResourceKey<CreativeModeTab> EQUIPMENT_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "equipment");
	public static final ResourceKey<CreativeModeTab> INGREDIENTS_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "ingredients");
	public static final ResourceKey<CreativeModeTab> TRINKETS_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "trinkets");

	public static final CreativeModeTab FOOD = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(SUSHI))
		.title(Component.translatable("itemGroup.stellarity.food"))
		.build();
	public static final CreativeModeTab BLOCKS = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(ENDER_GRASS_BLOCK))
		.title(Component.translatable("itemGroup.stellarity.blocks"))
		.build();
	public static final CreativeModeTab EQUIPMENT = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(CALL_OF_THE_VOID))
		.title(Component.translatable("itemGroup.stellarity.equipment"))
		.build();

	public static final CreativeModeTab INGREDIENTS = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(ENDERITE_SHARD))
		.title(Component.translatable("itemGroup.stellarity.ingredients"))
		.build();

	public static final CreativeModeTab TRINKETS = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(PRISMATIC_PEARL))
		.title(Component.translatable("itemGroup.stellarity.trinkets"))
		.build();

	public static void init() {
		register(FOOD_KEY, FOOD, FOOD_ITEMS, FOOD_ITEMSTACKS);
		register(BLOCKS_KEY, BLOCKS, BLOCKS_ITEMS);
		register(EQUIPMENT_KEY, EQUIPMENT, EQUIPMENT_ITEMS);
		register(INGREDIENTS_KEY, INGREDIENTS, INGREDIENT_ITEMS);
		register(TRINKETS_KEY, TRINKETS, TRINKET_ITEMS);

		Stellarity.LOGGER.info("Registering Stellarity Creative Mode Tabs");

	}

	public static void register(ResourceKey<CreativeModeTab> key, CreativeModeTab tab, ItemLike[] items, Supplier<ItemStack>[] stacks) {
		var creativeModeTab = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab);

		CreativeModeTabEvents.modifyOutputEvent(key).register(itemGroup -> {
			for (ItemLike item : items) {
				itemGroup.accept(item);
			}

			for (Supplier<ItemStack> stack : stacks) {
				itemGroup.accept(stack.get());
			}
		});


	}

	public static void register(ResourceKey<CreativeModeTab> key, CreativeModeTab tab, ItemLike[] items) {
		register(key, tab, items, new Supplier[0]);
	}

}
