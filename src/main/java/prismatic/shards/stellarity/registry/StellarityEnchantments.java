package prismatic.shards.stellarity.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import prismatic.shards.stellarity.Stellarity;

public class StellarityEnchantments {
	public static final ResourceKey<Enchantment> AMBUSH = register("ambush");
	public static final ResourceKey<Enchantment> CRITICAL_STRIKE = register("critical_strike");
	public static final ResourceKey<Enchantment> DUNE_SPEED = register("dune_speed");
	public static final ResourceKey<Enchantment> LEVITATION_SHOT = register("levitation_shot");
	public static final ResourceKey<Enchantment> PLATED = register("plated");
	public static final ResourceKey<Enchantment> SOARING = register("soaring");
	public static final ResourceKey<Enchantment> VOID_SHOT = register("void_shot");
	public static final ResourceKey<Enchantment> VOID_STRIKE = register("void_strike");

	private static ResourceKey<Enchantment> register(String name) {
		return Stellarity.key(Registries.ENCHANTMENT, name);
	}

	public static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Enchantments");
	}
}

