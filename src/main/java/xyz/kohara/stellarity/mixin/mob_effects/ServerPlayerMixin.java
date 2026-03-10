package xyz.kohara.stellarity.mixin.mob_effects;


import com.mojang.authlib.GameProfile;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//? < 1.21.9 {
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
	//? } else {
/*import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import com.llamalad7.mixinextras.sugar.Local;

import java.util.Collection;
*///? }


import xyz.kohara.stellarity.registry.effect.CreativeShockEffect;
//? 1.20.1 {

//? } else {
/*import xyz.kohara.stellarity.registry.StellarityMobEffects;
 *///? }


@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player {
	@Shadow
	@Final
	public ServerPlayerGameMode gameMode;


	@Shadow
	public abstract boolean setGameMode(GameType gameType);

	@Unique
	@Nullable
	private GameType initialGameType = null;

	//? < 1.21.9 {
	public ServerPlayerMixin(Level level, BlockPos blockPos, float f, GameProfile gameProfile) {
		super(level, blockPos, f, gameProfile);
	}
	//? } else {

	/*public ServerPlayerMixin(Level level, GameProfile gameProfile) {
		super(level, gameProfile);
	}
	*///? }


	@Inject(method = "onEffectAdded", at = @At("HEAD"))
	private void effectAdded(MobEffectInstance effectInstance, Entity entity, CallbackInfo ci) {
		var type = gameMode.getGameModeForPlayer();


		if (//? 1.20.1 {
			!(effectInstance.getEffect()/*? > 1.21 {*//*.value()*//*? } */ instanceof CreativeShockEffect effect)
			//? } else {
			/*!effectInstance.is(StellarityMobEffects.CREATIVE_SHOCK)
			 *///? }
		) return;


		if (!CreativeShockEffect.extremeCreativeShock() && type == GameType.CREATIVE) return;

		if (initialGameType == null) initialGameType = type;
		setGameMode(GameType.ADVENTURE);
	}

	//? < 1.21.9 {
	@Inject(method = "onEffectRemoved", at = @At("HEAD"))
	protected void effectRemoved(MobEffectInstance effectInstance, CallbackInfo ci)
	//? } else {

	/*@Inject(method = "onEffectsRemoved", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerGamePacketListenerImpl;send(Lnet/minecraft/network/protocol/Packet;)V", shift = At.Shift.AFTER))
	protected void effectRemoved(Collection<MobEffectInstance> collection, CallbackInfo ci, @Local MobEffectInstance effectInstance)
	*///? }
	{
		if (//? 1.20.1 {
			!(effectInstance.getEffect()/*? > 1.21 {*//*.value()*//*? } */ instanceof CreativeShockEffect effect)
			//? } else {
			/*!effectInstance.is(StellarityMobEffects.CREATIVE_SHOCK)
			 *///? }
		) return;

		if (initialGameType == null) initialGameType = GameType.SURVIVAL;

		setGameMode(initialGameType);
		initialGameType = null;
	}

	@Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
	public void saveData(
		//? < 1.21.9 {
		CompoundTag tag, CallbackInfo ci
		//? } else {
		/*ValueOutput tag, CallbackInfo ci
		 *///? }
	) {
		if (initialGameType != null) tag.putString("stellarity:initial_gamemode", initialGameType.getName());
	}

	//? > 1.21.9
	//@Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
	public void readData(
		//? < 1.21.9 {
		CompoundTag tag, CallbackInfo ci
		//? } else {
		/*ValueInput tag, CallbackInfo ci
		 *///? }
	) {
		if (tag.contains("stellarity:initial_gamemode")) {
			initialGameType = GameType.byName(tag.getString("stellarity:initial_gamemode")/*? > 1.21.9 >> ');'*//*.orElse("survival")*/);
		}
	}


}
