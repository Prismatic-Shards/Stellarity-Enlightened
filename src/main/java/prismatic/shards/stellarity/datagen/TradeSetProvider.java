package prismatic.shards.stellarity.datagen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.item.trading.VillagerTrade;
import prismatic.shards.stellarity.key.StellarityVillagerTradeSets;
import prismatic.shards.stellarity.tags.StellarityVillagerTradeTags;
import prismatic.shards.stellarity.utils.LootTableUtils;

import java.util.Optional;

public class TradeSetProvider {


	public static void bootstrap(BootstrapContext<TradeSet> context) {

		HolderGetter<VillagerTrade> tradeTags = context.lookup(Registries.VILLAGER_TRADE);
		context.register(StellarityVillagerTradeSets.ARMORER_LEVEL_1, new TradeSet(
			tradeTags.getOrThrow(StellarityVillagerTradeTags.ARMORER_LEVEL_1),
			LootTableUtils.num(1),
			false,
			Optional.empty()
		));

		context.register(StellarityVillagerTradeSets.ARMORER_LEVEL_1_2, new TradeSet(
			tradeTags.getOrThrow(StellarityVillagerTradeTags.ARMORER_LEVEL_1_2),
			LootTableUtils.num(1),
			false,
			Optional.empty()
		));
	}
}
