package prismatic.shards.stellarity.registry.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.registry.StellarityEntities;
import prismatic.shards.stellarity.registry.StellarityItems;
import prismatic.shards.stellarity.registry.StellarityMobEffects;

public class VoidedSkeleton extends Skeleton {
	public VoidedSkeleton(EntityType<? extends VoidedSkeleton> type, Level level) {
		super(type, level);
	}

	public VoidedSkeleton(Level level) {
		this(StellarityEntities.VOIDED_SKELETON, level);
	}


	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
		super.populateDefaultEquipmentSlots(random, difficulty);
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(StellarityItems.CALL_OF_THE_VOID));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Skeleton.createAttributes();
	}

	@Override
	public boolean doHurtTarget(@NonNull ServerLevel level, @NonNull Entity target) {
		if (!super.doHurtTarget(level, target)) return false;

		if (target instanceof LivingEntity entity) {
			entity.addEffect(new MobEffectInstance(StellarityMobEffects.VOIDED, 10 * 20));
		}

		return true;
	}
}
