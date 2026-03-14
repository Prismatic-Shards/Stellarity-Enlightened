package xyz.kohara.stellarity.mixin.exit_portal;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.interface_injection.ExtEndDragonFight;
//? > 1.21 {
/*import net.minecraft.core.registries.Registries;
*///? }


@Mixin(EndDragonFight.class)
public abstract class EndDragonFightMixin implements ExtEndDragonFight {
	@Shadow
	@Nullable
	public BlockPos portalLocation;

	@Shadow
	@Final
	private ServerLevel level;

	@Shadow
	public abstract boolean hasPreviouslyKilledDragon();

	@Redirect(method = "tryRespawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;relative(Lnet/minecraft/core/Direction;I)Lnet/minecraft/core/BlockPos;"))
	private BlockPos adjustPosition(BlockPos blockPos, Direction direction, int i) {
		return blockPos.relative(direction, 4).above(2);
	}

	@Inject(method = "spawnExitPortal", at = @At("TAIL"))
	private void placeChest(boolean bl, CallbackInfo ci) {
		if (stellarity$portalChestGenerated() || portalLocation == null) return;
		var chestPos = portalLocation.offset(7, 1, 0);
		level.setBlock(chestPos, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.EAST), Block.UPDATE_CLIENTS);
		var entity = level.getBlockEntity(chestPos);

		if (entity instanceof ChestBlockEntity chestEntity) {
			chestEntity.setLootTable(/*? 1.20.1 { */Stellarity.id("exit_portal") /*? } else {*/ /*Stellarity.key(Registries.LOOT_TABLE, "exit_portal")*//*? }*/, level.getSeed());

			stellarity$setPortalChestGenerated(true);
		}
	}

	@WrapOperation(method = "onCrystalDestroyed", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/dimension/end/EndDragonFight;spawnExitPortal(Z)V"))
	private void dontAllowEscape(EndDragonFight instance, boolean bl, Operation<Void> original) {
		if (hasPreviouslyKilledDragon()) original.call(instance, bl);
	}


}
