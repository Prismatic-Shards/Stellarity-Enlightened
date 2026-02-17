package xyz.kohara.stellarity.registry;

//? > 1.21 {

/*import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;
*///? }

import xyz.kohara.stellarity.Stellarity;

public class StellarityJukeboxSongs {
    //? > 1.21 {
    /*public static final ResourceKey<JukeboxSong> FIRES_OF_HOKKAI = key("fires_of_hokkai");
    public static final ResourceKey<JukeboxSong> DEVIANTS_LIGHT_MUSIC_BOX = key("deviants_light_music_box");
    public static final ResourceKey<JukeboxSong> PRECIPICE_STEREO = key("precipice_stereo");


    public static final ResourceKey<JukeboxSong> key(String id) {
        return Stellarity.key(Registries.JUKEBOX_SONG, id);
    }


    *///? }

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Jukebox Songs");
    }
}
