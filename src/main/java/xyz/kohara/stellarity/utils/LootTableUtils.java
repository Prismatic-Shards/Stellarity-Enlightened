package xyz.kohara.stellarity.utils;

import net.minecraft.advancements.critereon.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;

import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

//? 1.20.1 {
import net.minecraft.nbt.TagParser;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
//? } else {
/*import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
	*///? }
//? > 1.21.10 {
/*import net.minecraft.core.registries.BuiltInRegistries;
 *///? }


import net.minecraft.resources.ResourceLocation;

public class LootTableUtils {
	public static LootPool.Builder pool() {
		return new LootPool.Builder();
	}

	public static LootItemCondition.Builder onDamage(DamageSourcePredicate.Builder predicate) {
		return DamageSourceCondition.hasDamageSource(predicate);
	}

	public static DamageSourcePredicate.Builder damage() {
		return new DamageSourcePredicate.Builder();
	}

	public static EntityPredicate.Builder entity() {
		return new EntityPredicate.Builder();
	}

	public static EntityTypePredicate entityType(EntityType<?> type) {
		//? <= 1.21.1 {
		return EntityTypePredicate.of(type);
		//? } else {
		/*return EntityTypePredicate.of(BuiltInRegistries.ENTITY_TYPE, type);
		 *///? }
	}


	public static NbtPredicate nbt(CompoundTag tag) {
		return new NbtPredicate(tag);
	}


	public static ConstantValue num(float num) {
		return /*? 1.20.1 { */ ConstantValue.exactly(num) /*? } else { *//*new ConstantValue(num) *//*? } */;
	}

	public static UniformGenerator range(float min, float max) {
		return    /*? 1.20.1 { */ UniformGenerator.between(min, max) /*? } else { *//*new UniformGenerator(num(min), num(max)) *//*? } */;
	}

	public static LootPoolSingletonContainer.Builder<?> item(ItemLike i) {
		return LootItem.lootTableItem(i);
	}

	public static LootItemConditionalFunction.Builder<?> count(NumberProvider provider) {
		return SetItemCountFunction.setCount(provider);
	}

	public static LootItemConditionalFunction.Builder<?> countAdd(NumberProvider provider) {
		return SetItemCountFunction.setCount(provider, true);
	}

	//? 1.20.1 {
	public static LootItemConditionalFunction.Builder<?> setNbt(String string) {
		try {
			//noinspection deprecation
			return SetNbtFunction.setTag(TagParser.parseTag(string));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//? } else {
	/*public static <T> LootItemConditionalFunction.Builder<?> component(DataComponentType<T> type, T obj) {
		return SetComponentsFunction.setComponent(type, obj);
	}

	*///? }

	public static EnchantWithLevelsFunction.Builder enchantLevels(/*? > 1.21 { *//*HolderLookup.Provider provider, *//*? }*/ int min, int max) {
		return EnchantWithLevelsFunction.enchantWithLevels(/*? > 1.21 {*/ /*provider, *//*? } */ range(min, max));
	}

	public static LootItemConditionalFunction.Builder<?> damage(float damage) {
		return SetItemDamageFunction.setDamage(num(damage));
	}

	public static LootItemConditionalFunction.Builder<?> damage(float min, float max) {
		return SetItemDamageFunction.setDamage(range(min, max));
	}


	public static LootPoolSingletonContainer.Builder<?> lootTable(ResourceLocation location) {
		//? 1.20.1 {
		return LootTableReference.lootTableReference(location);
		 //? } else {
		/*return NestedLootTable.lootTableReference(ResourceKey.create(Registries.LOOT_TABLE, location));
		*///? }
	}

	public static LootTable.Builder lootTable() {
		return LootTable.lootTable();
	}

	public static LootItemBlockStatePropertyCondition.Builder blockState(Block block) {
		return new LootItemBlockStatePropertyCondition.Builder(block);
	}

	public static <T extends Comparable<T> & StringRepresentable> StatePropertiesPredicate.Builder hasProperty(Property<T> property, T value) {
		return StatePropertiesPredicate.Builder.properties().hasProperty(property, value);
	}

	public static StatePropertiesPredicate.Builder hasProperty(Property<Integer> property, int value) {
		return StatePropertiesPredicate.Builder.properties().hasProperty(property, value);
	}

	public static StatePropertiesPredicate.Builder hasProperty(Property<Boolean> property, boolean value) {
		return StatePropertiesPredicate.Builder.properties().hasProperty(property, value);
	}

	public static /*? 1.20.1 {*/Enchantment/*? } else {*//*Holder<Enchantment>*//*? }*/ enchant(/*? 1.20.1 {*/Object never, Enchantment enchantment/*? } else {*//*HolderLookup.Provider registries, ResourceKey<Enchantment> enchantment*//*? }*/) {
		//? 1.20.1 {
		return enchantment;
		 //? } else {
		/*return registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(enchantment);
		*///? }
	}

	public static LootItemConditionalFunction.Builder<?> uniform(/*? 1.20.1 {*/Enchantment/*? } else {*//*Holder<Enchantment>*//*? }*/ enchantment, int i) {
		return ApplyBonusCount.addUniformBonusCount(enchantment, i);
	}


}
