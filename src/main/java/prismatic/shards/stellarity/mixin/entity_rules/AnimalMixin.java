package prismatic.shards.stellarity.mixin.entity_rules;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Animal.class)
public abstract class AnimalMixin extends AgeableMob {
	protected AnimalMixin(EntityType<? extends AgeableMob> type, Level level) {
		super(type, level);
	}

	@Definition(id = "brightEnoughToSpawn", local = @Local(type = boolean.class, name = "brightEnoughToSpawn"))
	@Expression("brightEnoughToSpawn")
	@ModifyExpressionValue(method = "checkAnimalSpawnRules", at = @At("MIXINEXTRAS:EXPRESSION"))
	private static boolean changeEndLightRequirements(boolean original, @Local(argsOnly = true, name = "level") LevelAccessor level, @Local(argsOnly = true, name = "pos") BlockPos pos) {
		// end has no light requirement
		return original || level.getBiome(pos).is(BiomeTags.IS_END);
	}

}
