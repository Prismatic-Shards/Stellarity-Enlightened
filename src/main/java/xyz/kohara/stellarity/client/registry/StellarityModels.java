package xyz.kohara.stellarity.client.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.renderer.BiomeColors;

import net.minecraft.world.level.block.Block;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityBlocks;

//? 1.21.1 {
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.Item;

import xyz.kohara.stellarity.registry.StellarityItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;

//?} else {
/*import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
*///?}

import java.util.List;

@Environment(EnvType.CLIENT)
public class StellarityModels {
	//? <= 1.21.4 {
	private static void registerBowModel(Item bow) {
		ItemProperties.register(bow, Stellarity.mcId("pull"), (itemStack, clientWorld, entity, seed) -> {
			if (entity == null) {
				return 0.0F;
			}

			return entity.getUseItem() != itemStack ? 0.0F : (itemStack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 20.0F;
		});

		ItemProperties.register(bow, Stellarity.mcId("pulling"), (itemStack, clientWorld, entity, seed) -> {
			if (entity == null) {
				return 0.0F;
			}
			return entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F;
		});
	}

	private static void registerFishingRodModel(Item fishingRod) {

		ItemProperties.register(fishingRod, Stellarity.mcId("cast"), (itemStack, clientWorld, livingEntity, seed) -> {
			if (livingEntity == null) {
				return 0.0F;
			} else {
				boolean bl = livingEntity.getMainHandItem() == itemStack;
				boolean bl2 = livingEntity.getOffhandItem() == itemStack;
				if (livingEntity.getMainHandItem().getItem() instanceof FishingRodItem) {
					bl2 = false;
				}

				return (bl || bl2) && livingEntity instanceof Player player && player.fishing != null ? 1.0F : 0.0F;
			}
		});


	}

	//?}

	public static void initModelPredicates() {
		//? <= 1.21.4 {
		registerBowModel(StellarityItems.CALL_OF_THE_VOID);
		registerFishingRodModel(StellarityItems.FISHER_OF_VOIDS);
		Stellarity.LOGGER.info("Stellarity Model Predicates Initialized!");
		//?}

	}

	public static void initBlockColors() {

		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			if (world != null && pos != null) {
				return BiomeColors.getAverageGrassColor(world, pos);
			}
			// fallback tint
			return 0x91BD59;
		}, StellarityBlocks.ENDER_GRASS_BLOCK);

		for (Block block : List.of(StellarityBlocks.ENDER_GRASS_BLOCK, StellarityBlocks.DUSKBERRY_BUSH)) {
			//~ if > 1.21.10 'BlockRenderLayerMap.INSTANCE.p' -> 'BlockRenderLayerMap.p' {
			//~ if > 1.21.10 'RenderType.cutout()' -> 'ChunkSectionLayer.CUTOUT'  {
			BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
			//~}
			//~}
		}

		Stellarity.LOGGER.info("Initialized Block Model Colors");
	}

	public static void initItemColors() {
		//? 1.21.1{
		ColorProviderRegistry.ITEM.register((itemStack, i) -> 0x91BD59, StellarityItems.ENDER_GRASS_BLOCK);
		//?}

		Stellarity.LOGGER.info("Initialized Item Model Colors");

	}

	public static void init() {
		initModelPredicates();
		initBlockColors();
		initItemColors();
	}
}
