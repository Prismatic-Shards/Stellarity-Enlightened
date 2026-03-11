package xyz.kohara.stellarity.registry.advancement_criterion;


import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.dimension.end.EndDragonFight;

import org.jetbrains.annotations.Nullable;
import xyz.kohara.stellarity.Stellarity;

//? 1.20.1 {

import com.google.gson.JsonObject;
 //? } else {
/*import java.util.Optional;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.Codec;
import xyz.kohara.stellarity.registry.StellarityCriteriaTriggers;
*///? }

public class DragonSummonedTrigger extends SimpleCriterionTrigger<DragonSummonedTrigger.TriggerInstance> {
	static final ResourceLocation ID = Stellarity.id("dragon_summoned");

	public ResourceLocation getId() {
		return ID;
	}


	public void trigger(ServerPlayer serverPlayer, EndDragonFight dragonFight) {
		this.trigger(serverPlayer, (triggerInstance) -> triggerInstance.count == null || dragonFight.getSummonCount(serverPlayer) <= triggerInstance.count);
	}

	//? 1.20.1 {


	public static class TriggerInstance extends AbstractCriterionTriggerInstance {
		private final @Nullable Integer count;

		public TriggerInstance(ContextAwarePredicate contextAwarePredicate, @Nullable Integer count) {
			super(ID, contextAwarePredicate);
			this.count = count;
		}


		public static TriggerInstance triggerInstance(Integer count) {
			return new TriggerInstance(ContextAwarePredicate.ANY, count);
		}

		@Override
		public JsonObject serializeToJson(SerializationContext serializationContext) {
			JsonObject jsonObject = super.serializeToJson(serializationContext);
			jsonObject.addProperty("count", count);
			return jsonObject;
		}
	}

	@Override
	public TriggerInstance createInstance(JsonObject jsonObject, ContextAwarePredicate contextAwarePredicate, DeserializationContext deserializationContext) {
		return new TriggerInstance(contextAwarePredicate, jsonObject.get("count").getAsInt());
	}

	//? } else {

	/*public record TriggerInstance(@Nullable Integer count) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create((instance) -> instance.group(Codec.INT.fieldOf("count").orElse(0).forGetter(TriggerInstance::count)).apply(instance, TriggerInstance::new));

		@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
		public static Criterion<TriggerInstance> triggerInstance(@Nullable Integer count) {
			return StellarityCriteriaTriggers.DRAGON_SUMMONED.createCriterion(new TriggerInstance(count));
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