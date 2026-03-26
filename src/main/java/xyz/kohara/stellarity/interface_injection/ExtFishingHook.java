package xyz.kohara.stellarity.interface_injection;

import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import xyz.kohara.stellarity.registry.StellarityDataAttachments;

@SuppressWarnings("NonExtendableApiUsage")
public interface ExtFishingHook extends AttachmentTarget {
	default void stellarity$setVoidFishingBuff(boolean buff) {
		this.setAttached(StellarityDataAttachments.VOID_FISHING_BUFF, buff);
	}

	default boolean stellarity$getVoidFishingBuff() {
		return this.getAttachedOrElse(StellarityDataAttachments.VOID_FISHING_BUFF, false);
	}


}
