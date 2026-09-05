package dev.coder2195.stellarity.consume_effect;

import com.mojang.serialization.MapCodec;
import dev.coder2195.stellarity.registry.StellarityConsumeEffects;
import dev.coder2195.stellarity.registry.StellarityDataComponents;
import dev.coder2195.stellarity.data_component.LoafOfPlentyEats;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;

public class LoafOfPlentyConsumeEffect implements ConsumeEffect {
	public static final LoafOfPlentyConsumeEffect INSTANCE = new LoafOfPlentyConsumeEffect();

	public static final MapCodec<LoafOfPlentyConsumeEffect> CODEC = MapCodec.unit(INSTANCE);
	public static final StreamCodec<RegistryFriendlyByteBuf, LoafOfPlentyConsumeEffect> STREAM_CODEC = StreamCodec.unit(INSTANCE);
	@Override
	public Type<? extends ConsumeEffect> getType() {
		return StellarityConsumeEffects.LOAF_OF_PLENTY;
	}

	@Override
	public boolean apply(Level level, ItemStack stack, LivingEntity user) {
		if (!user.hasInfiniteMaterials()) stack.setCount(stack.getCount() + 1);

		stack.set(StellarityDataComponents.LOAF_OF_PLENTY_EATS, stack.getOrDefault(StellarityDataComponents.LOAF_OF_PLENTY_EATS, new LoafOfPlentyEats(0)).increment());

		return true;
	}
}
