package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityEquipmentAssets {
	ResourceKey<EquipmentAsset> SHULKER = id("shulker");

	static ResourceKey<EquipmentAsset> id(final String name) {
		return ResourceKey.create(EquipmentAssets.ROOT_ID, Stellarity.id(name));
	}

}
