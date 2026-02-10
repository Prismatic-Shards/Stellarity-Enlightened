package xyz.kohara.stellarity.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import xyz.kohara.stellarity.registry.StellarityDamageTypes;

import java.util.concurrent.CompletableFuture;

public class DamageTagProvider extends FabricTagProvider<DamageType> {
    public DamageTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.DAMAGE_TYPE, registriesFuture);
    }


    @Override
    protected void addTags(HolderLookup.Provider provider) {

        // add optional because fabric loves bugging
        /*? <1.21.11 { */
        getOrCreateTagBuilder/*? } else { */
            /*builder*//*? } */(DamageTypeTags.BYPASSES_COOLDOWN).addOptional(
            StellarityDamageTypes.NO_KNOCKBACK_IGNORES_IFRAMES
        );
    }
}
