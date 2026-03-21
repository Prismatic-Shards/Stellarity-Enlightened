package xyz.kohara.stellarity.registry.advancement_criterion;

import java.util.Collection;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import xyz.kohara.stellarity.Stellarity;

import java.util.Optional;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.Codec;
import xyz.kohara.stellarity.registry.StellarityCriteriaTriggers;

public class VoidFishedTrigger extends SimpleCriterionTrigger<VoidFishedTrigger.TriggerInstance> {
	static final ResourceLocation ID = Stellarity.id("void_fished");

	public ResourceLocation getId() {
		return ID;
	}


	public void trigger(ServerPlayer serverPlayer, ItemStack itemStack, FishingHook fishingHook, Collection<ItemStack> collection) {
		LootContext lootContext = EntityPredicate.createContext(serverPlayer, (Entity) (fishingHook.getHookedIn() != null ? fishingHook.getHookedIn() : fishingHook));
		this.trigger(serverPlayer, (triggerInstance) -> triggerInstance.matches(itemStack, lootContext, collection));
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> rod,
	                              Optional<ContextAwarePredicate> entity,
	                              Optional<ItemPredicate> item) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create((instance) -> instance.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player), ItemPredicate.CODEC.optionalFieldOf("rod").forGetter(TriggerInstance::rod), EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("entity").forGetter(TriggerInstance::entity), ItemPredicate.CODEC.optionalFieldOf("item").forGetter(TriggerInstance::item)).apply(instance, TriggerInstance::new));

		@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
		public static Criterion<TriggerInstance> fishedItem(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> itemPredicate, Optional<EntityPredicate> optional2, Optional<ItemPredicate> optional3) {
			return StellarityCriteriaTriggers.VOID_FISHED.createCriterion(new TriggerInstance(player, itemPredicate, EntityPredicate.wrap(optional2), optional3));
		}

		public boolean matches(ItemStack itemStack, LootContext lootContext, Collection<ItemStack> collection) {
			if (this.rod.isPresent() && !this.rod.get().test(itemStack)) {
				return false;
			} else if (this.entity.isPresent() && !this.entity.get().matches(lootContext)) {
				return false;
			} else {
				if (this.item.isPresent()) {
					boolean bl = false;
					Entity entity = lootContext./*? 1.21.1 {*/getParamOrNull/*?} else { *//*getOptionalParameter*//*? } */(LootContextParams.THIS_ENTITY);

					if (entity instanceof ItemEntity itemEntity) {
						if (this.item.get().test(itemEntity.getItem())) {
							bl = true;
						}
					}

					for (ItemStack itemStack2 : collection) {
						if (this.item.get().test(itemStack2)) {
							bl = true;
							break;
						}
					}

					return bl;
				}

				return true;
			}
		}

		public void validate(CriterionValidator criterionValidator) {
			SimpleCriterionTrigger.SimpleInstance.super.validate(criterionValidator);
			criterionValidator.validateEntity(this.entity, "entity");
		}
	}

	public Codec<TriggerInstance> codec() {
		return TriggerInstance.CODEC;
	}
}