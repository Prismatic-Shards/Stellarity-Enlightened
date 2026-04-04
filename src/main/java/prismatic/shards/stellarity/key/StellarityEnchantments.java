package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityEnchantments {
	ResourceKey<Enchantment> AMBUSH = id("ambush");
	ResourceKey<Enchantment> CRITICAL_STRIKE = id("critical_strike");
	ResourceKey<Enchantment> DUNE_SPEED = id("dune_speed");
	ResourceKey<Enchantment> LEVITATION_SHOT = id("levitation_shot");
	ResourceKey<Enchantment> PLATED = id("plated");
	ResourceKey<Enchantment> SOARING = id("soaring");
	ResourceKey<Enchantment> VOID_SHOT = id("void_shot");
	ResourceKey<Enchantment> VOID_STRIKE = id("void_strike");

	static ResourceKey<Enchantment> id(String name) {
		return Stellarity.key(Registries.ENCHANTMENT, name);
	}
}

