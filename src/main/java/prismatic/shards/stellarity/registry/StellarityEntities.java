package prismatic.shards.stellarity.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.entity.*;


public interface StellarityEntities {

	EntityType<PhantomItemFrame> PHANTOM_ITEM_FRAME = register("phantom_item_frame", EntityType.Builder.of(PhantomItemFrame::new, MobCategory.MISC));

	EntityType<ThrownPrismaticPearl> PRISMATIC_PEARL = register("prismatic_pearl", EntityType.Builder.<ThrownPrismaticPearl>of(ThrownPrismaticPearl::new, MobCategory.MISC).noLootTable().sized(0.25F, 0.25F));

	EntityType<VoidedZombie> VOIDED_ZOMBIE = register("voided_zombie", EntityType.Builder.<VoidedZombie>of(VoidedZombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).eyeHeight(1.74F).passengerAttachments(2.0125F).ridingOffset(-0.7F).clientTrackingRange(8).notInPeaceful());

	EntityType<VoidedSkeleton> VOIDED_SKELETON = register("voided_skeleton", EntityType.Builder.<VoidedSkeleton>of(VoidedSkeleton::new, MobCategory.MONSTER).sized(0.6F, 1.99F).eyeHeight(1.74F).ridingOffset(-0.7F).clientTrackingRange(8).notInPeaceful());

	EntityType<VoidedSilverfish> VOIDED_SILVERFISH = register("voided_silverfish", EntityType.Builder.<VoidedSilverfish>of(VoidedSilverfish::new, MobCategory.MONSTER).sized(0.4F, 0.3F).eyeHeight(0.13F).passengerAttachments(0.2375F).clientTrackingRange(8).notInPeaceful());

	EntityType<VoidedSlime> VOIDED_SLIME = register("voided_slime", EntityType.Builder.<VoidedSlime>of(VoidedSlime::new, MobCategory.MONSTER).sized(0.52F, 0.52F).eyeHeight(0.325F).spawnDimensionsScale(4.0F).clientTrackingRange(10).notInPeaceful());

	static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
		var location = Stellarity.id(id);
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, location, builder.build(
			Stellarity.key(Registries.ENTITY_TYPE, id)
		));
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Entities");

		FabricDefaultAttributeRegistry.register(VOIDED_ZOMBIE, VoidedZombie.createAttributes());
		FabricDefaultAttributeRegistry.register(VOIDED_SKELETON, VoidedSkeleton.createAttributes());
		FabricDefaultAttributeRegistry.register(VOIDED_SILVERFISH, VoidedSilverfish.createAttributes());
		FabricDefaultAttributeRegistry.register(VOIDED_SLIME, VoidedSlime.createAttributes());
	}
}
