package xyz.kohara.stellarity.registry.item;


import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;

//? 1.20.1 {
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;
//? } else {
/*import net.minecraft.world.item.ItemStack;
import xyz.kohara.stellarity.Stellarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.entity.EquipmentSlotGroup;
*///? }

//? > 1.21.10 {
/*import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import org.jspecify.annotations.Nullable;
*///? }

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import net.minecraft.world.level.Level;
import xyz.kohara.stellarity.registry.StellarityBlocks;
import xyz.kohara.stellarity.registry.StellarityItems;

//~ if > 1.21.10 'ItemNameBlockItem' -> 'BlockItem' {
import net.minecraft.world.item.ItemNameBlockItem;

public class Duskberry extends ItemNameBlockItem {
	//~ }
	public Duskberry(Properties properties) {
		super(StellarityBlocks.DUSKBERRY_BUSH, properties);
	}

	public static MobEffectInstance[] debuffs(int level) {
		if (level == 0) return new MobEffectInstance[0];
		return Stream.of(MobEffects.DARKNESS, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.DIG_SLOWDOWN, MobEffects.WEAKNESS, MobEffects.CONFUSION).map((e) -> new MobEffectInstance(e, level * 12 * 20)).toArray(MobEffectInstance[]::new);
	}

	//? > 1.21.10 {
	/*@Override
	public void inventoryTick(ItemStack itemStack, ServerLevel serverLevel, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
		inventoryTick(itemStack, serverLevel, entity);
	}

	*///? } else {
	@Override
	public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean bl) {
		if (level instanceof ServerLevel serverLevel) inventoryTick(itemStack, serverLevel, entity);
	}
	//? }

	public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity entity) {
		if (!(entity instanceof Player player)) return;

		var effect = player.getEffect(MobEffects.NIGHT_VISION);
		if (effect != null && !effect.endsWithin(11 * 20)) return;

		for (ItemStack held : new ItemStack[]{player.getMainHandItem(), player.getOffhandItem()}) {
			if (!held.is(this)) continue;

			player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 15 * 20));
			return;
		}
	}


	public static final Properties PROPERTIES = StellarityItems.foodProperties(2, 0.4f, Arrays.stream(debuffs(3)).map((e) -> new StellarityItems.EffectChance(e, 1f)).toArray(StellarityItems.EffectChance[]::new))
		//? > 1.21 {
		/*.attributes(new ItemAttributeModifiers(List.of(
			new ItemAttributeModifiers.Entry(
				Attributes.ATTACK_DAMAGE, new AttributeModifier(Stellarity.id("duskberry_attack_damage"), 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.HAND
			),
			new ItemAttributeModifiers.Entry(
				Attributes.MOVEMENT_SPEED, new AttributeModifier(Stellarity.id("duskberry_movement_speed"), 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.HAND
			),
			new ItemAttributeModifiers.Entry(
				Attributes.ATTACK_SPEED, new AttributeModifier(Stellarity.id("duskberry_attack_speed"), 0.12, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.HAND
			),
			new ItemAttributeModifiers.Entry(
				Attributes.SWEEPING_DAMAGE_RATIO, new AttributeModifier(Stellarity.id("duskberry_sweeping_damage"), 0.2, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HAND
			),
			new ItemAttributeModifiers.Entry(
				Attributes.MOVEMENT_EFFICIENCY, new AttributeModifier(Stellarity.id("duskberry_movement_efficiency"), 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.HAND
			)
		)/^? 1.21.1 >> ')'^//^, false^/))
		/^? > 1.21.10 {^//^.component(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT.withHidden(DataComponents.ATTRIBUTE_MODIFIERS, true))^//^? }^/
		*///? }
		;


	//? 1.20.1 {
	public static final Multimap<Attribute, AttributeModifier> ATTRIBUTE_MODIFIERS = ImmutableMultimap.of(
		Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("f6e9a21c-9419-4c78-93d1-86364ecf62fb"), "stellarity:duskberry_attack_damage", 0.1, AttributeModifier.Operation.MULTIPLY_BASE),
		Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("fdbfa0c6-b516-4570-83e5-dfd6a2f640d1"), "stellarity:duskberry_attack_damage", 0.1, AttributeModifier.Operation.MULTIPLY_BASE),
		Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("a948006b-faa2-4548-a1f9-c19cf5c31f17"), "stellarity:duskberry_attack_damage", 0.12, AttributeModifier.Operation.MULTIPLY_BASE)
	);

	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
		if (!equipmentSlot.getType().equals(EquipmentSlot.Type.HAND))
			return super.getDefaultAttributeModifiers(equipmentSlot);
		return ATTRIBUTE_MODIFIERS;
	}
	//? }


	@Override
	public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
		if (livingEntity instanceof Fox fox) {
			fox.addEffect(new MobEffectInstance(MobEffects.POISON, 20 * 20), player);
			return InteractionResult.SUCCESS;
		}
		return super.interactLivingEntity(itemStack, player, livingEntity, interactionHand);
	}
}
