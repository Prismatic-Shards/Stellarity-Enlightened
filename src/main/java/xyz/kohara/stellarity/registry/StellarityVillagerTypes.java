package xyz.kohara.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.villager.VillagerType;
import xyz.kohara.stellarity.Stellarity;

public class StellarityVillagerTypes {
	public static final ResourceKey<VillagerType> END = register("end");

	private static ResourceKey<VillagerType> register(String name) {
		ResourceKey<VillagerType> key = Stellarity.key(Registries.VILLAGER_TYPE, name);
		//noinspection InstantiationOfUtilityClass
		Registry.register(BuiltInRegistries.VILLAGER_TYPE, Stellarity.id(name), new VillagerType());
		return key;
	}

	public static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Villager Types");
	}
}
