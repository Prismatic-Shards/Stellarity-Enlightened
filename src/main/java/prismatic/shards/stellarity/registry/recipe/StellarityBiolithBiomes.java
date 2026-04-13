package prismatic.shards.stellarity.registry.recipe;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import net.minecraft.world.level.biome.Climate;
import prismatic.shards.stellarity.key.StellarityBiomes;

public interface StellarityBiolithBiomes {
	static void init() {
		BiomePlacement.addEnd(StellarityBiomes.AMETHYST_FOREST, new Climate.ParameterPoint(
			Climate.Parameter.span(-0.405f, 0.48f),
			Climate.Parameter.span(0.265f, 1f),
			Climate.Parameter.span(-0.15f, 1f),
			Climate.Parameter.span(-1f, -0.065f),
			Climate.Parameter.point(0f),
			Climate.Parameter.span(-1f, 0.26666668f),
			0
		));
	}
}
