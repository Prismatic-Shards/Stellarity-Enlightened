package prismatic.shards.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import prismatic.shards.stellarity.Stellarity;

public interface StellaritySounds {
	SoundEvent TAMARIS_EXECUTE = register("item.tamaris.execute");
	SoundEvent TAMARIS_EXECUTE_SPECIAL = register("item.tamaris.execute_special");
	SoundEvent TAMARIS_CHIME = register("item.tamaris.chime");
	SoundEvent TAMARIS_EXECUTE_BG = register("item.tamaris.execute_bg");
	SoundEvent TAMARIS_RAVE = register("item.tamaris.rave");

	SoundEvent CRITICAL_STRIKE = register("enchantment.critical_strike.crit");

	SoundEvent AMBUSH_LEVEL_1 = register("enchantment.ambush.level_1");
	SoundEvent AMBUSH_LEVEL_2 = register("enchantment.ambush.level_2");
	SoundEvent AMBUSH_LEVEL_3 = register("enchantment.ambush.level_3");

	SoundEvent FIRES_OF_HOKKAI = register("music_disc.fires_of_hokkai");
	SoundEvent DEVIANTS_LIGHT_MUSIC_BOX = register("music_disc.deviants_light_music_box");
	SoundEvent PRECIPICE_STEREO = register("music_disc.precipice_stereo");


	SoundEvent PRISMATIC_PEARL_THROW = register("item.prismatic_pearl.throw");

	private static SoundEvent register(String id) {
		var location = Stellarity.id(id);
		return Registry.register(BuiltInRegistries.SOUND_EVENT, location, SoundEvent.createVariableRangeEvent(location));
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Sounds");
	}
}
