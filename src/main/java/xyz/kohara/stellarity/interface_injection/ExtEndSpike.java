package xyz.kohara.stellarity.interface_injection;


import net.minecraft.world.level.levelgen.feature.SpikeFeature;

public interface ExtEndSpike {
	default boolean stellarity$hasAltar() {
		throw new AssertionError("Not transformed");
	}

	default void stellarity$setAltar(boolean hasAltar) {
		throw new AssertionError("Not transformed");
	}

	default boolean stellarity$hasCryingObsidianTops() {
		throw new AssertionError("Not transformed");
	}

	default void stellarity$setCryingObsidianTops(boolean cryingObsidianTops) {
		throw new AssertionError("Not transformed");
	}

	static SpikeFeature.EndSpike apply(SpikeFeature.EndSpike spike, boolean hasAltar, boolean cryingObsidianTops) {
		spike.stellarity$setAltar(hasAltar);
		spike.stellarity$setCryingObsidianTops(cryingObsidianTops);
		return spike;
	}

	static SpikeFeature.EndSpike applyDefaults(SpikeFeature.EndSpike spike) {
		return apply(spike, false, false);
	}
}
