package xyz.kohara.stellarity.mixin.end_crystal;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.end.EnderDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.interface_injection.ExtEndCrystal;

@Mixin(EndCrystal.class)
public abstract class EndCrystalMixin extends Entity implements ExtEndCrystal {
	@Unique
	Type type = Type.NORMAL;

	public EndCrystalMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	public void stellarity$setType(Type type) {
		this.type = type;
		boolean glow = type != Type.NORMAL;
		setGlowingTag(glow);
		this.stellarity$setGlowColor(glow ? 11141290 : -1);
	}

	@Override
	public Type stellarity$getType() {
		return type;
	}


	@WrapOperation(method = "hurtServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/DamageSource;is(Lnet/minecraft/tags/TagKey;)Z"))

	public boolean explodeOnlyNormal(DamageSource instance, TagKey<DamageType> tagKey, Operation<Boolean> original) {

		return original.call(instance, tagKey) || type != Type.NORMAL;
	}


	@Inject(method = "hurtServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/enderdragon/EndCrystal;onDestroyedBy(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;)V"))
	private void dropCrystal(ServerLevel level, DamageSource damageSource, float f, CallbackInfoReturnable<Boolean> cir) {


		BlockPos pos = blockPosition();
		if (level.getBlockState(pos).is(BlockTags.FIRE)) {
			level.setBlock(blockPosition(), Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
		}
		if (type == Type.RESPAWN && !damageSource.isCreativePlayer())
			level.addFreshEntity(new ItemEntity(level, getX(), getY(), getZ(), new ItemStack(Items.END_CRYSTAL)));

	}

	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/enderdragon/EndCrystal;blockPosition()Lnet/minecraft/core/BlockPos;", shift = At.Shift.AFTER))
	public void checkType(CallbackInfo ci) {

		ServerLevel level = (ServerLevel) level();
		EnderDragonFight dragonFight = level.getDragonFight();
		BlockPos blockPos = blockPosition();
		BlockPos portalLocation = dragonFight == null ? null : dragonFight.exitPortalLocation;
		if (type == Type.RESPAWN) {
			if (portalLocation == null) {
				stellarity$setType(Type.NORMAL);
				Stellarity.LOGGER.info("bad respawn crystal");
			} else {
				boolean valid = false;
				for (Direction direction : Direction.Plane.HORIZONTAL) {
					if (portalLocation.above(3).relative(direction, 4).equals(blockPos)) {
						valid = true;
						break;
					}
				}
				if (!valid) {
					stellarity$setType(Type.NORMAL);
					Stellarity.LOGGER.info("bad respawn crystal 2");
				}
			}
		} else if (type == Type.NORMAL) {
			if (portalLocation != null) {
				for (Direction direction : Direction.Plane.HORIZONTAL) {
					if (portalLocation.above(3).relative(direction, 4).equals(blockPos)) {
						stellarity$setType(Type.RESPAWN);
						Stellarity.LOGGER.info("Found respawn crystal");
						break;
					}
				}
			}
		}
	}
}
