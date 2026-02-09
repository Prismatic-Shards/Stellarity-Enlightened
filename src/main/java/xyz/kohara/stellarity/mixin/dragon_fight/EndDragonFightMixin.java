package xyz.kohara.stellarity.mixin.dragon_fight;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import net.minecraft.world.level.levelgen.feature.SpikeFeature;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.interface_injection.ExtEndDragonFight;

import java.util.List;

@Mixin(EndDragonFight.class)
public abstract class EndDragonFightMixin implements ExtEndDragonFight {
    @Shadow
    private boolean dragonKilled;

    @Shadow
    @Nullable
    private List<EndCrystal> respawnCrystals;
    @Shadow
    private int crystalsAlive;

    @Shadow
    protected abstract void findOrCreateDragon();

    @Unique
    @Final
    private final ServerBossEvent stellarity$crystalsRemaining = stellarity$createBossEvent();
    
    @Unique
    private ServerBossEvent stellarity$createBossEvent() {
        return new ServerBossEvent(Component.translatable("bossbar.stellarity.crystals_left", crystalsAlive).withStyle(ChatFormatting.DARK_PURPLE), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.NOTCHED_20);
    }

    @Override
    public boolean stellarity$dragonKilled() {
        return dragonKilled;
    }

    @Inject(method = "resetSpikeCrystals", at = @At("HEAD"))
    private void resetRespawnCrystals(CallbackInfo ci) {
        for (EndCrystal endCrystal : respawnCrystals) {
            endCrystal.setInvulnerable(false);
            endCrystal.setBeamTarget(null);
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        stellarity$crystalsRemaining.setVisible(!dragonKilled);
        if (!dragonKilled) {
            stellarity$crystalsRemaining.setProgress(Math.min((float) crystalsAlive / SpikeFeature.NUMBER_OF_SPIKES, 1.0f));
            stellarity$crystalsRemaining.setName(Component.translatable("bossbar.stellarity.crystals_left", crystalsAlive).withStyle(ChatFormatting.DARK_PURPLE));
        }
    }

    @Inject(method = "updatePlayers", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerBossEvent;addPlayer(Lnet/minecraft/server/level/ServerPlayer;)V"))
    private void addPlayerBossBar(CallbackInfo ci, @Local/*? if !fabric >> ' Server' *//*(name = "serverplayer")*/ ServerPlayer player) {
        stellarity$crystalsRemaining.addPlayer(player);
    }

    @Inject(method = "updatePlayers", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerBossEvent;removePlayer(Lnet/minecraft/server/level/ServerPlayer;)V"))
    private void removePlayerBossBar(CallbackInfo ci, @Local/*? if !fabric >> ' Server' *//*(name = "serverplayer1")*/ ServerPlayer player) {
        stellarity$crystalsRemaining.removePlayer(player);
    }
}
