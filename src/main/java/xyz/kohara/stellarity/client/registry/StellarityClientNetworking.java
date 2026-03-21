package xyz.kohara.stellarity.client.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.world.entity.Entity;
import xyz.kohara.stellarity.networking.S2CSetStellarityEntityDataPacket;

import net.minecraft.client.multiplayer.ClientLevel;

@Environment(EnvType.CLIENT)
public class StellarityClientNetworking {
	public static void init() {

		ClientPlayNetworking.registerGlobalReceiver(S2CSetStellarityEntityDataPacket.TYPE, (packet, context) -> {
			@SuppressWarnings("resource") ClientLevel world = context.client().level;
			if (world == null) return;

			Entity entity = world.getEntity(packet.id());
			if (entity == null) return;

			entity.stellarity$entityData().assignValues(packet.list());

		});
	}
}
