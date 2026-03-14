package xyz.kohara.stellarity.registry.advancement_criterion;


import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import xyz.kohara.stellarity.Stellarity;

//? 1.20.1 {
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
	//? } else {
/*import java.util.Optional;

import net.minecraft.advancements.Criterion;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.Codec;
import xyz.kohara.stellarity.registry.StellarityCriteriaTriggers;
*///? }

public class AdvancementCompletedTrigger extends SimpleCriterionTrigger<AdvancementCompletedTrigger.TriggerInstance> {
	static final ResourceLocation ID = Stellarity.id("advancement_completed");

	public ResourceLocation getId() {
		return ID;
	}


	public void trigger(ServerPlayer serverPlayer, ResourceLocation location) {
		this.trigger(serverPlayer, (triggerInstance) -> triggerInstance.location.equals(location));
	}

	//? 1.20.1 {

	public void trigger(ServerPlayer serverPlayer, Advancement advancement) {
		this.trigger(serverPlayer, (triggerInstance) -> {
			var a = serverPlayer.server.getAdvancements().getAdvancement(triggerInstance.location);
			return a != null && a.equals(advancement);
		});
	}


	public static class TriggerInstance extends AbstractCriterionTriggerInstance {
		public final ResourceLocation location;
		public final boolean isPrerequisite = true;

		// this means like you cannot start any other advancement triggers until all prerequiste advancementcompleted trigger has been fulfilled
		public boolean isPrerequisite() {
			return isPrerequisite;
		}

		public TriggerInstance(ContextAwarePredicate contextAwarePredicate, ResourceLocation location, boolean isPrerequisite) {
			super(ID, contextAwarePredicate);
			this.location = location;
		}


		public static TriggerInstance triggerInstance(ResourceLocation location, boolean isPrerequisite) {
			return new TriggerInstance(ContextAwarePredicate.ANY, location, isPrerequisite);
		}

		@Override
		public JsonObject serializeToJson(SerializationContext serializationContext) {
			JsonObject jsonObject = super.serializeToJson(serializationContext);
			jsonObject.addProperty("advancement", location.toString());
			jsonObject.addProperty("is_prerequisite", isPrerequisite);

			return jsonObject;
		}
	}

	@Override
	public TriggerInstance createInstance(JsonObject jsonObject, ContextAwarePredicate contextAwarePredicate, DeserializationContext deserializationContext) {
		return new TriggerInstance(contextAwarePredicate, ResourceLocation.tryParse(jsonObject.get("advancement").getAsString()), !jsonObject.has("is_prerequisite") || jsonObject.get("is_prerequisite").getAsBoolean());
	}

	//? } else {

	/*public record TriggerInstance(ResourceLocation location,
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
	*///? }
}