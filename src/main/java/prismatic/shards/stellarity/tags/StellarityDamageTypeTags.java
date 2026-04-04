package prismatic.shards.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import prismatic.shards.stellarity.Stellarity;

public class StellarityDamageTypeTags {
	public static final TagKey<DamageType> MELEE = bind("melee");
	public static final TagKey<DamageType> RANGED = bind("ranged");

	private static TagKey<DamageType> bind(String id) {
		return TagKey.create(Registries.DAMAGE_TYPE, Stellarity.id(id));
	}
}
