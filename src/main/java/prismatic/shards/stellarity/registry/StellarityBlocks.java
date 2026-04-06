package prismatic.shards.stellarity.registry;

import net.fabricmc.fabric.api.registry.FlattenableBlockRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RootedDirtBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.block.AltarOfTheAccursed;
import prismatic.shards.stellarity.registry.block.DuskberryBush;
import prismatic.shards.stellarity.registry.block.EnderDirtPath;
import prismatic.shards.stellarity.registry.block.EnderGrassBlock;

import java.util.function.Function;

public interface StellarityBlocks {
	Block ENDER_DIRT = register("ender_dirt", Block::new, BlockBehaviour.Properties.of()
		.mapColor(MapColor.DIRT)
		.strength(0.5F)
		.sound(SoundType.ROOTED_DIRT));
	Block ENDER_GRASS_BLOCK = register("ender_grass_block", EnderGrassBlock::new, EnderGrassBlock.PROPERTIES);
	Block ASHEN_FROGLIGHT = register("ashen_froglight", RotatedPillarBlock::new, BlockBehaviour.Properties.of()
		.mapColor(MapColor.SAND)
		.strength(0.3F)
		.lightLevel((state) -> 15)
		.sound(SoundType.FROGLIGHT));
	Block ROOTED_ENDER_DIRT = register("rooted_ender_dirt", RootedDirtBlock::new, BlockBehaviour.Properties.of()
		.mapColor(MapColor.DIRT)
		.strength(0.5F)
		.sound(SoundType.ROOTED_DIRT));
	Block ENDER_DIRT_PATH = register("ender_dirt_path", EnderDirtPath::new, EnderDirtPath.PROPERTIES);
	Block ALTAR_OF_THE_ACCURSED = register("altar_of_the_accursed", AltarOfTheAccursed::new, AltarOfTheAccursed.PROPERTIES);
	Block DUSKBERRY_BUSH = register("duskberry_bush", DuskberryBush::new, DuskberryBush.PROPERTIES);

	static Block register(String id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings) {
		var location = Stellarity.key(Registries.BLOCK, id);
		settings = settings.setId(location);

		Block block = blockFactory.apply(settings);
		Registry.register(BuiltInRegistries.BLOCK, location, block);

		return block;
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Blocks");
		TillableBlockRegistry.register(ROOTED_ENDER_DIRT, (unused) -> true, HoeItem.changeIntoStateAndDropItem(StellarityBlocks.ENDER_DIRT.defaultBlockState(), Items.HANGING_ROOTS));
		FlattenableBlockRegistry.register(ENDER_DIRT, ENDER_DIRT_PATH.defaultBlockState());
		FlattenableBlockRegistry.register(ENDER_GRASS_BLOCK, ENDER_DIRT_PATH.defaultBlockState());
	}
}
