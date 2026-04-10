package prismatic.shards.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.entity.PhantomItemFrame;
import prismatic.shards.stellarity.registry.entity.ThrownPrismaticPearl;
import prismatic.shards.stellarity.registry.entity.variants.StellarityFrogVariants;


public interface StellarityEntities {

	EntityType<PhantomItemFrame> PHANTOM_ITEM_FRAME = register("phantom_item_frame", EntityType.Builder.of(PhantomItemFrame::new, MobCategory.MISC));

	EntityType<ThrownPrismaticPearl> PRISMATIC_PEARL = register("prismatic_pearl", EntityType.Builder.<ThrownPrismaticPearl>of(ThrownPrismaticPearl::new, MobCategory.MISC).noLootTable().sized(0.25F, 0.25F));

	static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
		var location = Stellarity.id(id);
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, location, builder.build(


			Stellarity.key(Registries.ENTITY_TYPE, id)

		));
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Entities");
		StellarityFrogVariants.init();
	}
}
