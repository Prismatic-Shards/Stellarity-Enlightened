package dev.coder2195.stellarity.client.renderer.entity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.entity.SpectralBolt;

public class SpectralBoltRenderer extends ArrowRenderer<SpectralBolt, ArrowRenderState> {
	public static final Identifier TEXTURE = Stellarity.id("textures/entity/projectiles/spectral_bolt.png");

	public SpectralBoltRenderer(final EntityRendererProvider.Context context) {
		super(context);
	}

	protected Identifier getTextureLocation(final ArrowRenderState state) {
		return TEXTURE;
	}

	public ArrowRenderState createRenderState() {
		return new ArrowRenderState();
	}
}
