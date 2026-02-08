package xyz.kohara.stellarity.datagen;

//? if fabric {
/*import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
*///? } else if forge {
import net.minecraft.core.HolderLookup;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
//? }
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import org.jetbrains.annotations.Nullable;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.datagen.provider.*;
import xyz.kohara.stellarity.datagen.provider.loot_table.BlockLootTableProvider;
import xyz.kohara.stellarity.datagen.provider.loot_table.FishingLootTableProvider;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

//? if forge {
@Mod.EventBusSubscriber(modid = Stellarity.MOD_ID)
//? }
public class StellarityDatagen /*? if fabric >> ' {'*//* implements DataGeneratorEntrypoint, ModInitializer*/ {
    //? if fabric {
    /*@Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();


        pack.addProvider(ModelProvider::new);
        pack.addProvider(AdvancementProvider::new);
        pack.addProvider(ItemTagProvider::new);
        pack.addProvider(RecipeProvider::new);
        pack.addProvider(BlockLootTableProvider::new);
        pack.addProvider(BlockTagProvider::new);
        pack.addProvider(FishingLootTableProvider::new);


        pack.addProvider((fabricDataOutput, completableFuture) -> new EndonomiconBookProvider(fabricDataOutput, fabricDataGenerator, completableFuture.join(), "endonomicon"));
    }

    @Override
    public void onInitialize() {

    }

    @Override
    public @Nullable String getEffectiveModId() {
        return "stellarity";
    }
    *///? } else if forge {
    @SubscribeEvent
    public static void theDatagen(GatherDataEvent event) {
        // here you go
        // im only fixing this because datagen suddenly started running itself
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        HolderLookup.Provider lookup = VanillaRegistries.createLookup();
        CompletableFuture<HolderLookup.Provider> registryLookup = CompletableFuture.completedFuture(lookup);
        PackOutput clientOutput = generator.getPackOutput("assets/stellarity");
        PackOutput serverOutput = generator.getPackOutput("data/stellarity");
        
        generator.addProvider(event.includeClient(), new ModelProvider.Blocks(clientOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModelProvider.Items(clientOutput, existingFileHelper));
        generator.addProvider(event.includeServer(), new LootTableProvider(
            serverOutput,
            Collections.emptySet(),
            List.of(
                new LootTableProvider.SubProviderEntry(BlockLootTableProvider::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(FishingLootTableProvider::new, LootContextParamSets.FISHING)
            )
        ));
        generator.addProvider(event.includeServer(), new AdvancementProvider(serverOutput, registryLookup));
        generator.addProvider(event.includeServer(), new EndonomiconBookProvider(serverOutput, generator, lookup, "endonomicon"));
        generator.addProvider(event.includeServer(), new BlockTagProvider(serverOutput, registryLookup, existingFileHelper));
        generator.addProvider(event.includeServer(), new ItemTagProvider(serverOutput, registryLookup, existingFileHelper));
    }
    //? }
}
