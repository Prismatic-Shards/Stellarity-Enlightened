package xyz.kohara.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import xyz.kohara.stellarity.Stellarity;

public class StellarityEntityTags {
	public static final TagKey<EntityType<?>> INVALID_TARGETS = bind("invalid_targets");


	private static TagKey<EntityType<?>> bind(String id) {
		return TagKey.create(Registries.ENTITY_TYPE, Stellarity.id(id));
	}

}
