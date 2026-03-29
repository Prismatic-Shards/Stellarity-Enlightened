package xyz.kohara.stellarity.registry.advancement_criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.Validatable;
import net.minecraft.world.level.storage.loot.ValidationContextSource;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import xyz.kohara.stellarity.registry.StellarityCriteriaTriggers;

import java.util.Optional;

public class SpecialCraftTrigger extends SimpleCriterionTrigger<SpecialCraftTrigger.TriggerInstance> {
	@Override
	public Codec<TriggerInstance> codec() {
		return SpecialCraftTrigger.TriggerInstance.CODEC;
	}

	public void trigger(final ServerPlayer player, final BlockPos pos, final ItemInstance tool) {
		ServerLevel level = player.level();
		BlockState state = level.getBlockState(pos);
		LootParams params = (new LootParams.Builder(level)).withParameter(LootContextParams.ORIGIN, pos.getCenter()).withParameter(LootContextParams.THIS_ENTITY, player).withParameter(LootContextParams.BLOCK_STATE, state).withParameter(LootContextParams.TOOL, tool).create(LootContextParamSets.ADVANCEMENT_LOCATION);
		LootContext context = (new LootContext.Builder(params)).create(Optional.empty());
		this.trigger(player, (t) -> t.matches(context));
	}

	public static Criterion<TriggerInstance> triggerInstance(Optional<ContextAwarePredicate> player,
	                                                         Optional<ContextAwarePredicate> location) {
		return StellarityCriteriaTriggers.SPECIAL_CRAFT.createCriterion(new TriggerInstance(player, location));
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player,
	                              Optional<ContextAwarePredicate> location) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<SpecialCraftTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create((i) -> i.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(SpecialCraftTrigger.TriggerInstance::player), ContextAwarePredicate.CODEC.optionalFieldOf("location").forGetter(SpecialCraftTrigger.TriggerInstance::location)).apply(i, SpecialCraftTrigger.TriggerInstance::new));

		public boolean matches(final LootContext locationContext) {
			return this.location.isEmpty() || ((ContextAwarePredicate) this.location.get()).matches(locationContext);
		}

		@Override
		public void validate(ValidationContextSource validator) {
			SimpleInstance.super.validate(validator);
			Validatable.validate(validator.context(LootContextParamSets.ADVANCEMENT_LOCATION), "location", this.location);
		}
	}
}
