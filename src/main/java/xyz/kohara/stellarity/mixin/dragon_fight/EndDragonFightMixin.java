package xyz.kohara.stellarity.mixin.dragon_fight;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.critereon.SummonedEntityTrigger;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import net.minecraft.world.level.levelgen.feature.SpikeFeature;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityCriteriaTriggers;

import java.util.List;

@Mixin(EndDragonFight.class)
public abstract class EndDragonFightMixin {
	@Shadow
	public boolean dragonKilled;

	@Shadow
	@Nullable
	private List<EndCrystal> respawnCrystals;
	@Shadow
	private int crystalsAlive;

	@Unique
	private final ServerBossEvent crystalsRemaining = new ServerBossEvent(Component.translatable("bossbar.stellarity.crystals_left", crystalsAlive).withStyle(ChatFormatting.DARK_PURPLE), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.NOTCHED_20);

	@Inject(method = "resetSpikeCrystals", at = @At("HEAD"))
	private void resetRespawnCrystals(CallbackInfo ci) {
		if (respawnCrystals == null) return;
		for (EndCrystal endCrystal : respawnCrystals) {
			endCrystal.setInvulnerable(false);
			endCrystal.setBeamTarget((BlockPos) null);
		}
	}

	@Inject(method = "tick", at = @At("TAIL"))
	private void tick(CallbackInfo ci) {
		crystalsRemaining.setVisible(!dragonKilled);
		if (!dragonKilled) {
			crystalsRemaining.setProgress(Math.min((float) crystalsAlive / SpikeFeature.NUMBER_OF_SPIKES, 1.0f));
			crystalsRemaining.setName(Component.translatable("bossbar.stellarity.crystals_left", crystalsAlive).withStyle(ChatFormatting.DARK_PURPLE));
		}
	}


	@Inject(method = "updatePlayers", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerBossEvent;addPlayer(Lnet/minecraft/server/level/ServerPlayer;)V"))
	private void addPlayerBossBar(CallbackInfo ci, @Local ServerPlayer player) {
		crystalsRemaining.addPlayer(player);
	}

	@Inject(method = "updatePlayers", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerBossEvent;removePlayer(Lnet/minecraft/server/level/ServerPlayer;)V"))
	private void removePlayerBossBar(CallbackInfo ci, @Local ServerPlayer player) {
		crystalsRemaining.removePlayer(player);
	}

	@WrapOperation(method = "scanState", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/dimension/end/EndDragonFight;dragonKilled:Z", opcode = Opcodes.PUTFIELD))
	private void scanStatePreventRevive(EndDragonFight instance, boolean value, Operation<Void> original) {
		original.call(instance, true);
		Stellarity.LOGGER.info("force overriding to prevent dragon summoning");
	}
}
