package dev.coder2195.stellarity.number_provider;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record Multiply(HolderSet<NumberProvider> factors) implements NumberProvider {
	public static final MapCodec<Multiply> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
		NumberProviders.LIST_CODEC.fieldOf("factors").forGetter(Multiply::factors)
	).apply(builder, Multiply::new));

	@SafeVarargs
	public static Holder<NumberProvider> multiply(Holder<NumberProvider>... factors) {
		return Holder.direct( new Multiply(HolderSet.direct(factors)));
	}


	@Override
	public float getFloat(@NonNull LootContext context) {
		float product = 1;
		for (var factor : factors) {
			product *= factor.value().getFloat(context);
		}
		return product;
	}

	@Override
	public @NonNull MapCodec<? extends NumberProvider> codec() {
		return CODEC;
	}
}
