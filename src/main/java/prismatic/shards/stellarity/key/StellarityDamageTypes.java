package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import prismatic.shards.stellarity.Stellarity;


public interface StellarityDamageTypes {
	ResourceKey<DamageType> BRITTLE = id("brittle");
	ResourceKey<DamageType> FROSTBURN = id("frostburn");
	ResourceKey<DamageType> TAMARIS_EXECUTE = id("tamaris_execute");

	static ResourceKey<DamageType> id(String string) {
		return Stellarity.key(Registries.DAMAGE_TYPE, string);
	}
}