package xyz.kohara.stellarity.interface_injection;

import net.minecraft.world.level.dimension.end.EndDragonFight;

public interface ExtEndDragonFightData {
	default boolean stellarity$portalChestGenerated() {
		throw new AssertionError("Not transformed!");
	}

	default void stellarity$setPortalChestGenerated(boolean generated) {
		throw new AssertionError("Not transformed!");
	}

	static EndDragonFight.Data apply(EndDragonFight.Data original, boolean generated) {
		original.stellarity$setPortalChestGenerated(generated);
		return original;
	}

	static EndDragonFight.Data applyDefaults(EndDragonFight.Data original) {
		return apply(original, false);
	}
}
