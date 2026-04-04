package prismatic.shards.stellarity.interface_injection;

import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.StellarityDataAttachments;

import java.util.HashMap;

@SuppressWarnings("NonExtendableApiUsage")
public interface ExtAbstractArrow extends AttachmentTarget {

	default int stellarity$levitationShot() {
		var attachment = this.getAttached(StellarityDataAttachments.ENCHANTMENTS);
		if (attachment == null) return 0;
		return Math.max(0, attachment.getOrDefault(Stellarity.id("levitation_shot"), 0));
	}

	default void stellarity$setLevitationShot(int levitationShot) {
		this.getAttachedOrSet(StellarityDataAttachments.ENCHANTMENTS, new HashMap<>()).put(Stellarity.id("levitation_shot"), levitationShot);
	}

	default boolean stellarity$voidShot() {
		var attachment = this.getAttached(StellarityDataAttachments.ENCHANTMENTS);
		if (attachment == null) return false;
		return attachment.getOrDefault(Stellarity.id("void_shot"), 0) > 0;
	}

	default void stellarity$setVoidShot(boolean voidShot) {
		this.getAttachedOrSet(StellarityDataAttachments.ENCHANTMENTS, new HashMap<>()).put(Stellarity.id("void_shot"), voidShot ? 1 : 0);
	}

}
