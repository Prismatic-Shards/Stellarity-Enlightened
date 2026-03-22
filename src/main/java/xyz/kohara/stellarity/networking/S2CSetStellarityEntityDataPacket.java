package xyz.kohara.stellarity.networking;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import xyz.kohara.stellarity.Stellarity;

import java.util.ArrayList;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.codec.StreamCodec;


import java.util.List;

public record S2CSetStellarityEntityDataPacket(int id, List<SynchedEntityData.DataValue<?>> list)
	implements CustomPacketPayload {
	
	public static final Identifier ID = Stellarity.id("set_entity_data");

	public static final Type<S2CSetStellarityEntityDataPacket> TYPE = new Type<>(ID);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static final StreamCodec<RegistryFriendlyByteBuf, S2CSetStellarityEntityDataPacket> STREAM_CODEC = new StreamCodec<>() {
		@Override
		public void encode(RegistryFriendlyByteBuf buf, S2CSetStellarityEntityDataPacket packet) {
			buf.writeVarInt(packet.id);
			for (SynchedEntityData.DataValue<?> dataValue : packet.list) {
				dataValue.write(buf);
			}

			buf.writeByte(255);
		}

		@Override
		public S2CSetStellarityEntityDataPacket decode(RegistryFriendlyByteBuf buf) {
			int id = buf.readVarInt();
			List<SynchedEntityData.DataValue<?>> list = new ArrayList<>();

			int i;
			while ((i = buf.readUnsignedByte()) != 255) {
				list.add(SynchedEntityData.DataValue.read(buf, i));
			}

			return new S2CSetStellarityEntityDataPacket(id, list);
		}
	};
}
