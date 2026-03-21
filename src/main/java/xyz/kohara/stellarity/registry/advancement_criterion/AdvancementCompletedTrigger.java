package xyz.kohara.stellarity.registry.advancement_criterion;


import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import xyz.kohara.stellarity.Stellarity;

import java.util.Optional;

import net.minecraft.advancements.Criterion;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.Codec;
import xyz.kohara.stellarity.registry.StellarityCriteriaTriggers;

public class AdvancementCompletedTrigger extends SimpleCriterionTrigger<AdvancementCompletedTrigger.TriggerInstance> {
	static final ResourceLocation ID = Stellarity.id("advancement_completed");

	public ResourceLocation getId() {
		return ID;
	}


	public void trigger(ServerPlayer serverPlayer, ResourceLocation location) {
		this.trigger(serverPlayer, (triggerInstance) -> triggerInstance.location.equals(location));
	}

	public record TriggerInstance(ResourceLocation location,
	                              boolean isPrerequisite) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create((instance) -> instance.group(ResourceLocation.CODEC.fieldOf("advancement").forGetter(TriggerInstance::location), Codec.BOOL.fieldOf("is_prerequisite").forGetter(TriggerInstance::isPrerequisite)).apply(instance, TriggerInstance::new));

		public static Criterion<TriggerInstance> triggerInstance(ResourceLocation count, boolean isPrerequisite) {
			return StellarityCriteriaTriggers.ADVANCEMENT_COMPLETED.createCriterion(new TriggerInstance(count, isPrerequisite));
		}


		public void validate(CriterionValidator criterionValidator) {

		}

		@Override
		public Optional<ContextAwarePredicate> player() {
			return Optional.empty();
		}
	}

	public Codec<TriggerInstance> codec() {
		return TriggerInstance.CODEC;
	}
}