package xyz.kohara.stellarity.mixin.potions;


import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.world.item.PotionItem;

//? 1.21.1 {
/*import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.item.alchemy.PotionContents;
import org.spongepowered.asm.mixin.injection.At;
import xyz.kohara.stellarity.Stellarity;
*///? }

@Mixin(PotionItem.class)
public class PotionItemMixin {
	//? 1.21.1 {

	/*@Expression(value = "? == null")
	@WrapOperation(method = "appendHoverText", at = @At("MIXINEXTRAS:EXPRESSION"))
	private boolean removeRedPotionTooltip(Object left, Object right, Operation<Boolean> original, @Local PotionContents contents) {
		return original.call(left, right) && contents.potion().map(p -> !p.is(Stellarity.id("red"))).orElse(true);
	}

	*///? }
}
