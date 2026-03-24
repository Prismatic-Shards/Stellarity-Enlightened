package xyz.kohara.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEntityTypeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import xyz.kohara.stellarity.tags.StellarityEntityTags;

import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.TagKey;


import java.util.concurrent.CompletableFuture;

public class EntityTagProvider extends FabricTagsProvider.EntityTypeTagsProvider {
	public EntityTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	public void addTags(HolderLookup.Provider provider) {
		getOrCreateTagBuilder(StellarityEntityTags.INVALID_TARGETS).forceAddTag(ConventionalEntityTypeTags.BOATS).forceAddTag(EntityTypeTags.IMPACT_PROJECTILES).forceAddTag(ConventionalEntityTypeTags.MINECARTS).add(EntityType.ITEM_FRAME, EntityType.GLOW_ITEM_FRAME, EntityType.PAINTING, EntityType.ARMOR_STAND, EntityType.LEASH_KNOT, EntityType.INTERACTION, EntityType.AREA_EFFECT_CLOUD, EntityType.ENDER_PEARL, EntityType.END_CRYSTAL, EntityType.EVOKER_FANGS, EntityType.EXPERIENCE_BOTTLE, EntityType.EXPERIENCE_ORB, EntityType.EYE_OF_ENDER, EntityType.FALLING_BLOCK, EntityType.FISHING_BOBBER, EntityType.FIREWORK_ROCKET, EntityType.FIREBALL, EntityType.ITEM, EntityType.LLAMA_SPIT, EntityType.SHULKER_BULLET


			, EntityType.SPLASH_POTION, EntityType.LINGERING_POTION
		);
	}


	public TagAppender<EntityType<?>, EntityType<?>> getOrCreateTagBuilder(TagKey<EntityType<?>> tagKey) {
		return this.valueLookupBuilder(tagKey);
	}

}
