package xyz.kohara.stellarity.mixin.extend_classes;


import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.datafixers.kinds.App;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import xyz.kohara.stellarity.interface_injection.ExtEndDragonFightData;
import xyz.kohara.stellarity.utils.CodecExtensionHelper;
import xyz.kohara.stellarity.utils.CustomCodec;

import java.util.Optional;
import java.util.function.Function;

@Mixin(EndDragonFight.Data.class)
public class EndDragonFightDataMixin implements ExtEndDragonFightData {
	@Unique
	private boolean portalChestGenerated;

	@Override
	public boolean stellarity$portalChestGenerated() {
		return portalChestGenerated;
	}

	@Override
	public void stellarity$setPortalChestGenerated(boolean generated) {
		this.portalChestGenerated = generated;
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
				Codec.BOOL.fieldOf("stellarity:portal_chest_generated").orElse(false).forGetter(ExtEndDragonFightData::stellarity$portalChestGenerated)
			).apply(instance, ExtEndDragonFightData::apply),
			ExtEndDragonFightData::applyDefaults
		);
	}


	@ModifyExpressionValue(method = "<clinit>", at = @At(value = "NEW", target = "(ZZZZLjava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;)Lnet/minecraft/world/level/dimension/end/EndDragonFight$Data;"))
	private static EndDragonFight.Data changeDefault(EndDragonFight.Data original) {
		return ExtEndDragonFightData.applyDefaults(original);
	}

}
