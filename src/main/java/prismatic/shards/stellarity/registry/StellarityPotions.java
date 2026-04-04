package prismatic.shards.stellarity.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import prismatic.shards.stellarity.Stellarity;

import java.util.HashMap;

public interface StellarityPotions {
	HashMap<Holder<Potion>, Integer> COLORS = new HashMap<>();

	Holder<Potion> BLIND_RAGE = register("blind_rage", "blind_rage",
		new MobEffectInstance(MobEffects.DARKNESS, 15 * 20, 0),
		new MobEffectInstance(MobEffects.STRENGTH, 15 * 20, 2));

	Holder<Potion> LONG_BLIND_RAGE = register("long_blind_rage", "blind_rage", new MobEffectInstance(MobEffects.DARKNESS, 22 * 20, 0),
		new MobEffectInstance(MobEffects.STRENGTH, 22 * 20, 2));

	Holder<Potion> ENDURANCE = register("endurance", "endurance", new MobEffectInstance(MobEffects.RESISTANCE, 2 * 60 * 20
	));

	Holder<Potion> LONG_ENDURANCE = register("long_endurance", "endurance", new MobEffectInstance(
		MobEffects.RESISTANCE, 4 * 60 * 20
	));

	Holder<Potion> STRONG_ENDURANCE = register("strong_endurance", "endurance", new MobEffectInstance(
		MobEffects.RESISTANCE, 60 * 20, 1
	));

	Holder<Potion> ENTANGLEMENT = register("entanglement", "entanglement", new MobEffectInstance(
		MobEffects.SLOWNESS, 11 * 20, 4
	));

	Holder<Potion> LONG_ENTANGLEMENT = register("long_entanglement", "entanglement", new MobEffectInstance(
		MobEffects.SLOWNESS, 14 * 20, 4
	));

	Holder<Potion> STRONG_ENTANGLEMENT = register("strong_entanglement", "entanglement", new MobEffectInstance(
		MobEffects.SLOWNESS, 8 * 20, 5
	));

	Holder<Potion> FROST_CLOUD = register("frost_cloud", "frost_cloud", new MobEffectInstance(
		MobEffects.SLOWNESS, 6, 98
	));

	Holder<Potion> HELLFIRE_TREADER = register("hellfire_treader", "hellfire_treader", new MobEffectInstance(
		MobEffects.SPEED, 4 * 60 * 20, 0
	), new MobEffectInstance(
		MobEffects.FIRE_RESISTANCE
		, 4 * 60 * 20, 0
	));

	Holder<Potion> LONG_HELLFIRE_TREADER = register("long_hellfire_treader", "hellfire_treader", new MobEffectInstance(
		MobEffects.SPEED
		, 8 * 60 * 20, 0
	), new MobEffectInstance(
		MobEffects.FIRE_RESISTANCE
		, 8 * 60 * 20, 0
	));

	Holder<Potion> STRONG_HELLFIRE_TREADER = register("strong_hellfire_treader", "hellfire_treader", new MobEffectInstance(
		MobEffects.SPEED, 2 * 60 * 20, 1
	), new MobEffectInstance(
		MobEffects.FIRE_RESISTANCE
		, 2 * 60 * 20, 1
	));


	Holder<Potion> AMARENE = register("amarene", "amarene",
		new MobEffectInstance(MobEffects.SPEED, 48 * 20, 1),
		new MobEffectInstance(MobEffects.REGENERATION, 48 * 20),
		new MobEffectInstance(MobEffects.WEAKNESS, 48 * 20),
		new MobEffectInstance(MobEffects.SATURATION, 3, 1),
		new MobEffectInstance(MobEffects.LUCK, 4800)
	);

	Holder<Potion> LIFEFORCE = register("lifeforce", "lifeforce",
		new MobEffectInstance(MobEffects.HEALTH_BOOST, 3 * 60 * 20),
		new MobEffectInstance(MobEffects.REGENERATION, 3 * 60 * 20)
	);

	Holder<Potion> LONG_LIFEFORCE = register("long_lifeforce", "lifeforce",
		new MobEffectInstance(MobEffects.HEALTH_BOOST, 6 * 60 * 20),
		new MobEffectInstance(MobEffects.REGENERATION, 6 * 60 * 20)
	);

	Holder<Potion> STRONG_LIFEFORCE = register("strong_lifeforce", "lifeforce",
		new MobEffectInstance(MobEffects.HEALTH_BOOST, 90 * 20, 1),
		new MobEffectInstance(MobEffects.REGENERATION, 90 * 20, 1)
	);

	Holder<Potion> SPELUNKER = register("spelunker", "spelunker",
		new MobEffectInstance(MobEffects.NIGHT_VISION, 3 * 60 * 20),
		new MobEffectInstance(MobEffects.HASTE, 3 * 60 * 20)
	);

	Holder<Potion> LONG_SPELUNKER = register("long_spelunker", "spelunker",
		new MobEffectInstance(MobEffects.NIGHT_VISION, 6 * 60 * 20),
		new MobEffectInstance(MobEffects.HASTE, 6 * 60 * 20)
	);

	Holder<Potion> STRONG_SPELUNKER = register("strong_spelunker", "spelunker",
		new MobEffectInstance(MobEffects.NIGHT_VISION, 90 * 20),
		new MobEffectInstance(MobEffects.HASTE, 90 * 20, 1)
	);

	Holder<Potion> POSEIDONS_NECTAR = register("poseidons_nectar", "poseidons_nectar",
		new MobEffectInstance(MobEffects.CONDUIT_POWER, 390 * 20),
		new MobEffectInstance(MobEffects.ABSORPTION, 390 * 20),
		new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 390 * 20)
	);

	Holder<Potion> REGENERAGA = register("regeneraga", "regeneraga",
		new MobEffectInstance(MobEffects.REGENERATION, 140, 2),
		new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1, 1)
	);

	Holder<Potion> LONG_REGENERAGA = register("long_regeneraga", "regeneraga",
		new MobEffectInstance(MobEffects.REGENERATION, 210, 2),
		new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1, 1)
	);

	Holder<Potion> STRONG_REGENERAGA = register("strong_regeneraga", "regeneraga",
		new MobEffectInstance(MobEffects.REGENERATION, 70, 4),
		new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1, 1)
	);

	Holder<Potion> RED = register("red", "red",
		new MobEffectInstance(MobEffects.DARKNESS, -1, 9),
		new MobEffectInstance(MobEffects.SLOWNESS, -1, 9),
		new MobEffectInstance(MobEffects.MINING_FATIGUE, -1, 9),
		new MobEffectInstance(MobEffects.NAUSEA, -1),
		new MobEffectInstance(MobEffects.BLINDNESS, -1),
		new MobEffectInstance(MobEffects.HUNGER, -1, 9),
		new MobEffectInstance(MobEffects.WEAKNESS, -1, 9),
		new MobEffectInstance(MobEffects.WITHER, -1, 9),
		new MobEffectInstance(MobEffects.POISON, -1, 9),
		new MobEffectInstance(MobEffects.BAD_OMEN, 1200, 9),
		new MobEffectInstance(MobEffects.UNLUCK, -1, 9),
		new MobEffectInstance(MobEffects.GLOWING, -1)
	);

	Holder<Potion> LUCK = register("luck", "luck",
		new MobEffectInstance(MobEffects.LUCK)
	);


	private static Holder<Potion> register(String id, String name, MobEffectInstance... effects) {
		return Registry.registerForHolder(BuiltInRegistries.POTION, Stellarity.id(id), new Potion("stellarity." + name, effects));
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Potions");
		COLORS.put(BLIND_RAGE, 7230976);
		COLORS.put(LONG_BLIND_RAGE, 7230976);
		COLORS.put(ENDURANCE, 9520880);
		COLORS.put(LONG_ENDURANCE, 9520880);
		COLORS.put(STRONG_ENDURANCE, 9520880);
		COLORS.put(ENTANGLEMENT, 8463733);
		COLORS.put(LONG_ENTANGLEMENT, 8463733);
		COLORS.put(STRONG_ENTANGLEMENT, 8463733);
		COLORS.put(FROST_CLOUD, 8297656);
		COLORS.put(HELLFIRE_TREADER, 16153088);
		COLORS.put(LONG_HELLFIRE_TREADER, 16153088);
		COLORS.put(STRONG_HELLFIRE_TREADER, 16153088);
		COLORS.put(AMARENE, 13176087);
		COLORS.put(LIFEFORCE, 16733011);
		COLORS.put(LONG_LIFEFORCE, 16733011);
		COLORS.put(STRONG_LIFEFORCE, 16733011);
		COLORS.put(SPELUNKER, 16767232);
		COLORS.put(LONG_SPELUNKER, 16767232);
		COLORS.put(STRONG_SPELUNKER, 16767232);
		COLORS.put(POSEIDONS_NECTAR, 4695807);
		COLORS.put(RED, 16056354);
		COLORS.put(REGENERAGA, 16121975);
		COLORS.put(LONG_REGENERAGA, 16121975);
		COLORS.put(STRONG_REGENERAGA, 16121975);
		COLORS.put(LUCK, 5882118);
	}
}
