package xyz.kohara.stellarity.mixin.extend_classes;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.fabricmc.fabric.impl.attachment.AttachmentTargetImpl;
import net.fabricmc.fabric.impl.attachment.sync.AttachmentChange;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import xyz.kohara.stellarity.interface_injection.ExtEntity;

import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("UnstableApiUsage")
@Mixin(Entity.class)
public abstract class EntityMixin implements ExtEntity, AttachmentTargetImpl {
	@Shadow
	public abstract Level level();

	@WrapMethod(method = "getTeamColor")
	private int customGlowColor(Operation<Integer> original) {
		int color = stellarity$getGlowColor();
		if (color != -1) return color;

		return original.call();
	}

	@SuppressWarnings("UnstableApiUsage")
	@WrapMethod(method = "load")
	private void bugFixFabricAttachmentSync(ValueInput input, Operation<Void> original) {
		if (!fabric_shouldTryToSync()) {
			original.call(input);
			return;
		}

		var oldAttachments = this.fabric_getAttachments();
		oldAttachments = oldAttachments != null ? new HashMap<>(oldAttachments) : new HashMap<>();

		original.call(input);

		var newAttachments = this.fabric_getAttachments();
		newAttachments = newAttachments != null ? new HashMap<>(newAttachments) : new HashMap<>();

		var allKeys = new HashSet<>(oldAttachments.keySet());
		allKeys.addAll(newAttachments.keySet());


		for (var key : allKeys) {
			var newValue = newAttachments.get(key);
			if (!key.isSynced() || newValue != null && newValue.equals(oldAttachments.get(key))) continue;

			AttachmentChange change = AttachmentChange.create(fabric_getSyncTargetInfo(), key, newValue, fabric_getRegistryAccess());
			this.fabric_syncChange(key, change);
		}
	}
}
