package prismatic.shards.stellarity.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.trading.TradeSet;
import prismatic.shards.stellarity.Stellarity;

public class StellarityVillagerTradeSets {
	public static final ResourceKey<TradeSet> ARMORER_LEVEL_1 = key("armorer/level_1");
	public static final ResourceKey<TradeSet> ARMORER_LEVEL_1_2 = key("armorer/level_1_2");

	public static ResourceKey<TradeSet> key(String name) {
		return Stellarity.key(Registries.TRADE_SET, name);
	}
}
