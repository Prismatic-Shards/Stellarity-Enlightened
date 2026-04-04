package prismatic.shards.stellarity.registry.item;

import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class CallOfTheVoid extends BowItem {
	public static final Item.Properties PROPERTIES = new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1);

	public CallOfTheVoid(Properties properties) {
		super(properties);
	}

}