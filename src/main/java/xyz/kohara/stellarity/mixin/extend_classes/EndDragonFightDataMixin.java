package xyz.kohara.stellarity.mixin.extend_classes;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import xyz.kohara.stellarity.interface_injection.ExtEndDragonFightData;
import xyz.kohara.stellarity.utils.CodecExtensionHelper;
import xyz.kohara.stellarity.utils.CustomCodec;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Mixin(EndDragonFight.Data.class)
public class EndDragonFightDataMixin implements ExtEndDragonFightData {
	@Unique
	private Map<UUID, Integer> dragonSummonTracker = new HashMap<>();

	@Override
	public Map<UUID, Integer> stellarity$dragonSummonTracker() {
		return dragonSummonTracker;
	}

	@Override
	public void stellarity$setDragonSummonTracker(Map<UUID, Integer> map) {
		dragonSummonTracker = map;
	}

	@ModifyExpressionValue(
		method = "<clinit>",
		at = @At(
			value = "INVOKE",
			target = "Lcom/mojang/serialization/codecs/RecordCodecBuilder;create(Ljava/util/function/Function;)Lcom/mojang/serialization/Codec;"
		)
	)
	private static Codec<EndDragonFight.Data> more(Codec<EndDragonFight.Data> original) {
		return CodecExtensionHelper.buildExtensionCodec(original, (instance, wrapper) -> instance.group(wrapper,
				Codec.unboundedMap(CustomCodec.UUID, Codec.INT).fieldOf("stellarity:dragon_summons").orElse(new HashMap<>()).forGetter(ExtEndDragonFightData::stellarity$dragonSummonTracker)
			).apply(instance, ExtEndDragonFightData::apply),
			ExtEndDragonFightData::applyDefaults
		);
	}


	@WrapOperation(method = "<clinit>", at = @At(value = "NEW", target = "(ZZZZLjava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;)Lnet/minecraft/world/level/dimension/end/EndDragonFight$Data;"))
	private static EndDragonFight.Data changeDefault(boolean bl, boolean bl2, boolean bl3, boolean bl4, Optional optional, Optional optional2, Optional optional3, Operation<EndDragonFight.Data> original) {

		return original.call(bl, true, false, false, optional, optional2, optional3);
	}


}
