package xyz.kohara.stellarity.utils;

import net.minecraft.world.level.levelgen.feature.SpikeFeature;
import xyz.kohara.stellarity.interface_injection.ExtEndSpike;

import java.util.List;

public class Constants {
	public static final List<SpikeFeature.EndSpike> OBSIDIAN_SPIKES = List.of(
		ExtEndSpike.apply(new SpikeFeature.EndSpike(63, 0, 5, 100, false), false, true),
		ExtEndSpike.apply(new SpikeFeature.EndSpike(50, 36, 4, 105, false), false, true),
		ExtEndSpike.apply(new SpikeFeature.EndSpike(18, 59, 4, 94, false), false, true),
		ExtEndSpike.apply(new SpikeFeature.EndSpike(-19, 59, 5, 106, false), false, true),
		ExtEndSpike.apply(new SpikeFeature.EndSpike(-51, 36, 4, 105, false), false, true),
		ExtEndSpike.apply(new SpikeFeature.EndSpike(-63, 0, 5, 93, false), false, true),
		ExtEndSpike.apply(new SpikeFeature.EndSpike(-51, -39, 6, 100, false), false, true),
		ExtEndSpike.apply(new SpikeFeature.EndSpike(-19, -60, 6, 96, false), false, true),
		ExtEndSpike.apply(new SpikeFeature.EndSpike(18, -60, 5, 87, false), false, true),
		ExtEndSpike.apply(new SpikeFeature.EndSpike(50, -39, 8, 105, false), true, true)
	);
}
