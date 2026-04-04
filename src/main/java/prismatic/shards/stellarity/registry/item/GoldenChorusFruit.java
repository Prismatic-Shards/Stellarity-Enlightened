package prismatic.shards.stellarity.registry.item;


public class GoldenChorusFruit extends TeleportingFood {
	private static final int TELEPORT_DIAMETER = 300;

	public GoldenChorusFruit(Properties properties) {
		super(properties, TELEPORT_DIAMETER);
	}

	public static final Properties PROPERTIES = TeleportingFood.foodProperties(6, 14.4f, true, TELEPORT_DIAMETER);
}