package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import prismatic.shards.stellarity.Stellarity;


public interface StellarityDamageTypes {

	ResourceKey<DamageType> FROSTBURN = id("dot/frostburn");
	ResourceKey<DamageType> PRISMATIC_INFERNO = id("dot/prismatic_inferno");
	ResourceKey<DamageType> BRITTLE = id("brittle");
	ResourceKey<DamageType> TAMARIS_EXECUTE = id("tamaris_execute");
	ResourceKey<DamageType> PRISMEMBER = id("prismember");

	static ResourceKey<DamageType> id(String string) {
		return ResourceKey.create(Registries.DAMAGE_TYPE, Stellarity.id(string));
	}
}
