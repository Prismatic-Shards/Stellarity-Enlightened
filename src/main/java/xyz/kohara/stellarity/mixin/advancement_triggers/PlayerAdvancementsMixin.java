package xyz.kohara.stellarity.mixin.advancement_triggers;


import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.kohara.stellarity.registry.StellarityCriteriaTriggers;
import xyz.kohara.stellarity.registry.advancement_criterion.AdvancementCompletedTrigger;

@Mixin(PlayerAdvancements.class)
public abstract class PlayerAdvancementsMixin {
	@Shadow
	private ServerPlayer player;

	@Shadow
	public abstract boolean revoke(AdvancementHolder advancement, String string);

	@Inject(method = "award", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerAdvancements;markForVisibilityUpdate(Lnet/minecraft/advancements/AdvancementHolder;)V", shift = At.Shift.AFTER))
	private void advancementTrigger(AdvancementHolder advancement, String string, CallbackInfoReturnable<Boolean> cir) {
		StellarityCriteriaTriggers.ADVANCEMENT_COMPLETED.trigger(this.player, advancement.id());
	}

	@Inject(method = "award", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerAdvancements;unregisterListeners(Lnet/minecraft/advancements/AdvancementHolder;)V"))
	private void clearAfterAdvancementRequirement(AdvancementHolder advancement, String string, CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 1) boolean isDone, @Local AdvancementProgress progress) {
		if (isDone) return;
		var required = advancement.value().criteria();
		var requirement = required.get(string);
		if (requirement == null || !(requirement.triggerInstance() instanceof AdvancementCompletedTrigger.TriggerInstance instance) || !instance.isPrerequisite())
			return;

		for (var completed : progress.getCompletedCriteria()) {
			if (required.get(completed).triggerInstance() instanceof AdvancementCompletedTrigger.TriggerInstance) continue;

			revoke(advancement, completed);
		}
	}
}
