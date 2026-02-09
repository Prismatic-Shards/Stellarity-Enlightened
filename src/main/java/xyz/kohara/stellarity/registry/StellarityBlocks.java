package xyz.kohara.stellarity.registry;

import dev.architectury.hooks.item.tool.HoeItemHooks;
import dev.architectury.hooks.item.tool.ShovelItemHooks;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RootedDirtBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.block.AltarOfTheAccursed;
import xyz.kohara.stellarity.registry.block.EnderDirtPath;
import xyz.kohara.stellarity.registry.block.EnderGrassBlock;

import java.util.function.Function;

public class StellarityBlocks {
    public static final Block ENDER_DIRT = register("ender_dirt", Block::new, BlockBehaviour.Properties.of()
        .mapColor(MapColor.DIRT)
        .strength(0.5F)
        .sound(SoundType.ROOTED_DIRT));
    public static final EnderGrassBlock ENDER_GRASS_BLOCK = register("ender_grass_block", EnderGrassBlock::new, EnderGrassBlock.blockProperties());
    public static final RotatedPillarBlock ASHEN_FROGLIGHT = register("ashen_froglight", RotatedPillarBlock::new, BlockBehaviour.Properties.of()
        .mapColor(MapColor.SAND)
        .strength(0.3F)
        .lightLevel((state) -> 15)
        .sound(SoundType.FROGLIGHT));
    public static final RootedDirtBlock ROOTED_ENDER_DIRT = register("rooted_ender_dirt", RootedDirtBlock::new, BlockBehaviour.Properties.of()
        .mapColor(MapColor.DIRT)
        .strength(0.5F)
        .sound(SoundType.ROOTED_DIRT));
    public static final EnderDirtPath ENDER_DIRT_PATH = register("ender_dirt_path", EnderDirtPath::new, EnderDirtPath.blockProperties());
    public static final AltarOfTheAccursed ALTAR_OF_THE_ACCURSED = register("altar_of_the_accursed", AltarOfTheAccursed::new, AltarOfTheAccursed.blockProperties());


    public static <T extends Block> T register(String id, Function<BlockBehaviour.Properties, T> blockFactory, BlockBehaviour.Properties settings) {
        var location = Stellarity.id(id);
        return Registry.register(BuiltInRegistries.BLOCK, location, blockFactory.apply(settings));
    }

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Blocks");
        
        HoeItemHooks.addTillable(
            ROOTED_ENDER_DIRT,
            unused -> true,
            HoeItem.changeIntoStateAndDropItem(ENDER_DIRT.defaultBlockState(), Items.HANGING_ROOTS),
            unused -> ENDER_DIRT.defaultBlockState());
        ShovelItemHooks.addFlattenable(ENDER_DIRT, ENDER_DIRT_PATH.defaultBlockState());
        ShovelItemHooks.addFlattenable(ENDER_GRASS_BLOCK, ENDER_DIRT_PATH.defaultBlockState());
    }
}
