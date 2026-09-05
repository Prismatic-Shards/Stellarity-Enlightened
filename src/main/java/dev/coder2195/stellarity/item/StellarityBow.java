package dev.coder2195.stellarity.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import dev.coder2195.stellarity.util.tuple.Tuple3;
import org.jspecify.annotations.Nullable;

public interface StellarityBow {
	Tuple3<@Nullable SoundEvent, @Nullable Float, @Nullable Float> DEFAULT = new Tuple3<>(null, null, null);

	default Tuple3<@Nullable SoundEvent, @Nullable Float, @Nullable Float> shootSound(final ItemStack weapon, ItemStack projectile, final Level level, final LivingEntity entity) {
		return DEFAULT;
	}
}
