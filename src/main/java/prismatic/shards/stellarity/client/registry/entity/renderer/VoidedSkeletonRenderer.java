package prismatic.shards.stellarity.client.registry.entity.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.registry.StellarityDataAttachments;
import prismatic.shards.stellarity.registry.entity.variant.VoidedSkeletonVariant;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class VoidedSkeletonRenderer extends AbstractSkeletonRenderer<Skeleton, VoidedSkeletonRenderer.VoidedSkeletonRenderState> {
  public VoidedSkeletonRenderer(final EntityRendererProvider.Context context) {
    super(context, ModelLayers.SKELETON, ModelLayers.SKELETON_ARMOR);
  }

  @Environment(EnvType.CLIENT)
  public static class VoidedSkeletonRenderState extends SkeletonRenderState {
    public Identifier texture;
  }


  @Override
  public @NonNull VoidedSkeletonRenderState createRenderState() {
    return new VoidedSkeletonRenderState();
  }

  @Override
  public void extractRenderState(@NonNull Skeleton entity, @NonNull VoidedSkeletonRenderState state, float partialTicks) {
    super.extractRenderState(entity, state, partialTicks);
    state.texture = Objects.requireNonNullElseGet(entity.getAttached(StellarityDataAttachments.VOIDED_SKELETON_VARIANT), () -> entity.registryAccess().getOrThrow(VoidedSkeletonVariant.DEFAULT_VARIANT)).value().assetInfo().texturePath();
  }

  @Override
  public @NonNull Identifier getTextureLocation(VoidedSkeletonRenderState state) {
    return state.texture;
  }
}
