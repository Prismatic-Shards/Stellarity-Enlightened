package xyz.kohara.stellarity.registry.entity;


import net.minecraft.core.particles.DustParticleOptions;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ByIdMap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityEntities;
import xyz.kohara.stellarity.registry.StellarityItems;
//? > 1.21.9 {
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
//? } else {
/*import net.minecraft.nbt.CompoundTag;
	*///? }

import java.util.Set;
import java.util.function.IntFunction;

public class ThrownPrismaticPearl extends ThrowableItemProjectile {
	public ThrownPrismaticPearl(EntityType<? extends ThrownPrismaticPearl> entityType, Level level) {
		super(entityType, level);
	}

	private @Nullable Vec3 oldPos = null;

	public ThrownPrismaticPearl(Level level, LivingEntity livingEntity, ItemStack itemStack) {
		//? > 1.21.9 {
		super(StellarityEntities.PRISMATIC_PEARL, livingEntity, level, itemStack);
		 //? } else {
		/*super(StellarityEntities.PRISMATIC_PEARL, livingEntity, level);
		setItem(itemStack);


		*///? }
	}

	@Override
	public void shootFromRotation(Entity entity, float f, float g, float h, float i, float j) {
		super.shootFromRotation(entity, f, g, h, i, j);

		if (!level().isClientSide() && entity instanceof Player player) {
			//~ if > 1.21.9 'getName()' -> 'name()'
			String name = player.getGameProfile().name();
			if (name.equalsIgnoreCase("bush_moss")) setTrailType(TrailType.BISEXUAL);
			else if (name.equalsIgnoreCase("coder2195")) setTrailType(TrailType.TRANSGENDER);
		}
	}

	public enum TrailType {
		NORMAL(0, new int[]{
			0xfa80fc,
			0xfa9ce0,
			0xfab5c7,
			0xfac5b7,
			0xfad9a3,
			0xcb6448,
			0x7a07f6,
			0x77e1a3,
			0x77b8cc,
			0x935118,
			0xbbff41,
			0xcae150,
			0xfa8150,
			0xfa811d
		}),
		BISEXUAL(1, new int[]{
			0xD60270,
			0xD60270,
			0xD60270,
			0xD60270,
			0x6348a3,
			0x6348a3,
			0x6348a3,
			0x6348a3,
			0x004be0,
			0x004be0,
			0x004be0,
			0x004be0
		}),
		TRANSGENDER(2, new int[]{
			0x5BCEFA,
			0x5BCEFA,
			0x5BCEFA,
			0x5BCEFA,
			0xF5A9B8,
			0xF5A9B8,
			0xF5A9B8,
			0xF5A9B8,
			0xFFFFFF,
			0xFFFFFF,
			0xFFFFFF,
			0xFFFFFF
		});

		public final int id;
		public final int[] colors;

		TrailType(int id, int[] colors) {
			this.id = id;
			this.colors = colors;
		}

		public int id() {
			return id;
		}

		public static final IntFunction<TrailType> BY_ID = ByIdMap.continuous(TrailType::id, values(), ByIdMap.OutOfBoundsStrategy.WRAP);


	}

	// first one is + 0	but intellj simplies it away
	public static final int DATA_SIZE = Entity.stellarity$DATA_SIZE + 1;
	// first one is + 0	but intellj simplies it away
	public static EntityDataAccessor<Integer> DATA_TRAIL_TYPE = new EntityDataAccessor<>(Entity.stellarity$DATA_SIZE, EntityDataSerializers.INT);


	@Override
	public void stellarity$defineSynchedData() {
		super.stellarity$defineSynchedData();

		stellarity$addSynchedData(DATA_TRAIL_TYPE, TrailType.NORMAL.id());
	}

	public TrailType getTrailType() {
		return TrailType.BY_ID.apply(stellarity$entityData().get(DATA_TRAIL_TYPE));
	}

	public void setTrailType(TrailType mode) {
		stellarity$entityData().set(DATA_TRAIL_TYPE, mode.id());
	}

	//? 1.21.1 {
	/*@Override
	public void readAdditionalSaveData(CompoundTag compoundTag) {
		super.readAdditionalSaveData(compoundTag);
		if (compoundTag.contains("stellarity:trail_type")) {
			TrailType trailType = TrailType.NORMAL;
			try {
				trailType = TrailType.valueOf(compoundTag.getString("stellarity:trail_type").toUpperCase());
			} catch (Exception e) {
				Stellarity.LOGGER.warn("Failed to parse prismatic pearl trail type");
			}
			setTrailType(trailType);
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compoundTag) {
		super.addAdditionalSaveData(compoundTag);
		compoundTag.putString("stellarity:trail_type", getTrailType().toString());
	}
	*///? } else {

	@Override
	protected void readAdditionalSaveData(ValueInput valueInput) {
		super.readAdditionalSaveData(valueInput);
		TrailType trailType = TrailType.NORMAL;
		try {
			trailType = TrailType.valueOf(valueInput.getStringOr("stellarity:trail_type", "NORMAL").toUpperCase());
		} catch (Exception e) {
			Stellarity.LOGGER.warn("Failed to parse prismatic pearl trail type");
		}
		setTrailType(trailType);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput valueOutput) {
		super.addAdditionalSaveData(valueOutput);
		valueOutput.putString("stellarity:trail_type", getTrailType().toString());
	}

	//? }

	private int colorIndex = 0;


	@Override
	public void tick() {
		super.tick();
		var level = level();

		int[] colors = getTrailType().colors;

		var position = position();
		var x = position.x;
		var y = position.y;
		var z = position.z;

		if (oldPos == null) {
			oldPos = new Vec3(x, y, z);
		}

		var dx = x - oldPos.x;
		var dy = y - oldPos.y;
		var dz = z - oldPos.z;

		long steps = (int) (Math.max(Math.max(Math.abs(dx), Math.abs(dy)), Math.abs(dz)) / 0.1);

		var xStep = dx / steps;
		var yStep = dy / steps;
		var zStep = dz / steps;

		if (colorIndex >= colors.length || colorIndex < 0) {
			colorIndex = 0;
		}

		for (int i = 0; i <= steps + 1; i++) {
			var color = colors[colorIndex];

			stellarity$setGlowColor(color);
			level.addParticle(new DustParticleOptions(/*? > 1.21.9 { */color/*? } else { *//*Vec3.fromRGB24(color).toVector3f()*//*? }*/, 1.5f), x + i * xStep, y + i * yStep, z + i * zStep, 0, 0, 0);
		}

		colorIndex++;

		oldPos = new Vec3(x, y, z);

	}

	public ThrownPrismaticPearl(Level level, LivingEntity livingEntity) {
		super(StellarityEntities.PRISMATIC_PEARL, livingEntity, level /*? > 1.21.9 {*/, new ItemStack(StellarityItems.PRISMATIC_PEARL)/*? } */);
	}

	@Override
	protected Item getDefaultItem() {
		return StellarityItems.PRISMATIC_PEARL;
	}

	private static final int[] LAND_COLORS = {
		0xfa80fc, 0xfa9ce0, 0xfab5c7, 0xfac5b7, 0xfad9a3, 0xcb6448, 0x7a07f6, 0x77e1a3, 0x77b8cc, 0x935118, 0xbbff41, 0xcae150, 0xfa8150, 0xfa811d
	};

	@Override
	protected void onHit(HitResult hitResult) {
		super.onHit(hitResult);

		var level = level();
		var position = position();

		if (level instanceof ServerLevel serverLevel && !isRemoved()) {
			var owner = getOwner();
			if (owner != null) {
				owner.teleportTo(serverLevel, position.x, position.y, position.z, Set.of(), owner.getYHeadRot(), owner.getXRot() /*? > 1.21.9 {*/, true /*? }*/);
				level.playSound(null, owner.blockPosition(), SoundEvents.ENDER_EYE_DEATH, SoundSource.NEUTRAL);
			}


			this.discard();
		}

		for (int color : LAND_COLORS) {
			level.addParticle(new DustParticleOptions(/*? > 1.21.9 { */color/*? } else { *//*Vec3.fromRGB24(color).toVector3f()*//*? }*/, 1.5f), position.x + random.nextDouble() * 2 - 1, position.y, position.z + random.nextDouble() * 2 - 1, 0, 0, 0);
		}
	}

	@Override
	public void syncPacketPositionCodec(double d, double e, double f) {
		super.syncPacketPositionCodec(d, e, f);
	}

	protected void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);
		var level = level();
		if (level.isClientSide()) return;
		entityHitResult.getEntity()./*? 1.21.1 {*//*hurt(*//*? } else {*/hurtServer((ServerLevel) level(), /*? } */this.damageSources().thrown(this, this.getOwner()), 0.0F);
	}


}
