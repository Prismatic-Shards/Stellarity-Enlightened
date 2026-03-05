//? 1.20.1 {
package xyz.kohara.stellarity.mixin.potions;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.kohara.stellarity.registry.StellarityPotions;

import java.util.Collection;
import java.util.List;

@Mixin(PotionUtils.class)
public abstract class PotionUtilsMixin {

    @Shadow
    public static @Nullable Potion getPotion(ItemStack itemStack) {
        return null;
    }

    @Inject(method = "getColor(Lnet/minecraft/world/item/alchemy/Potion;)I", at = @At("HEAD"), cancellable = true)
    private static void getColorPotion(Potion potion, CallbackInfoReturnable<Integer> cir) {
        Integer color = StellarityPotions.COLORS.get(potion);
        if (color == null) return;

        cir.setReturnValue(color);
        cir.cancel();
    }

    @WrapOperation(method = "getColor(Lnet/minecraft/world/item/ItemStack;)I", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/alchemy/PotionUtils;getColor(Ljava/util/Collection;)I"))
    private static int getColorItemStack(Collection<MobEffectInstance> collection, Operation<Integer> original, @Local(argsOnly = true) ItemStack itemStack) {
        Potion potion = PotionUtils.getPotion(itemStack);
        Integer color = StellarityPotions.COLORS.get(potion);
        if (color != null) {
            return color;
        }

        return original.call(collection);
    }

    @WrapMethod(method = "addPotionTooltip(Lnet/minecraft/world/item/ItemStack;Ljava/util/List;F)V")
    private static void removeRedPotionTooltip(ItemStack itemStack, List<Component> list, float f, Operation<Void> original) {
        var potion = getPotion(itemStack);
        if (potion != null && potion.equals(StellarityPotions.RED)) return;
        original.call(itemStack, list, f);
    }
}
//? }