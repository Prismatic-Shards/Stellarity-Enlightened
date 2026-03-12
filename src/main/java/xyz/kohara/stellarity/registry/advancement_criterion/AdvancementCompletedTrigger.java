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
		this.trigger(serverPlayer, (triggerInstance) -> triggerInstance.location == location);
	}

	//? 1.20.1 {

	public void trigger(ServerPlayer serverPlayer, Advancement advancement) {
		this.trigger(serverPlayer, (triggerInstance) -> {
			var a = serverPlayer.server.getAdvancements().getAdvancement(triggerInstance.location);
			return a != null && a.equals(advancement);
		});
	}


	public static class TriggerInstance extends AbstractCriterionTriggerInstance {
		private final ResourceLocation location;

		public TriggerInstance(ContextAwarePredicate contextAwarePredicate, ResourceLocation location) {
			super(ID, contextAwarePredicate);
			this.location = location;
		}


		public static TriggerInstance triggerInstance(ResourceLocation location) {
			return new TriggerInstance(ContextAwarePredicate.ANY, location);
		}

		@Override
		public JsonObject serializeToJson(SerializationContext serializationContext) {
			JsonObject jsonObject = super.serializeToJson(serializationContext);
			jsonObject.addProperty("advancement", location.toString());
			return jsonObject;
		}
	}

	@Override
	public TriggerInstance createInstance(JsonObject jsonObject, ContextAwarePredicate contextAwarePredicate, DeserializationContext deserializationContext) {
		return new TriggerInstance(contextAwarePredicate, ResourceLocation.tryParse(jsonObject.get("advancement").getAsString()));
	}

	//? } else {

	/*public record TriggerInstance(ResourceLocation location) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create((instance) -> instance.group(ResourceLocation.CODEC.fieldOf("advancement").forGetter(TriggerInstance::location)).apply(instance, TriggerInstance::new));

		public static Criterion<TriggerInstance> triggerInstance(ResourceLocation count) {
			return StellarityCriteriaTriggers.ADVANCEMENT_COMPLETED.createCriterion(new TriggerInstance(count));
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