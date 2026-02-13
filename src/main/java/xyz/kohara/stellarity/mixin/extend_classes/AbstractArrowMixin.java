package xyz.kohara.stellarity.mixin.extend_classes;

import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.interface_injection.ExtArrow;

//? < 1.21.9 {

import net.minecraft.nbt.CompoundTag;

//? } else {
/*import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
*///? }

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin {


    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void saveData(
        //? < 1.21.9 {
        CompoundTag tag, CallbackInfo ci
        //? } else {
        /*ValueOutput tag, CallbackInfo ci
         *///? }
    ) {
        if (this instanceof ExtArrow arrow) {
            tag.putInt("stellarity:levitation_shot", arrow.stellarity$levitationShot());
            tag.putBoolean("stellarity:void_shot", arrow.stellarity$voidShot());
        }
    }

    //? > 1.21.9
    //@SuppressWarnings("OptionalGetWithoutIsPresent")
    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void readData(
        //? < 1.21.9 {
        CompoundTag tag, CallbackInfo ci
        //? } else {
        /*ValueInput tag, CallbackInfo ci
         *///? }
    ) {
        if (this instanceof ExtArrow arrow) {
            if (tag.contains("stellarity:levitation_shot")) try {
                arrow.stellarity$setLevitationShot(tag.getInt("stellarity:levitation_shot")
                    //? > 1.21.9 {
                    /*.get()
                     *///? }
                );
            } catch (Exception e) {
                Stellarity.LOGGER.info("Detected invalid levitation shot, ignoring");
            }
            if (tag.contains("stellarity:void_shot")) try {
                arrow.stellarity$setVoidShot(tag.getBoolean("stellarity:void_shot")
                    //? > 1.21.9 {
                    /*.get()
                     *///? }
                );
            } catch (Exception e) {
                Stellarity.LOGGER.info("Detected invalid voidshot, ignoring");
            }
        }
    }

}
