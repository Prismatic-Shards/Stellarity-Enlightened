package xyz.kohara.stellarity.registry;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.block_entity.AltarOfTheAccursedBlockEntity;

public class StellarityBlockEntityTypes {
    private static final Registrar<BlockEntityType<?>> BLOCK_ENTITY_TYPES = StellarityRegistries.MANAGER.get().get(Registries.BLOCK_ENTITY_TYPE);
    
    public static final BlockEntityType<AltarOfTheAccursedBlockEntity> ALTAR_OF_THE_ACCURSED =
        register("altar_of_the_accursed", AltarOfTheAccursedBlockEntity::new, StellarityBlocks.ALTAR_OF_THE_ACCURSED);
    
    public static <T extends BlockEntity> BlockEntityType<T> register(String path, BlockEntityType.BlockEntitySupplier<T> factory, Block... blocks) {
        var ret = BlockEntityType.Builder.of(factory, blocks).build(null);
        BLOCK_ENTITY_TYPES.register(Stellarity.id(path), () -> ret);
        return ret;
    }

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Block Entities");
    }
}
