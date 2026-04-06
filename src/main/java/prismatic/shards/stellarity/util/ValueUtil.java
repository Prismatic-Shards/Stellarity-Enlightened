package prismatic.shards.stellarity.util;

import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;

public interface ValueUtil {
	static ConstantInt num(int value) {
		return ConstantInt.of(value);
	}

	/**
	 * DATA THEN WEIGHT I MADE A JAVADOC CUZ OF THIS REASON
	 *
	 * @param values DATA THEN WEIGHT
	 * @return weightedlist thingy
	 */
	static WeightedListInt weighted(int... values) {
		if (values.length % 2 != 0) throw new IllegalArgumentException("Must be even number of values (value, weight)");
		@SuppressWarnings("unchecked") Weighted<IntProvider>[] weights = new Weighted[values.length / 2];

		for (int i = 0; i < weights.length; i++) {
			weights[i] = new Weighted<>(num(values[i]), values[i + 1]);
		}

		return new WeightedListInt(WeightedList.of(weights));
	}

	static UniformInt num(int min, int max) {
		return UniformInt.of(min, max);
	}

	static <T> WeightedList<T> weighted(T[] blockStateProvider, int[] weights) {
		if (blockStateProvider.length != weights.length)
			throw new IllegalArgumentException("Blockstates must correspond to weights");
		@SuppressWarnings("unchecked") Weighted<T>[] weightList = new Weighted[blockStateProvider.length];
		for (int i = 0; i < blockStateProvider.length; i++) {
			weightList[i] = new Weighted<>(blockStateProvider[i], weights[i]);
		}

		return WeightedList.of(weightList);
	}

}
