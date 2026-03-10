package xyz.kohara.stellarity.interface_injection;

public interface ExtAbstractArrow {

	default int stellarity$levitationShot() {
		throw new AssertionError("Not transformed!");
	}

	default void stellarity$setLevitationShot(int levitationShot) {
		throw new AssertionError("Not transformed!");
	}

	default boolean stellarity$voidShot() {
		throw new AssertionError("Not transformed!");
	}

	default void stellarity$setVoidShot(boolean voidShot) {
		throw new AssertionError("Not transformed!");
	}

}
