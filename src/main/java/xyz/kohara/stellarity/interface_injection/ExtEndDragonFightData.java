//? 1.21.1 {

/*package xyz.kohara.stellarity.interface_injection;

import net.minecraft.world.level.dimension.end.EnderDragonFight;

public interface ExtEndDragonFightData {
	default boolean stellarity$portalChestGenerated() {
		throw new AssertionError("Not transformed!");
	}

	default void stellarity$setPortalChestGenerated(boolean generated) {
		throw new AssertionError("Not transformed!");
	}

	static EnderDragonFight.Data apply(EnderDragonFight.Data original, boolean generated) {
		original.stellarity$setPortalChestGenerated(generated);
		return original;
	}

	static EnderDragonFight.Data applyDefaults(EnderDragonFight.Data original) {
		return apply(original, false);
	}
}

*///? }