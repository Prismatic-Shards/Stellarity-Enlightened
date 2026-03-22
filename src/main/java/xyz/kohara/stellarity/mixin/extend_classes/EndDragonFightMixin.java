package xyz.kohara.stellarity.mixin.extend_classes;


import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.dimension.end.EnderDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.interface_injection.ExtEndDragonFight;

@Mixin(EnderDragonFight.class)
public class EndDragonFightMixin implements ExtEndDragonFight {
	@Unique
	private boolean portalChestGenerated;

	@Override
	public boolean stellarity$portalChestGenerated() {
		return portalChestGenerated;
	}

	@Override
	public void stellarity$setPortalChestGenerated(boolean generated) {
		this.portalChestGenerated = generated;
	}

	@WrapMethod(method = "saveData")
	private EnderDragonFight.Data saveStellarityData(Operation<EnderDragonFight.Data> original) {
		var data = original.call();
		data.stellarity$setPortalChestGenerated(portalChestGenerated);
		return data;
	}

	@Inject(method = "<init>(Lnet/minecraft/server/level/ServerLevel;JLnet/minecraft/world/level/dimension/end/EnderDragonFight$Data;Lnet/minecraft/core/BlockPos;)V", at = @At("TAIL"))
	private void loadStellarityData(ServerLevel serverLevel, long l, EnderDragonFight.Data data, BlockPos blockPos, CallbackInfo ci) {
		portalChestGenerated = data.stellarity$portalChestGenerated();
	}
}
