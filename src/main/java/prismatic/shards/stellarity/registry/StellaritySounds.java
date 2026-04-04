package prismatic.shards.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import prismatic.shards.stellarity.Stellarity;

public class StellaritySounds {
	public static final SoundEvent TAMARIS_EXECUTE = register("item.tamaris.execute");
	public static final SoundEvent TAMARIS_EXECUTE_SPECIAL = register("item.tamaris.execute_special");
	public static final SoundEvent TAMARIS_CHIME = register("item.tamaris.chime");
	public static final SoundEvent TAMARIS_EXECUTE_BG = register("item.tamaris.execute_bg");
	public static final SoundEvent TAMARIS_RAVE = register("item.tamaris.rave");

	public static final SoundEvent CRITICAL_STRIKE = register("enchantment.critical_strike.crit");

	public static final SoundEvent AMBUSH_LEVEL_1 = register("enchantment.ambush.level_1");
	public static final SoundEvent AMBUSH_LEVEL_2 = register("enchantment.ambush.level_2");
	public static final SoundEvent AMBUSH_LEVEL_3 = register("enchantment.ambush.level_3");

	public static final SoundEvent FIRES_OF_HOKKAI = register("music_disc.fires_of_hokkai");
	public static final SoundEvent DEVIANTS_LIGHT_MUSIC_BOX = register("music_disc.deviants_light_music_box");
	public static final SoundEvent PRECIPICE_STEREO = register("music_disc.precipice_stereo");


	public static final SoundEvent PRISMATIC_PEARL_THROW = register("item.prismatic_pearl.throw");

	private static SoundEvent register(String id) {
		var location = Stellarity.id(id);
		return Registry.register(BuiltInRegistries.SOUND_EVENT, location, SoundEvent.createVariableRangeEvent(location));
	}

	public static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Sounds");
	}
}
