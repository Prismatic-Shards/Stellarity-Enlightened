package xyz.kohara.stellarity.mixin.dragon_fight;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(EndDragonFight.Data.class)
public class EndDragonFightDataMixin {

	@WrapOperation(method = "<clinit>", at = @At(value = "NEW", target = "(ZZZZLjava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;)Lnet/minecraft/world/level/dimension/end/EndDragonFight$Data;"))
	private static EndDragonFight.Data changeDefault(boolean bl, boolean bl2, boolean bl3, boolean bl4, Optional optional, Optional optional2, Optional optional3, Operation<EndDragonFight.Data> original) {

		return original.call(bl, true, false, false, optional, optional2, optional3);
	}


}
