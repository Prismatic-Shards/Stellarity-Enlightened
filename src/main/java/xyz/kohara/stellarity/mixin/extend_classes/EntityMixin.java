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

}
