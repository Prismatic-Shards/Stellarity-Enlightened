package prismatic.shards.stellarity.registry;

import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.NoiseRouter;
import org.jetbrains.annotations.Nullable;
import prismatic.shards.stellarity.Stellarity;

import static prismatic.shards.stellarity.key.StellarityDensityFunctions.*;

public class StellarityRegistryEntries {
	private static @Nullable NoiseRouter endNoiseRouter = null;

	public static void init() {
		DynamicRegistrySetupCallback.EVENT.register(registryView -> {
			registryView.registerEntryAdded(Registries.DENSITY_FUNCTION, (rawId, id, densityFunction) -> {
				if (!id.getNamespace().equals(Stellarity.MOD_ID)) return;
				if (endNoiseRouter == null)
					throw new IllegalStateException("For some stupid reason, the end has not loaded before the density functions. Report this to dev.");
				if (id.equals(CLIMATE_TEMPERATURE.identifier())) {
					endNoiseRouter.temperature = densityFunction;
					return;
				}
				if (id.equals(CLIMATE_HUMIDITY.identifier())) {
					endNoiseRouter.vegetation = densityFunction;
					return;
				}
				if (id.equals(CLIMATE_CONTINENTS.identifier())) {
					endNoiseRouter.continents = densityFunction;
					return;
				}
				if (id.equals(CLIMATE_EROSION.identifier())) {
					endNoiseRouter.erosion = densityFunction;
					return;
				}
				if (id.equals(CLIMATE_DEPTH.identifier())) {
					endNoiseRouter.depth = densityFunction;
					return;
				}
				if (id.equals(CLIMATE_RIDGES.identifier())) {
					endNoiseRouter.ridges = densityFunction;
					return;
				}
				if (id.equals(INITIAL_DENSITY.identifier())) {
					endNoiseRouter.preliminarySurfaceLevel = densityFunction;
					return;
				}
				if (id.equals(FINAL_DENSITY.identifier())) {
					endNoiseRouter.finalDensity = densityFunction;
				}
			});
			registryView.registerEntryAdded(Registries.NOISE_SETTINGS, (rawId, id, noiseSettings) -> {
				if (id.equals(Stellarity.mcId("end"))) {
					var noise = noiseSettings.noiseSettings();
					endNoiseRouter = noiseSettings.noiseRouter();

					noise.height = Math.max(noise.height(), 384);
				}
			});
		});
	}
}
