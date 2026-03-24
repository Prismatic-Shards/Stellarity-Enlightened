package xyz.kohara.stellarity.mixin.dragon_fight;


import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.RandomSequence;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.EndSpikeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.EndSpikeConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityBlocks;

@Mixin(EndSpikeFeature.class)
public abstract class SpikeFeatureMixin extends Feature<EndSpikeConfiguration> {

	public SpikeFeatureMixin(Codec<EndSpikeConfiguration> codec) {
		super(codec);
	}

	@Unique
	private RandomSource random;

	@Inject(method = "placeSpike", at = @At("HEAD"))
	private void stellaritySpikeInit(ServerLevelAccessor serverLevelAccessor, RandomSource randomSource, EndSpikeConfiguration spikeConfiguration, EndSpikeFeature.EndSpike endSpike, CallbackInfo ci) {
		random = new RandomSequence((long) (endSpike.getCenterX()) << 32 & endSpike.getCenterX(), Stellarity.id("obsidian_splatter")).random();
	}

	@Definition(id = "OBSIDIAN", field = "Lnet/minecraft/world/level/block/Blocks;OBSIDIAN:Lnet/minecraft/world/level/block/Block;")
	@Expression("OBSIDIAN.?()")
	@WrapOperation(method = "placeSpike", at = @At("MIXINEXTRAS:EXPRESSION"))
	private BlockState cryingObsidianTops(Block instance, Operation<BlockState> original, @Local(name = "pos") BlockPos blockPos, @Local(argsOnly = true) EndSpikeFeature.EndSpike spike) {
		if (spike.stellarity$hasCryingObsidianTops()) {
			int distance = spike.getHeight() - blockPos.getY();

			if (distance <= 15f && random.nextFloat() < -0.05f * distance + 0.8f)
				return Blocks.CRYING_OBSIDIAN.defaultBlockState();
		}


		return original.call(instance);

	}

	@Definition(id = "setBlock", method = "Lnet/minecraft/world/level/levelgen/feature/EndSpikeFeature;setBlock(Lnet/minecraft/world/level/LevelWriter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V")
	@Definition(id = "blockPos", local = @Local(type = BlockPos.class))
	@Expression("?.setBlock(?,blockPos,?)")
	@WrapOperation(method = "placeSpike", at = @At("MIXINEXTRAS:EXPRESSION"))
	private void hollowForAltar(EndSpikeFeature instance, LevelWriter levelWriter, BlockPos blockPos, BlockState blockState, Operation<Void> original, @Local(argsOnly = true) EndSpikeFeature.EndSpike spike) {
		int y = blockPos.getY();
		if (spike.stellarity$hasAltar() && y < spike.getHeight() - 4 && y > spike.getHeight() - 30) return;
		original.call(instance, levelWriter, blockPos, blockState);

	}

	@WrapOperation(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/feature/EndSpikeFeature;placeSpike(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/level/levelgen/feature/configurations/EndSpikeConfiguration;Lnet/minecraft/world/level/levelgen/feature/EndSpikeFeature$EndSpike;)V"))
	private void placeAltar(EndSpikeFeature instance, ServerLevelAccessor serverLevelAccessor, RandomSource randomSource, EndSpikeConfiguration spikeConfiguration, EndSpikeFeature.EndSpike endSpike, Operation<Void> original) {
		try {
			var altarPos = new BlockPos(endSpike.getCenterX(), endSpike.getHeight() - 20, endSpike.getCenterZ());
			var altar = serverLevelAccessor.getBlockState(altarPos);

			original.call(instance, serverLevelAccessor, randomSource, spikeConfiguration, endSpike);
			if (!endSpike.stellarity$hasAltar() || altar.is(StellarityBlocks.ALTAR_OF_THE_ACCURSED)) return;

			var placePos = altarPos.offset(-8, -9, -9);

			var server = serverLevelAccessor.getServer();

			if (server != null) {
				serverLevelAccessor.getServer().getStructureManager().getOrCreate(Stellarity.id("altar_of_the_accursed")).placeInWorld(serverLevelAccessor, placePos, placePos, new StructurePlaceSettings(), randomSource, Block.UPDATE_CLIENTS);
			} else {
				Stellarity.LOGGER.info("failed to create the altar");
			}

		} catch (Exception e) {

			Stellarity.LOGGER.error("Failed to place spike correctly", e);
		}
	}


}
