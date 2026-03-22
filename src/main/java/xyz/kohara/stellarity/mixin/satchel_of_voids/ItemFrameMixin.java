package xyz.kohara.stellarity.mixin.satchel_of_voids;


import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.registry.StellarityBlocks;
import xyz.kohara.stellarity.registry.StellarityItems;
import xyz.kohara.stellarity.registry.block.AltarOfTheAccursed;
//? > 1.21.10 {
import net.minecraft.core.particles.ColorParticleOption;
 //? }

@Mixin(ItemFrame.class)
public abstract class ItemFrameMixin extends HangingEntity {
	protected ItemFrameMixin(EntityType<? extends HangingEntity> entityType, Level level) {
		super(entityType, level);
	}

	@Inject(method = "onItemChanged", at = @At("HEAD"), cancellable = true)
	private void convertIntoAltar(ItemStack itemStack, CallbackInfo ci) {

		if (!(level() instanceof ServerLevel level) || !itemStack.is(StellarityItems.SATCHEL_OF_VOIDS)) return;

		var targetPos = blockPosition().relative(getDirection().getOpposite(), 1);
		if (!level.getBlockState(targetPos).is(Blocks.CRYING_OBSIDIAN)) return;

		level.setBlockAndUpdate(targetPos, StellarityBlocks.ALTAR_OF_THE_ACCURSED.defaultBlockState().setValue(AltarOfTheAccursed.PLACE_TYPE, AltarOfTheAccursed.PlaceType.SATCHEL).setValue(AltarOfTheAccursed.LOCKED, false));

		var center = targetPos.getCenter();
		level.sendParticles(/*? 1.21.1 {*/ /*ParticleTypes.FLASH*//*? } else {*/ ColorParticleOption.create(ParticleTypes.FLASH, -1) /*? }*/, center.x, center.y, center.z, 1, 0, 0, 0, 0);

		this.discard();

		ci.cancel();
	}
}
