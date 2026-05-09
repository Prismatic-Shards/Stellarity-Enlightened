package prismatic.shards.stellarity.datagen.dynamic;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;

public interface CarverProvider {
  static void bootstrap(BootstrapContext<ConfiguredWorldCarver<?>> context) {

  }
}
