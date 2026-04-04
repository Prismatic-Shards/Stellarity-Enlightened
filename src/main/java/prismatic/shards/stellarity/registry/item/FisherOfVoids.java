package prismatic.shards.stellarity.registry.item;

import net.minecraft.world.item.FishingRodItem;

public class FisherOfVoids extends FishingRodItem {
	public FisherOfVoids(Properties properties) {
		super(properties);
	}

	public static final Properties PROPERTIES = new Properties().stacksTo(1).durability(100);
}
