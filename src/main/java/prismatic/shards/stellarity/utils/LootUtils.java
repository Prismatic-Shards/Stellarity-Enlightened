package prismatic.shards.stellarity.utils;

import net.minecraft.advancements.criterion.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class LootUtils {
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


		return EntityTypePredicate.of(BuiltInRegistries.ENTITY_TYPE, type);

	}


	public static NbtPredicate nbt(CompoundTag tag) {
		return new NbtPredicate(tag);
	}


	public static ConstantValue num(float num) {
		return new ConstantValue(num);
	}

	public static UniformGenerator range(float min, float max) {
		return new UniformGenerator(num(min), num(max));
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


	public static <T> LootItemConditionalFunction.Builder<?> component(DataComponentType<T> type, T obj) {
		return SetComponentsFunction.setComponent(type, obj);
	}


	public static EnchantWithLevelsFunction.Builder enchant(HolderLookup.Provider provider, int min, int max) {
		return EnchantWithLevelsFunction.enchantWithLevels(provider, range(min, max));
	}

	public static EnchantWithLevelsFunction.Builder enchant(HolderGetter<Enchantment> enchantments, int min, int max) {
		return new EnchantWithLevelsFunction.Builder(range(min, max)).withOptions(enchantments.getOrThrow(EnchantmentTags.ON_RANDOM_LOOT));
	}

	public static LootItemConditionalFunction.Builder<?> damage(float damage) {
		return SetItemDamageFunction.setDamage(num(damage));
	}

	public static LootItemConditionalFunction.Builder<?> damage(float min, float max) {
		return SetItemDamageFunction.setDamage(range(min, max));
	}


	public static LootPoolSingletonContainer.Builder<?> lootTable(Identifier location) {

		return NestedLootTable.lootTableReference(ResourceKey.create(Registries.LOOT_TABLE, location));
	}

	public static LootTable.Builder lootTable() {
		return LootTable.lootTable();
	}

	public static LootItemBlockStatePropertyCondition.Builder blockState(Block block) {
		return new LootItemBlockStatePropertyCondition.Builder(block);
	}

	public static <T extends Comparable<T> & StringRepresentable> StatePropertiesPredicate.Builder hasProperty(Property<@NonNull T> property, T value) {
		return StatePropertiesPredicate.Builder.properties().hasProperty(property, value);
	}

	public static StatePropertiesPredicate.Builder hasProperty(Property<Integer> property, int value) {
		return StatePropertiesPredicate.Builder.properties().hasProperty(property, value);
	}

	public static StatePropertiesPredicate.Builder hasProperty(Property<Boolean> property, boolean value) {
		return StatePropertiesPredicate.Builder.properties().hasProperty(property, value);
	}

	public static Holder<Enchantment> enchant(HolderLookup.Provider registries, ResourceKey<Enchantment> enchantment) {
		return registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(enchantment);
	}

	public static LootItemConditionalFunction.Builder<?> enchantLevel(Holder<Enchantment> enchantment, int i) {
		return ApplyBonusCount.addUniformBonusCount(enchantment, i);
	}

	public static SequenceFunction modifiers(LootItemFunction... functions) {
		return SequenceFunction.of(List.of(functions));
	}

	public static LootItemCondition.Builder chance(float chance) {
		return LootItemRandomChanceCondition.randomChance(chance);
	}

	public static LootItemCondition.Builder chance(NumberProvider numberProvider) {
		return LootItemRandomChanceCondition.randomChance(numberProvider);
	}

	public static EnchantRandomlyFunction.Builder enchant() {
		return EnchantRandomlyFunction.randomEnchantment();
	}

	public static LootItemFunction modifier(ResourceKey<LootItemFunction> key) {
		return FunctionReference.functionReference(key).build();
	}


}
