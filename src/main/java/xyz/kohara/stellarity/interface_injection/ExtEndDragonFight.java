package xyz.kohara.stellarity.interface_injection;

import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public interface ExtEndDragonFight extends ExtEndDragonFightData {
	default int getSummonCount(Player player) {
		return getSummonCount(player.getUUID());
	}

	default int getSummonCount(UUID uuid) {
		return stellarity$dragonSummonTracker().getOrDefault(uuid, 0);
	}

	default void addSummon(Player player) {
		addSummon(player.getUUID());
	}

	default void addSummon(UUID uuid) {
		stellarity$dragonSummonTracker().put(uuid, getSummonCount(uuid) + 1);
	}
}
