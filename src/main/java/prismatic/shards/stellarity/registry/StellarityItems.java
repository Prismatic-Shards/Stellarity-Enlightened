package prismatic.shards.stellarity.registry;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.key.StellarityJukeboxSongs;
import prismatic.shards.stellarity.registry.item.*;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public interface StellarityItems {

	Item ENDER_DIRT = registerBlock("ender_dirt", StellarityBlocks.ENDER_DIRT);
	Item ENDER_GRASS_BLOCK = registerBlock("ender_grass_block", StellarityBlocks.ENDER_GRASS_BLOCK);
	Item ASHEN_FROGLIGHT = registerBlock("ashen_froglight", StellarityBlocks.ASHEN_FROGLIGHT);
	Item ROOTED_ENDER_DIRT = registerBlock("rooted_ender_dirt", StellarityBlocks.ROOTED_ENDER_DIRT);
	Item ENDER_DIRT_PATH = registerBlock("ender_dirt_path", StellarityBlocks.ENDER_DIRT_PATH);
	Item ALTAR_OF_THE_ACCURSED = registerBlock("altar_of_the_accursed", StellarityBlocks.ALTAR_OF_THE_ACCURSED);

	Item CALL_OF_THE_VOID = register("call_of_the_void", CallOfTheVoid::new, CallOfTheVoid.PROPERTIES);
	Item FISHER_OF_VOIDS = register("fisher_of_voids", FisherOfVoids::new, FisherOfVoids.PROPERTIES);

	Item SUSHI = register("sushi", Item::new, basicFood(4, 2.4f));
	Item GOLDEN_CHORUS_FRUIT = register("golden_chorus_fruit", GoldenChorusFruit::new, GoldenChorusFruit.PROPERTIES);
	Item FRIED_CHORUS_FRUIT = register("fried_chorus_fruit", FriedChorusFruit::new, FriedChorusFruit.PROPERTIES);
	Item FROZEN_CARPACCIO = register("frozen_carpaccio", Item::new, basicFood(7, 8.4f));
	Item ENDERMAN_FLESH = register("enderman_flesh", EndermanFlesh::new, EndermanFlesh.PROPERTIES);
	Item CRYSTAL_HEARTFISH = register("crystal_heartfish", CrystalHeartfish::new, CrystalHeartfish.PROPERTIES);
	Item GRILLED_ENDERMAN_FLESH = register("grilled_enderman_flesh", Item::new, basicFood(6, 9.6f));
	Item FLAREFIN_KOI = register("flarefin_koi", Item::new, foodProperties(4, 0.8f, new EffectChance(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 16 * 20))));
	Item AMETHYST_BUDFISH = register("amethyst_budfish", Item::new, new Item.Properties());
	Item CRIMSON_TIGERFISH = register("crimson_tigerfish", Item::new, foodProperties(1, 0.2f,
		new EffectChance(new MobEffectInstance(MobEffects.HUNGER, 30 * 20)),
		new EffectChance(new MobEffectInstance(MobEffects.POISON, 20 * 20))));
	Item ENDER_KOI = register("ender_koi", Item::new, basicFood(1, 0.6f));
	Item FLESHY_PIRANHA = register("fleshy_piranha", Item::new, foodProperties(1, 0.2f,
		new EffectChance(new MobEffectInstance(MobEffects.HUNGER, 30 * 20)),
		new EffectChance(new MobEffectInstance(MobEffects.POISON, 20 * 20)))
	);
	Item BUBBLEFISH = register("bubblefish", Item::new, foodProperties(0, 0, new EffectChance(new MobEffectInstance(MobEffects.WATER_BREATHING, 20 * 20))));
	Item PRISMITE = register("prismite", Item::new, foodProperties(3, 1.8f, new EffectChance(new MobEffectInstance(MobEffects.REGENERATION, 5 * 20))));
	Item OVERGROWN_COD = register("overgrown_cod", Item::new,
		foodProperties(1, 0.2f, new EffectChance(new MobEffectInstance(

			MobEffects.SLOWNESS


			, 3 * 20, 2))));
	Item SHULKER_BODY = register("shulker_body", ShulkerBody::new, ShulkerBody.PROPERTIES);
	Item PRISMATIC_SUSHI = register("prismatic_sushi", Item::new, foodProperties(4, 2.4f, true, new EffectChance(new MobEffectInstance(MobEffects.HEALTH_BOOST, 40 * 20))));
	Item SHEPHERDS_PIE = register("shepherds_pie", Item::new,
		foodProperties(20, 20f, true,
			new EffectChance(new MobEffectInstance(

				MobEffects.HEALTH_BOOST


				, 20, 2)),
			new EffectChance(new MobEffectInstance(MobEffects.REGENERATION, 64 * 20, 1))
		));
	Item CHORUS_PIE = register("chorus_pie", Item::new, foodProperties(8, 4.8f));
	Item PHANTOM_ITEM_FRAME = register("phantom_item_frame", PhantomItemFrameItem::new, PhantomItemFrameItem.PROPERTIES);

	Item PHO = register("pho",
		Item::new,
		foodProperties(new Item.Properties().stacksTo(1).craftRemainder(Items.BOWL), new FoodProperties.Builder()


			, Consumables.defaultFood()

			, 13, 20f, true,
			new EffectChance(new MobEffectInstance(MobEffects.ABSORPTION, 150 * 20)),
			new EffectChance(new MobEffectInstance(MobEffects.STRENGTH, 150 * 20)),
			new EffectChance(new MobEffectInstance(MobEffects.REGENERATION, 32 * 20))
		)
			.usingConvertsTo(Items.BOWL));

	Item TAMARIS = register("tamaris", Tamaris::new, Tamaris.PROPERTIES);

	Item CHORUS_PLATING = register("chorus_plating", Item::new, new Item.Properties());
	Item ENDERITE_SHARD = register("enderite_shard", Item::new, new Item.Properties());
	Item ENDERITE_UPGRADE_SMITHING_TEMPLATE = register("enderite_upgrade_smithing_template", (properties) -> new SmithingTemplateItem(
		Component.translatable("item.stellarity.enderite_upgrade_smithing_template.applies_to").withStyle(ChatFormatting.BLUE),
		Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.4"), Component.translatable("item.stellarity.hallowed_ingot")).withStyle(ChatFormatting.BLUE),
		Component.translatable("item.stellarity.enderite_upgrade_smithing_template.upgrade").withStyle(ChatFormatting.GRAY),
		Component.empty(),
		List.of(),
		List.of(), properties
	) {


		@Override
		public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext tooltipContext, @NonNull TooltipDisplay tooltipDisplay, @NonNull Consumer<Component> consumer, @NonNull TooltipFlag tooltipFlag) {
			super.appendHoverText(itemStack, tooltipContext, tooltipDisplay, consumer, tooltipFlag);
			consumer.accept(CommonComponents.space().append(Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.4"), Component.translatable("item.stellarity.chorus_plating")).withStyle(ChatFormatting.BLUE)));
			consumer.accept(CommonComponents.space().append(Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.4"), Component.translatable("item.minecraft.shulker_shell")).withStyle(ChatFormatting.BLUE)));
			consumer.accept(CommonComponents.space().append(Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.8"), Component.translatable("block.minecraft.cherry_leaves")).withStyle(ChatFormatting.BLUE)));
		}


	}, new Item.Properties());

	Item HALLOWED_INGOT = register("hallowed_ingot", Item::new, new Item.Properties());
	Item SAND_RUNE = register("sand_rune", Item::new, new Item.Properties());
	Item STARLIGHT_SOOT = register("starlight_soot", Item::new, new Item.Properties());
	Item GILDED_PURPUR_KEY = register("gilded_purpur_key", Item::new, new Item.Properties());
	Item PURPUR_KEY = register("purpur_key", Item::new, new Item.Properties());
	Item WINGED_KEY = register("winged_key", Item::new, new Item.Properties());

	Item PRISMATIC_PEARL = register("prismatic_pearl", PrismaticPearlItem::new, PrismaticPearlItem.PROPERTIES);
	Item ENDONOMICON = register("endonomicon", Endonomicon::new, Endonomicon.PROPERTIES);

	Item MUSIC_DISC_DEVIANTS_LIGHT_MUSIC_BOX = register("music_disc_deviants_light_music_box",
		Item::new, new Item.Properties().stacksTo(1).jukeboxPlayable(StellarityJukeboxSongs.DEVIANTS_LIGHT_MUSIC_BOX)
	);


	Item MUSIC_DISC_FIRES_OF_HOKKAI = register("music_disc_fires_of_hokkai",
		Item::new, new Item.Properties().stacksTo(1).jukeboxPlayable(StellarityJukeboxSongs.FIRES_OF_HOKKAI)
	);

	Item MUSIC_DISC_PRECIPICE_STEREO = register("music_disc_precipice_stereo",
		Item::new, new Item.Properties().stacksTo(1).jukeboxPlayable(StellarityJukeboxSongs.PRECIPICE_STEREO)
	);


	Supplier<ItemStack> AMARENE_POTION = createPotion(StellarityPotions.AMARENE);

	Supplier<ItemStack> BLIND_RAGE_POTION = createPotion(StellarityPotions.BLIND_RAGE);
	Supplier<ItemStack> LONG_BLIND_RAGE_POTION = createPotion(StellarityPotions.LONG_BLIND_RAGE);

	Supplier<ItemStack> ENDURANCE_POTION = createPotion(StellarityPotions.ENDURANCE);
	Supplier<ItemStack> LONG_ENDURANCE_POTION = createPotion(StellarityPotions.LONG_ENDURANCE);
	Supplier<ItemStack> STRONG_ENDURANCE_POTION = createPotion(StellarityPotions.STRONG_ENDURANCE);

	Supplier<ItemStack> ENTANGLEMENT_POTION = createSplashPotion(StellarityPotions.ENTANGLEMENT);
	Supplier<ItemStack> LONG_ENTANGLEMENT_POTION = createSplashPotion(StellarityPotions.LONG_ENTANGLEMENT);
	Supplier<ItemStack> STRONG_ENTANGLEMENT_POTION = createSplashPotion(StellarityPotions.STRONG_ENTANGLEMENT);

	Supplier<ItemStack> FROST_CLOUD_POTION = createLingeringPotion(StellarityPotions.FROST_CLOUD);

	Supplier<ItemStack> HELLFIRE_TREADER_POTION = createPotion(StellarityPotions.HELLFIRE_TREADER);
	Supplier<ItemStack> LONG_HELLFIRE_TREADER_POTION = createPotion(StellarityPotions.LONG_HELLFIRE_TREADER);
	Supplier<ItemStack> STRONG_HELLFIRE_TREADER_POTION = createPotion(StellarityPotions.STRONG_HELLFIRE_TREADER);

	Supplier<ItemStack> LIFEFORCE_POTION = createPotion(StellarityPotions.LIFEFORCE);
	Supplier<ItemStack> LONG_LIFEFORCE_POTION = createPotion(StellarityPotions.LONG_LIFEFORCE);
	Supplier<ItemStack> STRONG_LIFEFORCE_POTION = createPotion(StellarityPotions.STRONG_LIFEFORCE);

	Supplier<ItemStack> SPELUNKER_POTION = createPotion(StellarityPotions.SPELUNKER);
	Supplier<ItemStack> LONG_SPELUNKER_POTION = createPotion(StellarityPotions.LONG_SPELUNKER);
	Supplier<ItemStack> STRONG_SPELUNKER_POTION = createPotion(StellarityPotions.STRONG_SPELUNKER);

	Supplier<ItemStack> POSEIDONS_NECTAR_POTION = createPotion(StellarityPotions.POSEIDONS_NECTAR);
	Supplier<ItemStack> RED_POTION = createPotion(StellarityPotions.RED);

	Supplier<ItemStack> REGENERAGA_POTION = createPotion(StellarityPotions.REGENERAGA);
	Supplier<ItemStack> LONG_REGENERAGA_POTION = createPotion(StellarityPotions.LONG_REGENERAGA);
	Supplier<ItemStack> STRONG_REGENERAGA_POTION = createPotion(StellarityPotions.STRONG_REGENERAGA);

	Supplier<ItemStack> LUCK_POTION = createPotion(StellarityPotions.LUCK);

	Item ROYAL_JELLY = register("royal_jelly", RoyalJelly::new,
		foodProperties(RoyalJelly.PROPERTIES, new FoodProperties.Builder()


			, Consumables.defaultFood().sound(SoundEvents.HONEY_DRINK)

			, 6, 3.6f, true,
			new EffectChance(new MobEffectInstance(MobEffects.ABSORPTION, 60 * 20))
		)

			.usingConvertsTo(Items.GLASS_BOTTLE)

	);

	Item ROYAL_JELLY_II = register("royal_jelly_ii", RoyalJelly::new,
		foodProperties(RoyalJelly.PROPERTIES, new FoodProperties.Builder()


			, Consumables.defaultFood()

			, 6, 3.6f, true,
			new EffectChance(new MobEffectInstance(MobEffects.ABSORPTION, 30 * 20, 2))
		)

			.usingConvertsTo(Items.GLASS_BOTTLE)

	);

	Item SATCHEL_OF_VOIDS = register("satchel_of_voids", SatchelOfVoids::new, SatchelOfVoids.PROPERTIES);
	Item DUSKBERRY = register("duskberry", Duskberry::new, Duskberry.PROPERTIES);

	Item SHULKER_HELMET = register("shulker_helmet", Item::new, new Item.Properties().humanoidArmor(StellarityArmorMaterials.SHULKER, ArmorType.HELMET).attributes(StellarityArmorMaterials.createShulkerAttributes(StellarityArmorMaterials.SHULKER, ArmorType.HELMET)));
	Item SHULKER_CHESTPLATE = register("shulker_chestplate", Item::new, new Item.Properties().humanoidArmor(StellarityArmorMaterials.SHULKER, ArmorType.CHESTPLATE).attributes(StellarityArmorMaterials.createShulkerAttributes(StellarityArmorMaterials.SHULKER, ArmorType.CHESTPLATE)));
	Item SHULKER_LEGGINGS = register("shulker_leggings", Item::new, new Item.Properties().humanoidArmor(StellarityArmorMaterials.SHULKER, ArmorType.LEGGINGS).attributes(StellarityArmorMaterials.createShulkerAttributes(StellarityArmorMaterials.SHULKER, ArmorType.LEGGINGS)));
	Item SHULKER_BOOTS = register("shulker_boots", Item::new, new Item.Properties().humanoidArmor(StellarityArmorMaterials.SHULKER, ArmorType.BOOTS).attributes(StellarityArmorMaterials.createShulkerAttributes(StellarityArmorMaterials.SHULKER, ArmorType.BOOTS)));


	static Supplier<ItemStack> createPotion(Holder<Potion> potion) {
		return () -> PotionContents.createItemStack(Items.POTION, potion);
	}

	static Supplier<ItemStack> createSplashPotion(Holder<Potion> potion) {
		return () -> PotionContents.createItemStack(Items.SPLASH_POTION, potion);
	}

	static Supplier<ItemStack> createLingeringPotion(Holder<Potion> potion) {
		return () -> PotionContents.createItemStack(Items.LINGERING_POTION, potion);
	}

	static Item registerBlock(String name, Block block) {
		return registerBlock(name, block, new Item.Properties());
	}

	static Item registerBlock(String name, Block block, Item.Properties settings) {
		ResourceKey<Item> itemKey = Stellarity.key(Registries.ITEM, name);

		settings = settings.useBlockDescriptionPrefix().setId(itemKey);

		Item item = new BlockItem(block, settings);

		Registry.register(BuiltInRegistries.ITEM, itemKey, item);

		return item;
	}

	static Item register(String name, Function<Item.Properties, Item> itemFactory) {
		return register(name, itemFactory, new Item.Properties());
	}

	static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
		ResourceKey<Item> itemKey = Stellarity.key(Registries.ITEM, name);

		settings.setId(itemKey);


		Item item = itemFactory.apply(settings);
		Registry.register(BuiltInRegistries.ITEM, itemKey, item);

		return item;
	}

	record EffectChance(MobEffectInstance effect, float chance) {
		public EffectChance(MobEffectInstance effect) {
			this(effect, 1.0f);
		}
	}


	static Item.Properties foodProperties(Item.Properties properties, FoodProperties.Builder foodProperties, Consumable.Builder consumable, int nutrition, float saturation, boolean alwaysEat, EffectChance... effectChances) {
		foodProperties = foodProperties
			.nutrition(nutrition)
			.saturationModifier(saturation);


		if (alwaysEat) {
			foodProperties = foodProperties.alwaysEdible();
		}


		for (EffectChance ec : effectChances) {
			consumable = consumable.onConsume(new ApplyStatusEffectsConsumeEffect(ec.effect, ec.chance));
		}
		return properties.food(foodProperties.build(), consumable.build());


	}


	static Item.Properties foodProperties(Item.Properties properties, FoodProperties.Builder foodProperties,
	                                      int nutrition, float saturation, boolean alwaysEat, EffectChance... effectChances) {
		return foodProperties(properties, foodProperties, Consumables.defaultFood(), nutrition, saturation, alwaysEat, effectChances);
	}


	static Item.Properties foodProperties(int nutrition, float saturation, boolean alwaysEat, EffectChance... effectChances) {
		return foodProperties(new Item.Properties(), new FoodProperties.Builder(), nutrition, saturation, alwaysEat, effectChances);
	}

	static Item.Properties foodProperties(int nutrition, float saturation, EffectChance... effectChances) {
		return foodProperties(nutrition, saturation, false, effectChances);
	}

	static Item.Properties basicFood(int nutrition, float saturation) {
		return foodProperties(nutrition, saturation, false);
	}


	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Items");


	}
}