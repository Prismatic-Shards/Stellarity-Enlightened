package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.world.level.storage.loot.functions.SetComponentsFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;
import java.util.Optional;

@Mixin(SetComponentsFunction.class)
public interface SetComponentsFunctionAccessor {
	@Invoker("<init>")
	static SetComponentsFunction create(final Optional<Holder<LootItemCondition>> condition, final DataComponentPatch components) {
		throw new AssertionError("Not transformed!");
	}
}
