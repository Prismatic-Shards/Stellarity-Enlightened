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


import static net.minecraft.core.registries.BuiltInRegistries.CREATIVE_MODE_TAB;

public class StellarityCreativeModeTabs {
	public static final ItemLike[] BLOCKS_ITEMS = new ItemLike[]{
		StellarityItems.ASHEN_FROGLIGHT,
		StellarityItems.ENDER_DIRT,
		StellarityItems.ENDER_GRASS_BLOCK,
		StellarityItems.ROOTED_ENDER_DIRT,
		StellarityItems.ENDER_DIRT_PATH,
		StellarityItems.ALTAR_OF_THE_ACCURSED,
		StellarityItems.PHANTOM_ITEM_FRAME,
	};

	public static final ItemLike[] FOOD_ITEMS = new ItemLike[]{
		StellarityItems.CRYSTAL_HEARTFISH,
		StellarityItems.SUSHI,
		StellarityItems.GOLDEN_CHORUS_FRUIT,
		StellarityItems.FRIED_CHORUS_FRUIT,
		StellarityItems.FROZEN_CARPACCIO,
		StellarityItems.ENDERMAN_FLESH,
		StellarityItems.GRILLED_ENDERMAN_FLESH,
		StellarityItems.FLAREFIN_KOI,
		StellarityItems.AMETHYST_BUDFISH,
		StellarityItems.CRIMSON_TIGERFISH,
		StellarityItems.ENDER_KOI,
		StellarityItems.FLESHY_PIRANHA,
		StellarityItems.BUBBLEFISH,
		StellarityItems.PRISMITE,
		StellarityItems.OVERGROWN_COD,
		StellarityItems.SHULKER_BODY,
		StellarityItems.PRISMATIC_SUSHI,
		StellarityItems.SHEPHERDS_PIE,
		StellarityItems.CHORUS_PIE,
		StellarityItems.PHO,
		StellarityItems.ROYAL_JELLY,
		StellarityItems.ROYAL_JELLY_II,
		StellarityItems.DUSKBERRY
	};


	public static final ItemStack[] FOOD_ITEMSTACKS = new ItemStack[]{
		StellarityItems.AMARENE_POTION,
		StellarityItems.BLIND_RAGE_POTION,
		StellarityItems.LONG_BLIND_RAGE_POTION,
		StellarityItems.ENDURANCE_POTION,
		StellarityItems.LONG_ENDURANCE_POTION,
		StellarityItems.STRONG_ENDURANCE_POTION,
		StellarityItems.ENTANGLEMENT_POTION,
		StellarityItems.LONG_ENTANGLEMENT_POTION,
		StellarityItems.STRONG_ENTANGLEMENT_POTION,
		StellarityItems.FROST_CLOUD_POTION,
		StellarityItems.HELLFIRE_TREADER_POTION,
		StellarityItems.LONG_HELLFIRE_TREADER_POTION,
		StellarityItems.STRONG_HELLFIRE_TREADER_POTION,
		StellarityItems.LIFEFORCE_POTION,
		StellarityItems.LONG_LIFEFORCE_POTION,
		StellarityItems.STRONG_LIFEFORCE_POTION,
		StellarityItems.SPELUNKER_POTION,
		StellarityItems.LONG_SPELUNKER_POTION,
		StellarityItems.STRONG_SPELUNKER_POTION,
		StellarityItems.POSEIDONS_NECTAR_POTION,
		StellarityItems.RED_POTION,
		StellarityItems.REGENERAGA_POTION,
		StellarityItems.LONG_REGENERAGA_POTION,
		StellarityItems.STRONG_REGENERAGA_POTION,
		StellarityItems.LUCK_POTION,
	};


	public static final ItemLike[] EQUIPMENT_ITEMS = new ItemLike[]{
		StellarityItems.CALL_OF_THE_VOID,
		StellarityItems.FISHER_OF_VOIDS,
		StellarityItems.TAMARIS
	};

	public static final ItemLike[] INGREDIENT_ITEMS = new ItemLike[]{
		StellarityItems.CHORUS_PLATING,
		StellarityItems.ENDERITE_SHARD,
		StellarityItems.ENDERITE_UPGRADE_SMITHING_TEMPLATE,
		StellarityItems.HALLOWED_INGOT,
		StellarityItems.SAND_RUNE,
		StellarityItems.STARLIGHT_SOOT,
		StellarityItems.GILDED_PURPUR_KEY,
		StellarityItems.PURPUR_KEY,
		StellarityItems.WINGED_KEY,
		StellarityItems.MUSIC_DISC_DEVIANTS_LIGHT_MUSIC_BOX,
		StellarityItems.MUSIC_DISC_FIRES_OF_HOKKAI,
		StellarityItems.MUSIC_DISC_PRECIPICE_STEREO
	};

	public static final ItemLike[] TRINKET_ITEMS = new ItemLike[]{
		StellarityItems.PRISMATIC_PEARL,
		StellarityItems.ENDONOMICON,
		StellarityItems.SATCHEL_OF_VOIDS,
		StellarityItems.DUSKBERRY
	};

	public static final ResourceKey<CreativeModeTab> FOOD_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "food");
	public static final ResourceKey<CreativeModeTab> BLOCKS_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "building_blocks");
	public static final ResourceKey<CreativeModeTab> EQUIPMENT_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "equipment");
	public static final ResourceKey<CreativeModeTab> INGREDIENTS_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "ingredients");
	public static final ResourceKey<CreativeModeTab> TRINKETS_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "trinkets");

	public static final CreativeModeTab FOOD = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(StellarityItems.SUSHI))
		.title(Component.translatable("itemGroup.stellarity.food"))
		.build();
	public static final CreativeModeTab BLOCKS = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(StellarityItems.ENDER_GRASS_BLOCK))
		.title(Component.translatable("itemGroup.stellarity.blocks"))
		.build();
	public static final CreativeModeTab EQUIPMENT = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(StellarityItems.CALL_OF_THE_VOID))
		.title(Component.translatable("itemGroup.stellarity.equipment"))
		.build();

	public static final CreativeModeTab INGREDIENTS = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(StellarityItems.ENDERITE_SHARD))
		.title(Component.translatable("itemGroup.stellarity.ingredients"))
		.build();

	public static final CreativeModeTab TRINKETS = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(StellarityItems.PRISMATIC_PEARL))
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

	public static void register(ResourceKey<CreativeModeTab> key, CreativeModeTab tab, ItemLike[] items, ItemStack[] stacks) {
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab);
		//~ if > 1.21.1 'modifyEntriesEvent' -> 'modifyOutputEvent'
		CreativeModeTabEvents.modifyOutputEvent(key).register(itemGroup -> {
			for (ItemLike item : items) {
				itemGroup.accept(item);
			}

			for (ItemStack stack : stacks) {
				itemGroup.accept(stack.copy());
			}
		});
	}

	public static void register(ResourceKey<CreativeModeTab> key, CreativeModeTab tab, ItemLike[] items) {
		register(key, tab, items, new ItemStack[]{});
	}

}
