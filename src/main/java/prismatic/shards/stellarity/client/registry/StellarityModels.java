package prismatic.shards.stellarity.client.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSources;
import prismatic.shards.stellarity.Stellarity;

import java.util.List;

import static prismatic.shards.stellarity.registry.StellarityBlocks.ENDER_GRASS_BLOCK;

@Environment(EnvType.CLIENT)
public class StellarityModels {


	public static void initModelPredicates() {


	}

	public static void initBlockColors() {
		BlockColorRegistry.register(List.of(BlockTintSources.grassBlock()), ENDER_GRASS_BLOCK);

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
