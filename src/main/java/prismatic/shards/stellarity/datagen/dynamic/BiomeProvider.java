package prismatic.shards.stellarity.datagen.dynamic;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.AmbientAdditionsSettings;
import net.minecraft.world.attribute.AmbientMoodSettings;
import net.minecraft.world.attribute.AmbientSounds;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import prismatic.shards.stellarity.registry.StellaritySounds;

import java.util.List;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static net.minecraft.core.Holder.direct;
import static prismatic.shards.stellarity.key.StellarityBiomes.AMETHYST_FOREST;


public interface BiomeProvider {
	static void bootstrap(BootstrapContext<Biome> context) {
		var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
		var worldCarvers = context.lookup(Registries.CONFIGURED_CARVER);

		context.register(AMETHYST_FOREST, new Biome.BiomeBuilder()
			.temperature(0.8f).downfall(0.4f).hasPrecipitation(false)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f473f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(direct(StellaritySounds.AMBIENT_HEAVENLY_GRIM)),
				of(new AmbientMoodSettings(direct(SoundEvents.AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE), 1000, 4, 2)),
				List.of(new AmbientAdditionsSettings(direct(SoundEvents.AMBIENT_UNDERWATER_LOOP_ADDITIONS_ULTRA_RARE), 0.001))
			)).specialEffects(new BiomeSpecialEffects(0xf3d1ff, of(0xd494ff), empty(), of(0xdeadff), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.addSpawn(MobCategory.MONSTER, 33, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 4, 4))
				.addSpawn(MobCategory.CREATURE, 12, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 4, 4))
				.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.PIG, 4, 4))
				.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 4, 4))
				.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.COW, 4, 4))
				.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 4, 8))
				.build()
			).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)

				.build()
			).build()
		);
	}
}
