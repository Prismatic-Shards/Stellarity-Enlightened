package prismatic.shards.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.VillagerTrade;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityVillagerTradeTags {
	TagKey<VillagerTrade> ARMORER_LEVEL_1 = id("armorer/level_1");
	TagKey<VillagerTrade> ARMORER_LEVEL_1_2 = id("armorer/level_1_2");
	TagKey<VillagerTrade> ARMORER_LEVEL_2 = id("armorer/level_2");
	TagKey<VillagerTrade> ARMORER_LEVEL_3 = id("armorer/level_3");
	TagKey<VillagerTrade> ARMORER_LEVEL_4 = id("armorer/level_4");
	TagKey<VillagerTrade> ARMORER_LEVEL_5 = id("armorer/level_5");

	static TagKey<VillagerTrade> id(String id) {
		return TagKey.create(Registries.VILLAGER_TRADE, Stellarity.id(id));
	}
}
