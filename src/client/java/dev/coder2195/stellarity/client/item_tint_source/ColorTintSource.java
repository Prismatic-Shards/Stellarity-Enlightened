package dev.coder2195.stellarity.client.item_tint_source;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import dev.coder2195.stellarity.registry.StellarityDataComponents;
import dev.coder2195.stellarity.block.ColoredLeavesBlock;
import dev.coder2195.stellarity.data_component.Color;
import org.jspecify.annotations.Nullable;

public class ColorTintSource implements ItemTintSource {
	public static final MapCodec<ColorTintSource> MAP_CODEC = MapCodec.unit(ColorTintSource::new);

	@Override
	public int calculate(ItemStack itemStack, @Nullable ClientLevel level, @Nullable LivingEntity owner) {
		return itemStack.getOrDefault(StellarityDataComponents.COLOR, new Color(ColoredLeavesBlock.DEFAULT_COLOR)).rgb() | 0xff000000;
	}

	@Override
	public MapCodec<? extends ItemTintSource> type() {
		return MAP_CODEC;
	}
}
