package xyz.kohara.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import xyz.kohara.stellarity.Stellarity;

public class StellarityDamageTypeTags {
    public static final TagKey<DamageType> MELEE = bind("melee");

    private static TagKey<DamageType> bind(String id) {
        return TagKey.create(Registries.DAMAGE_TYPE, Stellarity.id(id));
    }
}
