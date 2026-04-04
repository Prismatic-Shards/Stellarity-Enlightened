package prismatic.shards.stellarity.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import prismatic.shards.stellarity.Stellarity;

import java.util.List;
import java.util.Optional;

import static net.minecraft.world.item.Items.*;
import static prismatic.shards.stellarity.registry.StellarityItems.ENDERITE_SHARD;

public class VillagerTradeProvider {
	public static final ResourceKey<VillagerTrade> ARMORER_1_COAL_ENDERITE_SHARD = id("armorer/1/coal_enderite_shard");
	public static final ResourceKey<VillagerTrade> ARMORER_1_CHARCOAL_ENDERITE_SHARD = id("armorer/1/charcoal_enderite_shard");
	public static final ResourceKey<VillagerTrade> ARMORER_1_BLAZE_ROD_ENDERITE_SHARD = id("armorer/1/blaze_rod_enderite_shard");
	public static final ResourceKey<VillagerTrade> ARMORER_1_ENDERITE_SHARD_SPECIAL_IRON_CHESTPLATE = id("armorer/1/enderite_shard_special_iron_chestplate");
	public static final ResourceKey<VillagerTrade> ARMORER_1_ENDERITE_SHARD_SPECIAL_IRON_HELMET = id("armorer/1/enderite_shard_special_iron_helmet");


	public static ResourceKey<VillagerTrade> id(String name) {
		return Stellarity.key(Registries.VILLAGER_TRADE, name);
	}

	public static VillagerTrade simpleToShard(Item item, int count, int shards, int xp, int maxUses, float repDiscount) {
		return new VillagerTrade(
			new TradeCost(item, count),
			new ItemStackTemplate(ENDERITE_SHARD, shards),
			maxUses,
			xp,
			repDiscount,
			Optional.empty(),
			List.of()

		);
	}

	public static VillagerTrade shardToModifierItem(int shards, Item item, List<LootItemFunction> modifiers, int count, int xp, int maxUses, float repDiscount) {
		return new VillagerTrade(
			new TradeCost(ENDERITE_SHARD, shards),
			new ItemStackTemplate(item, count),
			maxUses,
			xp,
			repDiscount,
			Optional.empty(),
			modifiers
		);
	}


	public static void bootstrap(BootstrapContext<VillagerTrade> context) {
		context.register(ARMORER_1_COAL_ENDERITE_SHARD, simpleToShard(COAL, 19, 1, 2, 12, 0.05f));
		context.register(ARMORER_1_CHARCOAL_ENDERITE_SHARD, simpleToShard(CHARCOAL, 24, 1, 2, 10, 0.05f));
		context.register(ARMORER_1_BLAZE_ROD_ENDERITE_SHARD, simpleToShard(BLAZE_ROD, 3, 1, 1, 10, 0.05f));
		context.register(ARMORER_1_ENDERITE_SHARD_SPECIAL_IRON_CHESTPLATE, shardToModifierItem(9, IRON_CHESTPLATE, List.of(), 1, 2, 6, 0.2f));
		context.register(ARMORER_1_ENDERITE_SHARD_SPECIAL_IRON_HELMET, shardToModifierItem(7, IRON_HELMET, List.of(), 1, 2, 6, 0.2f));

	}
}
