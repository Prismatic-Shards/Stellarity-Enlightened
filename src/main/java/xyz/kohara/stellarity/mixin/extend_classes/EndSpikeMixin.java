package xyz.kohara.stellarity.mixin.extend_classes;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.SpikeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import xyz.kohara.stellarity.interface_injection.ExtEndSpike;
import xyz.kohara.stellarity.utils.CodecExtensionHelper;

@Mixin(SpikeFeature.EndSpike.class)
public class EndSpikeMixin implements ExtEndSpike {
	@Unique
	private boolean altar;

	@Unique
	private boolean cryingObsidianTops;

	@Override
	public boolean stellarity$hasAltar() {
		return altar;
	}

	@Override
	public void stellarity$setAltar(boolean altar) {
		this.altar = altar;
	}

	@Override
	public boolean stellarity$hasCryingObsidianTops() {
		return cryingObsidianTops;
	}

	@Override
	public void stellarity$setCryingObsidianTops(boolean cryingObsidianTops) {
		this.cryingObsidianTops = cryingObsidianTops;
	}

	@ModifyExpressionValue(
		method = "<clinit>",
		at = @At(
			value = "INVOKE",
			target = "Lcom/mojang/serialization/codecs/RecordCodecBuilder;create(Ljava/util/function/Function;)Lcom/mojang/serialization/Codec;"
		)
	)
	private static Codec<SpikeFeature.EndSpike> more(Codec<SpikeFeature.EndSpike> original) {
		return CodecExtensionHelper.buildExtensionCodec(original, (instance, wrapper) -> instance.group(wrapper,
				Codec.BOOL.fieldOf("stellarity:altar").orElse(false).forGetter(ExtEndSpike::stellarity$hasAltar),
				Codec.BOOL.fieldOf("stellarity:crying_obsidian_tops").orElse(false).forGetter(ExtEndSpike::stellarity$hasCryingObsidianTops)
			).apply(instance, ExtEndSpike::apply),
			ExtEndSpike::applyDefaults
		);
	}
}
