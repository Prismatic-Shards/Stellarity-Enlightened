package prismatic.shards.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.component.DyedItemColor;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityDataComponents {
	DataComponentType<DyedItemColor> DYED_COLOR = register("dyed_color", DataComponentType.<DyedItemColor>builder().persistent(DyedItemColor.CODEC).networkSynchronized(DyedItemColor.STREAM_CODEC));

	static <T> DataComponentType<T> register(String id, DataComponentType.Builder<T> component) {
		return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Stellarity.id(id), component.build());
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Data Components");
	}
}
