package prismatic.shards.stellarity.client.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSources;
import prismatic.shards.stellarity.Stellarity;

import java.util.List;

@Environment(EnvType.CLIENT)
public class StellarityModels {


	public static void initModelPredicates() {


	}

	public static void initBlockColors() {


		BlockColorRegistry.register(List.of(BlockTintSources.grassBlock()));


		Stellarity.LOGGER.info("Initialized Block Model Colors");
	}

	public static void initItemColors() {


		Stellarity.LOGGER.info("Initialized Item Model Colors");

	}

	public static void init() {
		initModelPredicates();
		initBlockColors();
		initItemColors();
	}
}
