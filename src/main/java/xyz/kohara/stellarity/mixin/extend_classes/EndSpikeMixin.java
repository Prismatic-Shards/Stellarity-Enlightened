package xyz.kohara.stellarity.mixin.extend_classes;

import net.minecraft.world.level.levelgen.feature.SpikeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import xyz.kohara.stellarity.interface_injection.ExtEndSpike;

@Mixin(SpikeFeature.EndSpike.class)
public class EndSpikeMixin implements ExtEndSpike {
    @Unique
    private boolean hasAltar;

    @Override
    public boolean stellarity$hasAltar() {
        return hasAltar;
    }

    @Override
    public void stellarity$setAltar(boolean hasAltar) {
        this.hasAltar = hasAltar;
    }
}
