package prismatic.shards.stellarity.client.registry.entity.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.Identifier;
import prismatic.shards.stellarity.Stellarity;

@Environment(EnvType.CLIENT)
public class VoidedSkeletonRenderer extends SkeletonRenderer {

	public VoidedSkeletonRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	public static final Identifier TEXTURE = Stellarity.id("textures/entity/voided_skeleton/voided_skeleton.png");

	@Override
	public Identifier getTextureLocation(SkeletonRenderState state) {
		return TEXTURE;
	}
}
