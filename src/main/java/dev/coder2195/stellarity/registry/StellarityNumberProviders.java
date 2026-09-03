package dev.coder2195.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.float_provider.NbtValue;

public interface StellarityNumberProviders {
	static void init() {
		Registry.register(BuiltInRegistries.CONTEXT_FLOAT_PROVIDER_TYPE, Stellarity.id("nbt_number"), NbtValue.CODEC);
	}

}
