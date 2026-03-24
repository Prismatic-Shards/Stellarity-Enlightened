package xyz.kohara.stellarity.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.color.item.GrassColorSource;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityBlocks;
import xyz.kohara.stellarity.registry.StellarityItems;


public class ModelProvider extends FabricModelProvider {
	public ModelProvider(FabricPackOutput output) {
		super(output);
	}

	public final static Item[] FLAT_ITEMS = new Item[]{
		StellarityItems.SUSHI,
		StellarityItems.GOLDEN_CHORUS_FRUIT,
		StellarityItems.FRIED_CHORUS_FRUIT,
		StellarityItems.FROZEN_CARPACCIO,
		StellarityItems.ENDERMAN_FLESH,
		StellarityItems.CRYSTAL_HEARTFISH,
		StellarityItems.GRILLED_ENDERMAN_FLESH,
		StellarityItems.FLAREFIN_KOI,
		StellarityItems.AMETHYST_BUDFISH,
		StellarityItems.CRIMSON_TIGERFISH,
		StellarityItems.ENDER_KOI,
		StellarityItems.FLESHY_PIRANHA,
		StellarityItems.BUBBLEFISH,
		StellarityItems.PRISMITE,
		StellarityItems.OVERGROWN_COD,
		StellarityItems.PRISMATIC_SUSHI,
		StellarityItems.SHEPHERDS_PIE,
		StellarityItems.CHORUS_PIE,
		StellarityItems.PHO,
		StellarityItems.PHANTOM_ITEM_FRAME,
		StellarityItems.CHORUS_PLATING,
		StellarityItems.ENDERITE_SHARD,
		StellarityItems.ENDERITE_UPGRADE_SMITHING_TEMPLATE,
		StellarityItems.HALLOWED_INGOT,
		StellarityItems.SAND_RUNE,
		StellarityItems.STARLIGHT_SOOT,
		StellarityItems.GILDED_PURPUR_KEY,
		StellarityItems.PURPUR_KEY,
		StellarityItems.WINGED_KEY,
		StellarityItems.PRISMATIC_PEARL,
		StellarityItems.ENDONOMICON,
		StellarityItems.MUSIC_DISC_DEVIANTS_LIGHT_MUSIC_BOX,
		StellarityItems.MUSIC_DISC_FIRES_OF_HOKKAI,
		StellarityItems.MUSIC_DISC_PRECIPICE_STEREO,
		StellarityItems.ROYAL_JELLY,
		StellarityItems.ROYAL_JELLY_II,
		StellarityItems.SATCHEL_OF_VOIDS,
		StellarityItems.DUSKBERRY
	};

	public void generateBush(BlockModelGenerators generators, Block block) {


		generators.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)

			.with(PropertyDispatch.initial(BlockStateProperties.AGE_3)
				.select(0, BlockModelGenerators.plainVariant(generators.createSuffixedVariant(block, "_stage0", ModelTemplates.CROSS, TextureMapping::cross)))
				.select(1, BlockModelGenerators.plainVariant(generators.createSuffixedVariant(block, "_stage1", ModelTemplates.CROSS, TextureMapping::cross)))
				.select(2, BlockModelGenerators.plainVariant(generators.createSuffixedVariant(block, "_stage2", ModelTemplates.CROSS, TextureMapping::cross)))
				.select(3, BlockModelGenerators.plainVariant(generators.createSuffixedVariant(block, "_stage3", ModelTemplates.CROSS, TextureMapping::cross)))
			)
		);


	}


	@Override
	public void generateBlockStateModels(BlockModelGenerators generators) {
		generators.createTrivialCube(StellarityBlocks.ENDER_DIRT);
		generators.createTrivialCube(StellarityBlocks.ROOTED_ENDER_DIRT);
		generators.createNonTemplateModelBlock(StellarityBlocks.ENDER_DIRT_PATH);
		generators.createNonTemplateModelBlock(StellarityBlocks.ALTAR_OF_THE_ACCURSED);

		generateBush(generators, StellarityBlocks.DUSKBERRY_BUSH);


		generators.createAxisAlignedPillarBlock(StellarityBlocks.ASHEN_FROGLIGHT, TexturedModel.COLUMN);
		generators.registerSimpleItemModel(StellarityBlocks.ASHEN_FROGLIGHT, Stellarity.id("block/ashen_froglight"));
		generators.createGrassLikeBlock(StellarityBlocks.ENDER_GRASS_BLOCK, new MultiVariant(WeightedList.<Variant>builder()
			.add(new Variant(Stellarity.id("block/ender_grass_block")))
			.add(new Variant(Stellarity.id("block/ender_grass_block")), 90)
			.add(new Variant(Stellarity.id("block/ender_grass_block")), 180)
			.add(new Variant(Stellarity.id("block/ender_grass_block")), 270)
			.build()), new MultiVariant(WeightedList.<Variant>builder().add(new Variant(Stellarity.id("block/ender_grass_block_snowy"))).build()));
		generators.registerSimpleTintedItemModel(StellarityBlocks.ENDER_GRASS_BLOCK, Stellarity.id("block/ender_grass_block"), new GrassColorSource(1.0f, 0.5f));


	}

	@Override
	public void generateItemModels(ItemModelGenerators generators) {

		generators.generateBow(StellarityItems.CALL_OF_THE_VOID);
		generators.declareCustomModelItem(StellarityItems.SHULKER_BODY);
		generators.generateFishingRod(StellarityItems.FISHER_OF_VOIDS);


		generators.generateFlatItem(StellarityItems.TAMARIS, ModelTemplates.FLAT_HANDHELD_ITEM);

		for (Item item : FLAT_ITEMS) {
			generators.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
		}

	}
}