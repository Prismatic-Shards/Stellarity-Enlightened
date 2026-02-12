package xyz.kohara.stellarity.registry;

import net.minecraft.world.item.enchantment.Enchantment;
import xyz.kohara.stellarity.Stellarity;

//? 1.20.1 {


import net.minecraft.core.registries.BuiltInRegistries;
import xyz.kohara.stellarity.registry.enchantment.*;

import net.minecraft.core.Registry;

public class StellarityEnchantments {

    public static final Enchantment LEVITATION_SHOT = register("levitation_shot", new LevitationShot());
    public static final Enchantment CRITICAL_STRIKE = register("critical_strike", new CriticalStrike());

    public static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT, Stellarity.id(name), enchantment);
    }

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Enchantments (LEGACY)");
    }
}

//? } else {


/*import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
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

*///? }
