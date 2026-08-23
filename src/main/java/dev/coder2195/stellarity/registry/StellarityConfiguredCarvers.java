package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.tags.StellarityBlockTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.carver.*;

import static dev.coder2195.stellarity.util.ValueUtil.*;
import static dev.coder2195.stellarity.util.WorldgenUtil.*;
import static net.minecraft.world.level.block.Blocks.*;

public interface StellarityConfiguredCarvers {
	ResourceKey<WorldCarver> RAVINE = id("ravine");
	ResourceKey<WorldCarver> CAVE = id("cave");
	ResourceKey<WorldCarver> CRACK = id("crack");

	static void bootstrap(BootstrapContext<WorldCarver> context) {
		context.register(CAVE, new CaveWorldCarver(
			0.15f, height(aboveBottom(8), belowTop(8)), veryBiasBottom(0, 9), trapezoidf(0, 6, 2), false, numf(0.4f, 1.5f), numf(1, 2.2f), numf(1, 2.2f), numf(1), numf(-1, -0.4f)
		));

		context.register(CRACK, new CanyonWorldCarver(
			0.066f, height(aboveBottom(16), belowTop(16)), numf(-0.125f, 0.125f),
			new CanyonWorldCarver.Shape(numf(0.5f, 1), numf(0, 1), 6, numf(0.25f, 1), 0, 0, numf(6, 8)
		)));

		context.register(RAVINE, new CanyonWorldCarver(
			0.02f, height(aboveBottom(16), belowTop(16)), numf(-0.125f, 0.125f),
			new CanyonWorldCarver.Shape(numf(0.75f, 1), trapezoidf(0, 4, 2), 3, numf(0.5f, 0.75f), 1, 0, numf(4))
		));
	}

	private static ResourceKey<WorldCarver> id(String id) {
		return Stellarity.key(Registries.CARVER, id);
	}
}
