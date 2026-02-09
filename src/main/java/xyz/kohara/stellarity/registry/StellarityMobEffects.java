package xyz.kohara.stellarity.registry;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.effect.*;

public class StellarityMobEffects {
    public static final VoidedEffect VOIDED = register("voided", new VoidedEffect());
    public static final JinxEffect JINX = register("jinx", new JinxEffect());

    public static final BrittleEffect BRITTLE = register("brittle", new BrittleEffect());
    public static final CreativeShockEffect CREATIVE_SHOCK = register("creative_shock", new CreativeShockEffect());
    public static final FrostburnEffect FROSTBURN = register("frostburn", new FrostburnEffect());
    public static final PrismaticInfernoEffect PRISMATIC_INFERNO = register("prismatic_inferno", new PrismaticInfernoEffect());


    public static <T extends MobEffect> T register(String name, T effect) {
        return Registry.register(BuiltInRegistries.MOB_EFFECT, Stellarity.id(name), effect);
    }

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Mob Effects");
    }
}
