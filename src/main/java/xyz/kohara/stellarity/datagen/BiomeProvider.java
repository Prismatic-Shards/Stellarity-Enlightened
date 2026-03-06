package xyz.kohara.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.biome.BiomeData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import xyz.kohara.stellarity.Stellarity;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class BiomeProvider extends FabricDynamicRegistryProvider {

    public BiomeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static Holder<Biome> THE_END;

    @Override
    protected void configure(HolderLookup.Provider provider, Entries entries) {
        THE_END = entries.add(Stellarity.mcKey(Registries.BIOME, "the_end"), new Biome.BiomeBuilder()
            .temperature(0.8f)
            .downfall(0.4f)
            .hasPrecipitation(false).specialEffects(new BiomeSpecialEffects.Builder()
                .skyColor(0x000000)
                .fogColor(0x000000)
                .waterColor(0x62529e)
                .waterFogColor(0x41307e)
                .grassColorOverride(0xdedede)
                .foliageColorOverride(0xc2c2c2)
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(
                EntityType.ENDERMAN, 10, 4, 4
            )).addMobCharge(EntityType.ENDERMAN, 0.75, 1).build())
            .generationSettings(new BiomeGenerationSettings.Builder(
                provider.lookupOrThrow(Registries.PLACED_FEATURE),
                provider.lookupOrThrow(Registries.CONFIGURED_CARVER)
            )
                .addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES,
                    PlacedFeatureProvider.MAIN_ISLAND_RING
                ).build())
            .build());
    }

    @Override
    public String getName() {
        return "stellarity-biome-provider";
    }
}
