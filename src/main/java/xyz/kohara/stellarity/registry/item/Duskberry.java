package xyz.kohara.stellarity.registry.item;


import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

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
*///? }

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import xyz.kohara.stellarity.registry.StellarityItems;

public class Duskberry extends Item {
	public Duskberry(Properties properties) {
		super(properties);
	}

	public static MobEffectInstance[] debuffs() {
		return Stream.of(MobEffects.DARKNESS, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.DIG_SLOWDOWN, MobEffects.WEAKNESS, MobEffects.CONFUSION).map((e) -> new MobEffectInstance(e, 36 * 20)).toArray(MobEffectInstance[]::new);
	}

	public static final Properties PROPERTIES = StellarityItems.foodProperties(2, 0.4f, Arrays.stream(debuffs()).map((e) -> new StellarityItems.EffectChance(e, 1f)).toArray(StellarityItems.EffectChance[]::new))
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
