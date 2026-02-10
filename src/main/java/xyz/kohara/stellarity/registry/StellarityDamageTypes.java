package xyz.kohara.stellarity.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import xyz.kohara.stellarity.Stellarity;

import static net.minecraft.core.registries.Registries.DAMAGE_TYPE;


public class StellarityDamageTypes {
    //DOT
    public static final ResourceKey<DamageType> FROSTBURN = create("dot/frostburn");
    public static final ResourceKey<DamageType> PRISMATIC_INFERNO = create("dot/prismatic_inferno");
    public static final ResourceKey<DamageType> BRITTLE = create("brittle");
    public static final ResourceKey<DamageType> TAMARIS_EXECUTE = create("tamaris_execute");

    private static ResourceKey<DamageType> create(String string) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Stellarity.id(string));
    }
}
