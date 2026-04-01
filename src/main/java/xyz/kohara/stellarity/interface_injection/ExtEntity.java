package xyz.kohara.stellarity.interface_injection;

import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import xyz.kohara.stellarity.registry.StellarityDataAttachments;

@SuppressWarnings("NonExtendableApiUsage")
public interface ExtEntity extends AttachmentTarget {

	default void stellarity$setGlowColor(int color) {
		this.setAttached(StellarityDataAttachments.GLOW_COLOR, color);
	}

	default int stellarity$getGlowColor() {
		return this.getAttachedOrElse(StellarityDataAttachments.GLOW_COLOR, -1);
	}
}