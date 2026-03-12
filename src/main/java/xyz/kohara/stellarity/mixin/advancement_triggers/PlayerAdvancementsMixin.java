package xyz.kohara.stellarity.mixin.advancement_triggers;

//? 1.20.1 {

import net.minecraft.advancements.Advancement;
//? } else {
/*import net.minecraft.advancements.AdvancementHolder;
 *///? }
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.kohara.stellarity.registry.StellarityCriteriaTriggers;

@Mixin(PlayerAdvancements.class)
public class PlayerAdvancementsMixin {
	@Shadow
	private ServerPlayer player;

	//? 1.20.1 {

	@Inject(method = "award", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerAdvancements;markForVisibilityUpdate(Lnet/minecraft/advancements/Advancement;)V"))
	private void advancementTrigger(Advancement advancement, String string, CallbackInfoReturnable<Boolean> cir) {
		StellarityCriteriaTriggers.ADVANCEMENT_COMPLETED.trigger(this.player, advancement);
	}
	//? } else {
	/*@Inject(method = "award", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerAdvancements;markForVisibilityUpdate(Lnet/minecraft/advancements/AdvancementHolder;)V", shift = At.Shift.AFTER))
	private void advancementTrigger(AdvancementHolder advancementHolder, String string, CallbackInfoReturnable<Boolean> cir) {
		StellarityCriteriaTriggers.ADVANCEMENT_COMPLETED.trigger(this.player, advancementHolder.id());
	}
	*///? }
}
