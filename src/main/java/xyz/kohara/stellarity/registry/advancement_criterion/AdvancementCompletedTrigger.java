package xyz.kohara.stellarity.registry.advancement_criterion;


import net.minecraft.advancements.criterion.*;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;

import org.jspecify.annotations.NonNull;
import xyz.kohara.stellarity.Stellarity;

import java.util.Optional;

import net.minecraft.advancements.Criterion;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.Codec;
import xyz.kohara.stellarity.registry.StellarityCriteriaTriggers;

import net.minecraft.world.level.storage.loot.ValidationContextSource;


public class AdvancementCompletedTrigger extends SimpleCriterionTrigger<AdvancementCompletedTrigger.TriggerInstance> {
	static final Identifier ID = Stellarity.id("advancement_completed");

	public Identifier getId() {
		return ID;
	}


	public void trigger(ServerPlayer serverPlayer, Identifier location) {
		this.trigger(serverPlayer, (triggerInstance) -> triggerInstance.location.equals(location));
	}

	public record TriggerInstance(Identifier location,
	                              boolean isPrerequisite) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create((instance) -> instance.group(Identifier.CODEC.fieldOf("advancement").forGetter(TriggerInstance::location), Codec.BOOL.fieldOf("is_prerequisite").forGetter(TriggerInstance::isPrerequisite)).apply(instance, TriggerInstance::new));

		public static Criterion<TriggerInstance> triggerInstance(Identifier count, boolean isPrerequisite) {
			return StellarityCriteriaTriggers.ADVANCEMENT_COMPLETED.createCriterion(new TriggerInstance(count, isPrerequisite));
		}


		@Override
		public void validate(final @NonNull ValidationContextSource validator) {
		}


		@Override
		public @NonNull Optional<ContextAwarePredicate> player() {
			return Optional.empty();
		}
	}

	public @NonNull Codec<TriggerInstance> codec() {
		return TriggerInstance.CODEC;
	}
}