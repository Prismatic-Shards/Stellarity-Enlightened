package xyz.kohara.stellarity.registry.entity.variants;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.animal.FrogVariant;
import xyz.kohara.stellarity.Stellarity;

public class StellarityFrogVariants {
    public static final FrogVariant END = register("end", "textures/entity/frog/ender_frog.png");
    public static final ResourceKey<FrogVariant> END_KEY = Stellarity.key(Registries.FROG_VARIANT, "end");

    private static FrogVariant register(String id, String texture) {
        return Registry.register(BuiltInRegistries.FROG_VARIANT, Stellarity.id(id), new FrogVariant(Stellarity.id(texture)));
    }

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Frog Variants");
    }
}
