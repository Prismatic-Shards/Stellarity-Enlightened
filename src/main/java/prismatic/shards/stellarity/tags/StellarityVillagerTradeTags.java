package prismatic.shards.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.VillagerTrade;
import prismatic.shards.stellarity.Stellarity;

public class StellarityVillagerTradeTags {
	public static final TagKey<VillagerTrade> ARMORER_LEVEL_1 = bind("armorer/level_1");
	public static final TagKey<VillagerTrade> ARMORER_LEVEL_1_2 = bind("armorer/level_1_2");
	public static final TagKey<VillagerTrade> ARMORER_LEVEL_2 = bind("armorer/level_2");
	public static final TagKey<VillagerTrade> ARMORER_LEVEL_3 = bind("armorer/level_3");
	public static final TagKey<VillagerTrade> ARMORER_LEVEL_4 = bind("armorer/level_4");
	public static final TagKey<VillagerTrade> ARMORER_LEVEL_5 = bind("armorer/level_5");

	private static TagKey<VillagerTrade> bind(String id) {
		return TagKey.create(Registries.VILLAGER_TRADE, Stellarity.id(id));
	}
}
