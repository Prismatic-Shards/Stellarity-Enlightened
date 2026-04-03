package xyz.kohara.stellarity.registry;

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.item.trading.TradeSets;
import org.jspecify.annotations.Nullable;
import xyz.kohara.stellarity.Stellarity;

public class StellarityVillagerProfessions {
	public static final Holder.Reference<VillagerProfession> ARMORER = register(
		"armorer",
		true,
		PoiTypes.ARMORER,
		null,
		Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, TradeSets.ARMORER_LEVEL_5), Int2ObjectMap.entry(2, TradeSets.ARMORER_LEVEL_4), Int2ObjectMap.entry(3, TradeSets.ARMORER_LEVEL_3), Int2ObjectMap.entry(4, TradeSets.ARMORER_LEVEL_4), Int2ObjectMap.entry(5, TradeSets.ARMORER_LEVEL_5))
	);

	public static Holder.Reference<VillagerProfession> mapVanilla(Holder.Reference<VillagerProfession> original) {
		var id = original.key().identifier();
		if (!id.getNamespace().equals("minecraft")) {
			return original;
		}
		var path = id.getPath();
		if (path.equals("armorer")) return ARMORER;

		return original;
	}


	public static Holder.Reference<VillagerProfession> register(String name, boolean vanilla, final ResourceKey<PoiType> jobSite, final @Nullable SoundEvent workSound, final Int2ObjectMap<ResourceKey<TradeSet>> trades) {
		var key = Stellarity.key(Registries.VILLAGER_PROFESSION, name);

		return Registry.registerForHolder(BuiltInRegistries.VILLAGER_PROFESSION, key, new VillagerProfession(
			Component.translatable("entity." + (vanilla ? "minecraft" : Stellarity.MOD_ID) + ".villager." + name),
			(site) -> site.is(jobSite),
			(site) -> site.is(jobSite),
			ImmutableSet.of(), ImmutableSet.of(),
			workSound,
			trades
		));
	}

	public static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Villager Professions");
	}
}
