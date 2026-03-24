
package xyz.kohara.stellarity.client.mixin.phantom_item_frame_model;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.world.entity.decoration.ItemFrame;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import xyz.kohara.stellarity.registry.StellarityEntities;

import static xyz.kohara.stellarity.client.registry.renderer.entity.PhantomItemFrameRenderer.FAKE_STATE_DEFINITION;

@Environment(EnvType.CLIENT)
@Mixin(ItemFrameRenderer.class)
public class ItemFrameRendererMixin {
	@Shadow
	@Final
	public static BlockDisplayContext BLOCK_DISPLAY_CONTEXT;

	@WrapOperation(method = "extractRenderState(Lnet/minecraft/world/entity/decoration/ItemFrame;Lnet/minecraft/client/renderer/entity/state/ItemFrameRenderState;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/block/BlockModelResolver;updateForItemFrame(Lnet/minecraft/client/renderer/block/BlockModelRenderState;ZZ)V"))
	private void phantomItemFrameState(BlockModelResolver instance, BlockModelRenderState renderState, boolean isGlowing, boolean map, Operation<Void> original, @Local(name = "entity") ItemFrame entity) {
		if (entity.is(StellarityEntities.PHANTOM_ITEM_FRAME)) {
			instance.update(renderState, FAKE_STATE_DEFINITION.any(), BLOCK_DISPLAY_CONTEXT);
		}


		original.call(instance, renderState, isGlowing, map);
	}
}
