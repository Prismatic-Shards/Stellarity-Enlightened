package xyz.kohara.stellarity.interface_injection;


public interface ExtEndSpike {
    default boolean stellarity$hasAltar() {
        throw new AssertionError("Not transformed");
    }

    default void stellarity$setAltar(boolean hasAltar) {
        throw new AssertionError("Not transformed");
    }
}
