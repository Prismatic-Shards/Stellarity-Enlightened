package prismatic.shards.stellarity.util;

import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.*;

import java.util.Arrays;

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
			weights[i] = new Weighted<>(num(values[2 * i]), values[2 * i + 1]);
		}

		return new WeightedListInt(WeightedList.of(weights));
	}

	static UniformInt num(int min, int max) {
		return UniformInt.of(min, max);
	}

	static <T> WeightedList<T> weighted(T[] values, int[] weights) {
		if (values.length != weights.length)
			throw new IllegalArgumentException("Blockstates must correspond to weights");
		@SuppressWarnings("unchecked") Weighted<T>[] weightList = new Weighted[values.length];
		for (int i = 0; i < values.length; i++) {
			weightList[i] = new Weighted<>(values[i], weights[i]);
		}

		return WeightedList.of(weightList);
	}

	static <T> WeightedList<T> weightedEven(T[] values) {
		//noinspection unchecked
		return WeightedList.of(Arrays.stream(values).map(value -> new Weighted<>(value, 1)).toArray(Weighted[]::new));
	}

	static TrapezoidFloat trapezoid(float min, float max, float plateau) {
		return new TrapezoidFloat(min, max, plateau);
	}

	static TrapezoidInt trapezoid(int min, int max, int plateau) {
		return new TrapezoidInt(min, max, plateau);
	}

}
