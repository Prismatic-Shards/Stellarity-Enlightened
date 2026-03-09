package xyz.kohara.stellarity.utils;

import net.minecraft.world.level.levelgen.feature.SpikeFeature;

import java.util.List;

public class Constants {
    public static final List<SpikeFeature.EndSpike> OBSIDIAN_SPIKES = List.of(
        new SpikeFeature.EndSpike(63, 0, 5, 100, false),
        new SpikeFeature.EndSpike(50, 36, 4, 105, false),
        new SpikeFeature.EndSpike(18, 59, 4, 94, false),
        new SpikeFeature.EndSpike(-19, 59, 5, 106, false),
        new SpikeFeature.EndSpike(-51, 36, 4, 105, false),
        new SpikeFeature.EndSpike(-63, 0, 5, 93, false),
        new SpikeFeature.EndSpike(-51, -39, 6, 100, false),
        new SpikeFeature.EndSpike(-19, -60, 6, 96, false),
        new SpikeFeature.EndSpike(18, -60, 5, 87, false),
        new SpikeFeature.EndSpike(50, -39, 8, 95, false)
    );
}
