package prismatic.shards.stellarity.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityEquipmentAssets {
	ResourceKey<EquipmentAsset> SHULKER = createId("shulker");

	static ResourceKey<EquipmentAsset> createId(final String name) {
		return ResourceKey.create(EquipmentAssets.ROOT_ID, Stellarity.id(name));
	}

}
