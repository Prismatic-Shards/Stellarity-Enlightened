package prismatic.shards.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.VillagerTrade;
import prismatic.shards.stellarity.tags.StellarityVillagerTradeTags;

import java.util.concurrent.CompletableFuture;

import static prismatic.shards.stellarity.datagen.VillagerTradeProvider.*;

public class VillageTradeTagProvider extends FabricTagsProvider<VillagerTrade> {
	public VillageTradeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, Registries.VILLAGER_TRADE, registryLookupFuture);
	}

	@SafeVarargs
	private void register(TagKey<VillagerTrade> tag, ResourceKey<VillagerTrade>... trades) {
		var builder = getOrCreateRawBuilder(tag);
		for (var trade : trades) {
			builder.addElement(trade.identifier());
		}
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		register(StellarityVillagerTradeTags.ARMORER_LEVEL_1, ARMORER_1_COAL_ENDERITE_SHARD, ARMORER_1_CHARCOAL_ENDERITE_SHARD, ARMORER_1_BLAZE_ROD_ENDERITE_SHARD);
		register(StellarityVillagerTradeTags.ARMORER_LEVEL_1_2, ARMORER_1_ENDERITE_SHARD_SPECIAL_IRON_CHESTPLATE, ARMORER_1_ENDERITE_SHARD_SPECIAL_IRON_HELMET);
	}
}
