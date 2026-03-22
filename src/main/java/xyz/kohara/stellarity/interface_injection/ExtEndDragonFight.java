package xyz.kohara.stellarity.interface_injection;

public interface ExtEndDragonFight /*? 1.21.1 >> '{'*/ /*extends ExtEndDragonFightData */{
	//? > 1.21.1 {
	default boolean stellarity$portalChestGenerated() {
		throw new AssertionError("Not transformed!");
	}

	default void stellarity$setPortalChestGenerated(boolean generated) {
		throw new AssertionError("Not transformed!");
	}
	//? }
}
