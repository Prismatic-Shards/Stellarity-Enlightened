package dev.coder2195.stellarity.client.renderer.entity;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.entity.VoidedSkeleton;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.Identifier;

public class VoidedSkeletonRenderer extends AbstractSkeletonRenderer<VoidedSkeleton, VoidedSkeletonRenderer.VoidedSkeletonRenderState> {
	public VoidedSkeletonRenderer(final EntityRendererProvider.Context context) {
		super(context, ModelLayers.SKELETON, ModelLayers.SKELETON_ARMOR);
	}

	public static class VoidedSkeletonRenderState extends SkeletonRenderState {
		public Identifier texture = Stellarity.id("dummy");
	}


	@Override
	public VoidedSkeletonRenderState createRenderState() {
		return new VoidedSkeletonRenderState();
	}

	@Override
	public void extractRenderState(VoidedSkeleton entity, VoidedSkeletonRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.texture = entity.getVariant().value().assetInfo().texturePath();
	}

	@Override
	public Identifier getTextureLocation(VoidedSkeletonRenderState state) {
		return state.texture;
	}
}
