package xyz.kohara.stellarity.utils;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.PrimitiveCodec;

import java.util.UUID;

public class CustomCodec {
	public static PrimitiveCodec<UUID> UUID = new PrimitiveCodec<>() {

		@Override
		public <T> DataResult<UUID> read(DynamicOps<T> ops, T input) {
			return ops.getStringValue(input).map(java.util.UUID::fromString);
		}

		@Override
		public <T> T write(DynamicOps<T> ops, UUID value) {
			return ops.createString(value.toString());
		}
	};
}
