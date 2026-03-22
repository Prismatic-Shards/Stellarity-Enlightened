package xyz.kohara.stellarity.registry;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import xyz.kohara.stellarity.networking.S2CSetStellarityEntityDataPacket;

import xyz.kohara.stellarity.Stellarity;

public class StellarityNetworking {
	//~ if > 1.21.1 'playS2C' -> 'clientboundPlay' {
	public static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Common Networking");
		PayloadTypeRegistry.clientboundPlay().register(S2CSetStellarityEntityDataPacket.TYPE, S2CSetStellarityEntityDataPacket.STREAM_CODEC);
	}
	//~ }
}
