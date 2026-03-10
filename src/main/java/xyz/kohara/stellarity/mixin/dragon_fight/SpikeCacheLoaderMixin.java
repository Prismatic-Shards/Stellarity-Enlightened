package xyz.kohara.stellarity.mixin.dragon_fight;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.level.levelgen.feature.SpikeFeature;
import org.spongepowered.asm.mixin.Mixin;
import xyz.kohara.stellarity.utils.Constants;

import java.util.List;

@Mixin(SpikeFeature.SpikeCacheLoader.class)
public class SpikeCacheLoaderMixin {
	@WrapMethod(method = "load(Ljava/lang/Long;)Ljava/util/List;")
	private List<SpikeFeature.EndSpike> stellaritySpikes(Long long_, Operation<List<SpikeFeature.EndSpike>> original) {
		return Constants.OBSIDIAN_SPIKES;
	}
}
