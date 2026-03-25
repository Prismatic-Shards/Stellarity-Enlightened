package xyz.kohara.stellarity.interface_injection;

public interface ExtEnderDragonFight {

	default boolean stellarity$portalChestGenerated() {
		throw new AssertionError("Not transformed!");
	}

	default void stellarity$setPortalChestGenerated(boolean generated) {
		throw new AssertionError("Not transformed!");
	}

}
