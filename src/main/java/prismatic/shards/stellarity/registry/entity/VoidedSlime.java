package prismatic.shards.stellarity.registry.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.registry.StellarityEntities;
import prismatic.shards.stellarity.registry.StellarityMobEffects;

public class VoidedSlime extends Slime {
	public VoidedSlime(EntityType<? extends VoidedSlime> type, Level level) {
		super(type, level);
	}

	public VoidedSlime(Level level) {
		this(StellarityEntities.VOIDED_SLIME, level);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes();
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
