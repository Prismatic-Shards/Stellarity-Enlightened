package prismatic.shards.stellarity.util;

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
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jspecify.annotations.NonNull;

import java.util.List;

public interface LootUtil {
	static LootPool.Builder pool() {
		return new LootPool.Builder();
	}

	static LootItemCondition.Builder onDamage(DamageSourcePredicate.Builder predicate) {
		return DamageSourceCondition.hasDamageSource(predicate);
	}

	static InvertedLootItemCondition not(LootItemCondition term) {
		return new InvertedLootItemCondition(term);
	}

	static DamageSourcePredicate.Builder damage() {
		return new DamageSourcePredicate.Builder();
	}

	static EntityPredicate.Builder entity() {
		return new EntityPredicate.Builder();
	}

	static EntityTypePredicate entityType(EntityType<?> type) {


		return EntityTypePredicate.of(BuiltInRegistries.ENTITY_TYPE, type);

	}


	static NbtPredicate nbt(CompoundTag tag) {
		return new NbtPredicate(tag);
	}


	static ConstantValue num(float num) {
		return new ConstantValue(num);
	}

	static UniformGenerator range(float min, float max) {
		return new UniformGenerator(num(min), num(max));
	}

	static LootPoolSingletonContainer.Builder<?> item(ItemLike i) {
		return LootItem.lootTableItem(i);
	}

	static LootItemConditionalFunction.Builder<?> count(NumberProvider provider) {
		return SetItemCountFunction.setCount(provider);
	}

	static LootItemConditionalFunction.Builder<?> countAdd(NumberProvider provider) {
		return SetItemCountFunction.setCount(provider, true);
	}


	static <T> LootItemConditionalFunction.Builder<?> component(DataComponentType<T> type, T obj) {
		return SetComponentsFunction.setComponent(type, obj);
	}


	static EnchantWithLevelsFunction.Builder enchant(HolderLookup.Provider provider, int min, int max) {
		return EnchantWithLevelsFunction.enchantWithLevels(provider, range(min, max));
	}

	static EnchantWithLevelsFunction.Builder enchant(HolderGetter<Enchantment> enchantments, int min, int max) {
		return new EnchantWithLevelsFunction.Builder(range(min, max)).withOptions(enchantments.getOrThrow(EnchantmentTags.ON_RANDOM_LOOT));
	}

	static LootItemConditionalFunction.Builder<?> damage(float damage) {
		return SetItemDamageFunction.setDamage(num(damage));
	}

	static LootItemConditionalFunction.Builder<?> damage(float min, float max) {
		return SetItemDamageFunction.setDamage(range(min, max));
	}


	static LootPoolSingletonContainer.Builder<?> lootTable(Identifier location) {

		return NestedLootTable.lootTableReference(ResourceKey.create(Registries.LOOT_TABLE, location));
	}

	static LootPoolSingletonContainer.Builder<?> lootTable(ResourceKey<LootTable> location) {
		return NestedLootTable.lootTableReference(location);
	}

	static LootTable.Builder lootTable() {
		return LootTable.lootTable();
	}

	static LootItemBlockStatePropertyCondition.Builder blockState(Block block) {
		return new LootItemBlockStatePropertyCondition.Builder(block);
	}

	static <T extends Comparable<T> & StringRepresentable> StatePropertiesPredicate.Builder hasProperty(Property<@NonNull T> property, T value) {
		return StatePropertiesPredicate.Builder.properties().hasProperty(property, value);
	}

	static StatePropertiesPredicate.Builder hasProperty(Property<Integer> property, int value) {
		return StatePropertiesPredicate.Builder.properties().hasProperty(property, value);
	}

	static StatePropertiesPredicate.Builder hasProperty(Property<Boolean> property, boolean value) {
		return StatePropertiesPredicate.Builder.properties().hasProperty(property, value);
	}

	static Holder<Enchantment> enchant(HolderLookup.Provider registries, ResourceKey<Enchantment> enchantment) {
		return registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(enchantment);
	}

	static LootItemConditionalFunction.Builder<?> enchantLevel(Holder<Enchantment> enchantment, int i) {
		return ApplyBonusCount.addUniformBonusCount(enchantment, i);
	}

	static SequenceFunction modifiers(LootItemFunction... functions) {
		return SequenceFunction.of(List.of(functions));
	}

	static LootItemCondition.Builder chance(float chance) {
		return LootItemRandomChanceCondition.randomChance(chance);
	}

	static LootItemCondition.Builder chance(NumberProvider numberProvider) {
		return LootItemRandomChanceCondition.randomChance(numberProvider);
	}

	static EnchantRandomlyFunction.Builder enchant() {
		return EnchantRandomlyFunction.randomEnchantment();
	}

	static LootItemFunction modifier(ResourceKey<LootItemFunction> key) {
		return FunctionReference.functionReference(key).build();
	}

	static LootItemCondition entityProperty(LootContext.EntityTarget target, EntityPredicate.Builder predicate) {
		return LootItemEntityPropertyCondition.hasProperties(target, predicate.build()).build();
	}

	static LootItemCondition entityProperty(EntityPredicate.Builder predicate) {
		return entityProperty(LootContext.EntityTarget.THIS, predicate);
	}

	static LootPoolSingletonContainer.Builder<?> empty() {
		return EmptyLootItem.emptyItem();
	}


}
