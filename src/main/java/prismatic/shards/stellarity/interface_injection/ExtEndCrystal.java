package prismatic.shards.stellarity.interface_injection;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import prismatic.shards.stellarity.registry.StellarityDataAttachments;
import prismatic.shards.stellarity.util.CustomCodec;

import java.util.function.IntFunction;

@SuppressWarnings("NonExtendableApiUsage")
public interface ExtEndCrystal extends AttachmentTarget {
	enum Type {
		NORMAL(0),
		RESPAWN(1),
		END_CITY(2);

		private final int id;

		Type(int id) {
			this.id = id;
		}

		public int id() {
			return id;
		}

		public static final IntFunction<Type> BY_ID =
			ByIdMap.continuous(
				Type::id,
				Type.values(),
				ByIdMap.OutOfBoundsStrategy.ZERO
			);

		public static final StreamCodec<ByteBuf, Type> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, Type::id);

		public final static Codec<Type> CODEC = CustomCodec.enumName(Type.class, NORMAL);
	}

	default Type stellarity$getType() {
		return this.getAttachedOrCreate(StellarityDataAttachments.END_CRYSTAL_TYPE, () -> Type.NORMAL);
	}

	default void stellarity$setType(Type type) {
		this.setAttached(StellarityDataAttachments.END_CRYSTAL_TYPE, type);
	}

}
