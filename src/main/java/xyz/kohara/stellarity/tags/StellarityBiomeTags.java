package xyz.kohara.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import xyz.kohara.stellarity.Stellarity;

public class StellarityBiomeTags {
    public static final TagKey<Biome> SNOWY = bind("snowy");

    private static TagKey<Biome> bind(String id) {
        return TagKey.create(Registries.BIOME, Stellarity.id(id));
    }
}
