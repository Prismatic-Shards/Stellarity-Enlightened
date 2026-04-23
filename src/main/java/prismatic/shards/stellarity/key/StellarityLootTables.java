package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityLootTables {
	ResourceKey<LootTable> DUNGEON = id("dungeon");
	ResourceKey<LootTable> EXIT_PORTAL = id("exit_portal");
	ResourceKey<LootTable> VOID_FISHING_FISH = id("void_fishing/fish");
	ResourceKey<LootTable> VOID_FISHING_JUNK = id("void_fishing/junk");
	ResourceKey<LootTable> VOID_FISHING_TREASURE = id("void_fishing/treasure");
	ResourceKey<LootTable> VOID_FISHING_FISHER_OF_VOIDS = id("void_fishing/fisher_of_voids");
	ResourceKey<LootTable> VOID_FISHING_EVENT = id("void_fishing/event");

	static ResourceKey<LootTable> id(String id) {
		return Stellarity.key(Registries.LOOT_TABLE, id);
	}
}
