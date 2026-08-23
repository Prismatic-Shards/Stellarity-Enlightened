package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NoiseGeneratorSettings.class)
public interface NoiseGeneratorSettingsAccessor {
	@Mutable
	@Accessor("materialRule")
	void stellarity$setMaterialRule(Holder<SurfaceRules.RuleSource> sequence);

	@Mutable
	@Accessor("disableMobGeneration")
	void stellarity$setDisableMobGeneration(boolean b);
}
