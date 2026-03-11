package xyz.kohara.stellarity.interface_injection;

import net.minecraft.world.level.dimension.end.EndDragonFight;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public interface ExtEndDragonFightData {
	default Map<UUID, Integer> stellarity$dragonSummonTracker() {
		throw new AssertionError("Not transformed!");
	}

	default void stellarity$setDragonSummonTracker(Map<UUID, Integer> map) {
		throw new AssertionError("Not transformed!");
	}

	static EndDragonFight.Data apply(EndDragonFight.Data original, Map<UUID, Integer> tracker) {
		original.stellarity$setDragonSummonTracker(tracker);
		return original;
	}

	static EndDragonFight.Data applyDefaults(EndDragonFight.Data original) {
		return apply(original, new HashMap<>());
	}
}
