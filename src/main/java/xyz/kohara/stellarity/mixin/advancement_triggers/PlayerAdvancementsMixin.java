package xyz.kohara.stellarity.mixin.advancement_triggers;


import com.llamalad7.mixinextras.sugar.Local;
//? 1.20.1 {

import net.minecraft.advancements.Advancement;
	//? } else {

/*import net.minecraft.advancements.AdvancementHolder;
 *///? }
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.CriterionProgress;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.kohara.stellarity.registry.StellarityCriteriaTriggers;
import xyz.kohara.stellarity.registry.advancement_criterion.AdvancementCompletedTrigger;

import java.util.Optional;

@Mixin(PlayerAdvancements.class)
public abstract class PlayerAdvancementsMixin {
	@Shadow
	private ServerPlayer player;

	//~ if > 1.21 'Advancement;' -> 'AdvancementHolder;' {
	//~ if > 1.21 'Advancement ' -> 'AdvancementHolder ' {
	//~ if > 1.21 'getTrigger()' -> 'triggerInstance()' {

	@Shadow
	public abstract boolean revoke(Advancement advancement, String string);

	@Inject(method = "award", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerAdvancements;markForVisibilityUpdate(Lnet/minecraft/advancements/Advancement;)V", shift = At.Shift.AFTER))
	private void advancementTrigger(Advancement advancement, String string, CallbackInfoReturnable<Boolean> cir) {
		StellarityCriteriaTriggers.ADVANCEMENT_COMPLETED.trigger(this.player, advancement/*? > 1.21 >> ');'*//*.id()*/);
	}

	@Inject(method = "award", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerAdvancements;unregisterListeners(Lnet/minecraft/advancements/Advancement;)V"))
	private void clearAfterAdvancementRequirement(Advancement advancement, String string, CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 1) boolean isDone, @Local AdvancementProgress progress) {
		if (isDone) return;
		var required = advancement./*? 1.20.1 {*/getCriteria/*? } else {*//*value().criteria*//*? }*/();
		var requirement = required.get(string);
		if (requirement == null || !(requirement.getTrigger() instanceof AdvancementCompletedTrigger.TriggerInstance instance) || !instance.isPrerequisite())
			return;

		for (var completed : progress.getCompletedCriteria()) {
			if (required.get(completed).getTrigger() instanceof AdvancementCompletedTrigger.TriggerInstance) continue;

			revoke(advancement, completed);
		}
	}
	//~ }
	//~ }
	//~ }

}
