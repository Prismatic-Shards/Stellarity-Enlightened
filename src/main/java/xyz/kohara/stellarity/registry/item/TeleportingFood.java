package xyz.kohara.stellarity.registry.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import xyz.kohara.stellarity.registry.StellarityItems;


import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.TeleportRandomlyConsumeEffect;


public abstract class TeleportingFood extends Item {
	private final int teleportDiameter;

	public TeleportingFood(Properties properties, int teleportDiameter) {
		super(properties);
		this.teleportDiameter = teleportDiameter;
	}

	public int getTeleportDiameter() {
		return teleportDiameter;
	}


	public static Properties foodProperties(int nutrition, float saturation, boolean alwaysEat, int teleportDiameter, StellarityItems.EffectChance... effectChances) {
		return foodProperties(
			Consumables.defaultFood(),
			nutrition,
			saturation,
			alwaysEat,
			teleportDiameter,
			effectChances
		);
	}

	public static Properties foodProperties(Consumable.Builder consumable, int nutrition, float saturation, boolean alwaysEat, int teleportDiameter, StellarityItems.EffectChance... effectChances) {
		return StellarityItems.foodProperties(
			new Item.Properties(),
			new FoodProperties.Builder(),
			consumable.onConsume(new TeleportRandomlyConsumeEffect(teleportDiameter)),
			nutrition,
			saturation,
			alwaysEat,
			effectChances
		);
	}


	public static Properties foodProperties(int nutrition, float saturation, int teleportDiameter, StellarityItems.EffectChance... effectChances) {
		return foodProperties(
			nutrition,
			saturation,
			false,
			teleportDiameter,
			effectChances
		);
	}


}
