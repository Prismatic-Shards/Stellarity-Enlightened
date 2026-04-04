package prismatic.shards.stellarity.client.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.client.registry.renderer.entity.PhantomItemFrameRenderer;
import prismatic.shards.stellarity.registry.StellarityEntities;

@Environment(EnvType.CLIENT)
public class StellarityEntityRenderers {

	public static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Entity Renderers");
		EntityRenderers.register(StellarityEntities.PHANTOM_ITEM_FRAME, PhantomItemFrameRenderer::new);
		EntityRenderers.register(StellarityEntities.PRISMATIC_PEARL, ThrownItemRenderer::new);
	}

}
