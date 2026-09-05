package dev.coder2195.stellarity.feature;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.coder2195.stellarity.Stellarity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import org.jspecify.annotations.NonNull;

public record CrystalTowerFeature(IntProvider width, IntProvider height, Holder<BlockStateProvider> material) implements Feature {
	public static final MapCodec<CrystalTowerFeature> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
		IntProviders.CODEC.fieldOf("width").forGetter(CrystalTowerFeature::width),
		IntProviders.CODEC.fieldOf("height").forGetter(CrystalTowerFeature::height),
		BlockStateProvider.CODEC.fieldOf("material").forGetter(CrystalTowerFeature::material)
	).apply(instance, CrystalTowerFeature::new));

	@Override
	public @NonNull MapCodec<? extends Feature> codec() {
		return CODEC;
	}

	public static boolean[][] pattern(int width) {
		if (width < 1) return null;
		if (width == 1) return new boolean[][]{{true}};
		if (width == 2) return new boolean[][]{{true, true}, {true, true}};
		if (width == 3) return new boolean[][]{{false, true, false}, {true, true, true}, {false, true, false}};

		boolean[][] arr = new boolean[width][width];
		return arr;
	}

	@Override
	public boolean place(@NonNull WorldGenLevel level, @NonNull ChunkGenerator chunkGenerator, @NonNull RandomSource random, @NonNull BlockPos origin) {
		var width = this.width.sample(random);
		var height = this.height.sample(random);
		var material = this.material.value().getState(level, random, origin);

		if (width < 1 || width > 16) {
			Stellarity.LOGGER.error("Tried generating a CrystalTowerFeature but got width: {}, which must be 1-16", width);
			return false;
		}
		if (height < 1) {
			Stellarity.LOGGER.error("Tried generating a CrystalTowerFeature but got height: {} < 1", height);
			return false;
		}



		int maxY = level.getMaxY();


		return true;
	}
}
