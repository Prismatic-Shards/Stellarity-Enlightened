package dev.coder2195.stellarity.crafting;

import dev.coder2195.stellarity.block.AltarOfTheAccursed;
import dev.coder2195.stellarity.entity.SatchelSigil;
import dev.coder2195.stellarity.interface_injection.ExtItemEntity;
import dev.coder2195.stellarity.registry.StellarityBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements ExtItemEntity {
	public ItemEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@Unique
	private long consecrateAt = 0;

	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;tick()V", shift = At.Shift.AFTER))
	public void movedOffRecipeBlock(CallbackInfo ci) {
		if (!stellarity$getItemMode().equals(ExtItemEntity.ItemMode.ALTAR_CRAFTING)) return;
		if (level() instanceof ServerLevel level) {
			var position = this.position();
			for (var corner : List.of(
				position.add(0, -0.75, 0),
				position.add(0.5, -0.75, 0.5),
				position.add(0.5, -0.75, -0.5),
				position.add(-0.5, -0.75, -0.5),
				position.add(-0.5, -0.75, 0.5)

			)) {
				var blockstate = level.getBlockState(BlockPos.containing(corner));

				if (blockstate.is(StellarityBlocks.ALTAR_OF_THE_ACCURSED) && !blockstate.getValue(AltarOfTheAccursed.LOCKED))
					return;
			}

			if (level.getEntitiesOfClass(SatchelSigil.class, this.getBoundingBox()).stream().noneMatch(SatchelSigil::isActive))
				stellarity$setItemMode(ExtItemEntity.ItemMode.DEFAULT);
		}
	}
}
