package xyz.kohara.stellarity.registry;


import xyz.kohara.stellarity.Stellarity;
//? > 1.21 {

import dev.architectury.networking.NetworkManager;
import xyz.kohara.stellarity.networking.S2CSetStellarityEntityDataPacket;

//? }

public class StellarityNetworking {
    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Common Networking");

        //? > 1.21 {
        NetworkManager.registerS2CPayloadType(S2CSetStellarityEntityDataPacket.TYPE, S2CSetStellarityEntityDataPacket.STREAM_CODEC);
        //? }
    }
}
