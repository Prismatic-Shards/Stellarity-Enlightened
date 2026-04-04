package prismatic.shards.stellarity.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.block_entity.AltarOfTheAccursedBlockEntity;


public class StellarityBlockEntityTypes {
	public static final BlockEntityType<AltarOfTheAccursedBlockEntity> ALTAR_OF_THE_ACCURSED = register("altar_of_the_accursed", AltarOfTheAccursedBlockEntity::new, StellarityBlocks.ALTAR_OF_THE_ACCURSED);


	public static <T extends BlockEntity> BlockEntityType<T> register(
		String name,
		FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
		Block... blocks
	) {
		return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Stellarity.id(name), FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
	}


	public static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Block Entities");
	}
}
