package xyz.kohara.stellarity.mixin.tooltips;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import xyz.kohara.stellarity.registry.item.Duskberry;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

	@Shadow
	public abstract Item getItem();

	//? 1.20.1 {
	@Definition(id = "shouldShowInTooltip", method = "Lnet/minecraft/world/item/ItemStack;shouldShowInTooltip(ILnet/minecraft/world/item/ItemStack$TooltipPart;)Z")
	@Definition(id = "MODIFIERS", field = "Lnet/minecraft/world/item/ItemStack$TooltipPart;MODIFIERS:Lnet/minecraft/world/item/ItemStack$TooltipPart;")
	@Expression("shouldShowInTooltip(?, MODIFIERS)")
	@WrapOperation(method = "getTooltipLines", at = @At("MIXINEXTRAS:EXPRESSION"))
	public boolean disableAttributeModifierTooltips(int i, ItemStack.TooltipPart tooltipPart, Operation<Boolean> original) {
		if (getItem() instanceof Duskberry) return false;
		return original.call(i, tooltipPart);
	}
	//? }
}
