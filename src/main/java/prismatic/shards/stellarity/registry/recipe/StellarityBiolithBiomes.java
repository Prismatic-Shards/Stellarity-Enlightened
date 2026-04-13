package prismatic.shards.stellarity.registry.recipe;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import net.minecraft.world.level.biome.Climate;

import static net.minecraft.world.level.biome.Biomes.*;
import static net.minecraft.world.level.biome.Climate.Parameter.point;
import static net.minecraft.world.level.biome.Climate.Parameter.span;
import static prismatic.shards.stellarity.key.StellarityBiomes.*;

public interface StellarityBiolithBiomes {
	static void init() {
		BiomePlacement.addEnd(HALLOWED_TUNDRA, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, 1f),
			span(-1.2f, -0.725f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(THE_HALLOW, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-1f, 1f),
			span(-1.2f, -0.725f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(PRISMATIC_DUNES, new Climate.ParameterPoint(
			span(0.48f, 1f),
			span(-1f, 1f),
			span(-1.2f, -0.725f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(FROZEN_SPIKES, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		));
		BiomePlacement.addEnd(END_BARRENS, new Climate.ParameterPoint(
			span(-0.405f, -0.15f),
			span(-1f, 0.265f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(0.265f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		));
		BiomePlacement.addEnd(END_BARRENS, new Climate.ParameterPoint(
			span(-0.15f, 0.2f),
			span(-1f, -0.47000000000000003f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(ENDER_WASTES, new Climate.ParameterPoint(
			span(-0.15f, 0.2f),
			span(-0.47000000000000003f, 0.265f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(END_BARRENS, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-1f, -0.47000000000000003f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		));
		BiomePlacement.addEnd(ENDER_WASTES, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-0.47000000000000003f, -0.19f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(ENDER_WASTES, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-0.19f, 0.265f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		));
		BiomePlacement.addEnd(CRYSTAL_CRAGS, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(0.265f, 0.685f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(0.2f, 1f),
			span(0.685f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(FIERY_HILLS, new Climate.ParameterPoint(
			span(0.48f, 1f),
			span(-1f, 0.265f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(0.48f, 1f),
			span(0.265f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(FROZEN_SHRUBLAND, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, 0.045f),
			span(-0.15f, 1f),
			span(-1f, 0.545f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		));
		BiomePlacement.addEnd(FROZEN_SHRUBLAND, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(0.045f, 0.265f),
			span(-0.15f, 1f),
			span(-1f, -0.065f),
			span(0f, 0f),
			span(-1f, -0.26666668f),
			0
		));
		BiomePlacement.addEnd(FROSTED_VALLEY, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(-1f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(END_HIGHLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(-1f, 0.265f),
			span(-0.15f, 1f),
			span(-1f, -0.065f),
			span(0f, 0f),
			span(-1f, -0.26666668f),
			0
		));
		BiomePlacement.addEnd(AMETHYST_FOREST, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(-1f, -0.065f),
			span(0f, 0f),
			span(-1f, -0.26666668f),
			0
		));
		BiomePlacement.addEnd(END_HIGHLANDS, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-1f, 0.045f),
			span(-0.15f, 1f),
			span(-1f, -0.065f),
			span(0f, 0f),
			span(-1f, -0.26666668f),
			0
		));
		BiomePlacement.addEnd(AMETHYST_FOREST, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-1f, -0.065f),
			span(0f, 0f),
			span(-1f, -0.26666668f),
			0
		));
		BiomePlacement.addEnd(ENDLESS_DUNES, new Climate.ParameterPoint(
			span(0.48f, 1f),
			span(-1f, 1f),
			span(-0.15f, 1f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(FROSTED_VALLEY, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(END_WILDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-1f, -0.19f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(END_WILDS, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(-0.19f, 0.045f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		));
		BiomePlacement.addEnd(END_MIDLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.045f, 0.265f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(-0.405f, -0.15f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		));
		BiomePlacement.addEnd(END_MIDLANDS, new Climate.ParameterPoint(
			span(-0.15f, 0.48f),
			span(0.265f, 0.685f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(-0.15f, 0.2f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(END_SHRUBLAND, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-0.19f, 0.045f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(END_MIDLANDS, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(FROZEN_MARSH, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, 1f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(ASHFALL_DELTAS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-1f, -0.19f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(WARPED_MARSH, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-0.19f, 0.265f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(WARPED_MARSH, new Climate.ParameterPoint(
			span(-0.405f, -0.15f),
			span(0.265f, 0.685f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		));
		BiomePlacement.addEnd(PRISMARINE_FOREST, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(PRISMARINE_FOREST, new Climate.ParameterPoint(
			span(-0.15f, 0.48f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(FROZEN_SHRUBLAND, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(0.045f, 0.265f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.26666668f, -0.05f),
			0
		));
		BiomePlacement.addEnd(END_HIGHLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-1f, 0.045f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.26666668f, 1f),
			0
		));
		BiomePlacement.addEnd(END_HIGHLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(0.045f, 0.265f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.26666668f, -0.05f),
			0
		));
		BiomePlacement.addEnd(AMETHYST_FOREST, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.26666668f, 1f),
			0
		));
		BiomePlacement.addEnd(AMETHYST_FOREST, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.26666668f, 1f),
			0
		));
		BiomePlacement.addEnd(FROSTED_VALLEY, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 1f),
			0
		));
		BiomePlacement.addEnd(END_WILDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-1f, -0.19f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 0.05f),
			0
		));
		BiomePlacement.addEnd(END_WILDS, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(-0.19f, 0.045f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, -0.05f),
			0
		));
		BiomePlacement.addEnd(END_MIDLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.045f, 0.265f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 0.05f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(-0.405f, -0.15f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, -0.05f),
			0
		));
		BiomePlacement.addEnd(END_MIDLANDS, new Climate.ParameterPoint(
			span(-0.15f, 0.48f),
			span(0.265f, 0.685f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 0.05f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(-0.15f, 0.2f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 0.05f),
			0
		));
		BiomePlacement.addEnd(END_SHRUBLAND, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-0.19f, 0.045f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 0.05f),
			0
		));
		BiomePlacement.addEnd(END_MIDLANDS, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 0.05f),
			0
		));
		BiomePlacement.addEnd(FLESH_TUNDRA, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, -0.19f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(FROZEN_SPIKES, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-0.19f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(END_BARRENS, new Climate.ParameterPoint(
			span(-0.405f, -0.15f),
			span(0.265f, 0.685f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(-0.405f, -0.15f),
			span(0.685f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(CRYSTAL_CRAGS, new Climate.ParameterPoint(
			span(-0.15f, 0.2f),
			span(0.265f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(ENDER_WASTES, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-1f, -0.19f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(CRYSTAL_CRAGS, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-0.19f, 0.685f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(FROZEN_SHRUBLAND, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, 0.045f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(FROSTED_VALLEY, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-1f, 0.545f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(AMETHYST_FOREST, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(FLESH_TUNDRA, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, -0.19f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.05f, 0.05f),
			0
		));
		BiomePlacement.addEnd(FROSTED_VALLEY, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-0.19f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.05f, 0.05f),
			0
		));
		BiomePlacement.addEnd(END_SHRUBLAND, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-0.19f, 0.045f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.05f, 0.05f),
			0
		));
		BiomePlacement.addEnd(END_MIDLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.265f, 0.685f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.05f, 0.05f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.05f, 0.05f),
			0
		));
		BiomePlacement.addEnd(PRISMARINE_FOREST, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(FROZEN_SHRUBLAND, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, 0.045f),
			span(-0.15f, 1f),
			span(-0.375f, -0.065f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(END_HIGHLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-1f, 0.045f),
			span(-0.15f, 1f),
			span(-0.375f, -0.065f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(AMETHYST_FOREST, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, -0.065f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(FLESH_TUNDRA, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, -0.19f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(FROSTED_VALLEY, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-0.19f, 1f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(END_SHRUBLAND, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-0.19f, 0.045f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(END_MIDLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.265f, 0.685f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(THE_END, new Climate.ParameterPoint(
			point(0),
			point(0),
			point(-2),
			point(-2),
			span(-2, 2),
			point(0),
			0
		));
	}
}
