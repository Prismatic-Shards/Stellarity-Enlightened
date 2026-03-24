package xyz.kohara.stellarity.registry;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import xyz.kohara.stellarity.Stellarity;

import static xyz.kohara.stellarity.utils.LootTableUtils.*;


public class StellarityLootTables {
	public static void init() {
		LootTableEvents.MODIFY.register((key, builder, source, provider) -> {


			var id = key.identifier();


			if (id.equals(Stellarity.mcId("entities/magma_cube"))) {
				Stellarity.LOGGER.info("Modifying Magma Cube Loot Table");


				var tag = new CompoundTag();
				tag.putString("variant", "stellarity:end");

				builder.withPool(pool().when(
					onDamage(
						damage().source(
							entity().entityType(entityType(EntityType.FROG)).nbt(nbt(tag))
						)
					)).add(item(StellarityItems.ASHEN_FROGLIGHT)));
			}
		});

	}
}
