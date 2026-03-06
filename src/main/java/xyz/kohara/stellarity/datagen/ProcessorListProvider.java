package xyz.kohara.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import xyz.kohara.stellarity.Stellarity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ProcessorListProvider extends FabricDynamicRegistryProvider {
    public ProcessorListProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static ResourceKey<StructureProcessorList> id(String name) {
        return Stellarity.key(Registries.PROCESSOR_LIST, name);
    }

    @Override
    protected void configure(HolderLookup.Provider provider, Entries entries) {
        entries.add(id("obsidian_spikes"), new StructureProcessorList(
            List.of(new RuleProcessor(
                List.of(new ProcessorRule(
                    AlwaysTrueTest.INSTANCE,
                    new RandomBlockStateMatchTest(Blocks.OBSIDIAN.defaultBlockState(), 0.076f),
                    Blocks.CRYING_OBSIDIAN.defaultBlockState()
                ))
            ))
        ));
    }

    @Override
    public String getName() {
        return "stellarity-processor-list-provider";
    }
}
