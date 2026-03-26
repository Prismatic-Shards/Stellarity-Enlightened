package xyz.kohara.stellarity.mixin.extend_classes;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import xyz.kohara.stellarity.interface_injection.ExtEntity;

@Mixin(Entity.class)
public abstract class EntityMixin implements ExtEntity {

	@WrapMethod(method = "getTeamColor")
	private int customGlowColor(Operation<Integer> original) {
		int color = stellarity$getGlowColor();
		if (color != -1) return color;

		return original.call();
	}
}
