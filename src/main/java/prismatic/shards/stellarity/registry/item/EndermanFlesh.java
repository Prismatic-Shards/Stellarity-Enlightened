package prismatic.shards.stellarity.registry.item;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import prismatic.shards.stellarity.registry.StellarityItems;


public class EndermanFlesh extends TeleportingFood {
	private static final int TELEPORT_DIAMETER = 16;

	public EndermanFlesh(Properties properties) {
		super(properties, TELEPORT_DIAMETER);
	}

	public static final Properties PROPERTIES = TeleportingFood.foodProperties(4, 0.8f, TELEPORT_DIAMETER, new StellarityItems.EffectChance(new MobEffectInstance(MobEffects.HUNGER, 40 * 20, 0), 0.8f));

}