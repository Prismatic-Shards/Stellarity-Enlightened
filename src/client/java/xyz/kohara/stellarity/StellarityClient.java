package xyz.kohara.stellarity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.*;

@Environment(EnvType.CLIENT)
public class StellarityClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Stellarity.LOGGER.info("Stellarity Client Initializing");

        StellarityModels.init();
        StellarityClientParticles.init();
        StellarityEntityRenderers.init();
        StellarityTooltips.init();
        StellarityClientNetworking.init();
    }
}
