package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.trading.TradeSet;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityVillagerTradeSets {
	ResourceKey<TradeSet> ARMORER_LEVEL_1 = id("armorer/level_1");
	ResourceKey<TradeSet> ARMORER_LEVEL_1_2 = id("armorer/level_1_2");

	static ResourceKey<TradeSet> id(String name) {
		return Stellarity.key(Registries.TRADE_SET, name);
	}
}
