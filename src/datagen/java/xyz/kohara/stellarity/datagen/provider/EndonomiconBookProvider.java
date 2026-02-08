package xyz.kohara.stellarity.datagen.provider;

import dev.aaronhowser.mods.patchoulidatagen.book_element.PatchouliBook;
import dev.aaronhowser.mods.patchoulidatagen.book_element.PatchouliBookCategory;
import dev.aaronhowser.mods.patchoulidatagen.book_element.PatchouliBookElement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityItems;

import java.util.function.Consumer;

public class EndonomiconBookProvider extends PatchouliBookProvider {

    public EndonomiconBookProvider(PackOutput output, DataGenerator generator, HolderLookup.Provider registries, String bookName) {
        super(generator, registries, bookName, output);
    }


    @Override
    public void buildPages(@NotNull Consumer<PatchouliBookElement> consumer) {
        PatchouliBook book = PatchouliBook.builder()
            .setBookText(
                Stellarity.MOD_ID,
                "Endonomicon",
                "An WIP collection of Stellarity knowledge."
            )
            .disableBook()
            .save(consumer);


        //TODO: update with actual chorus armor item
        PatchouliBookCategory armors = PatchouliBookCategory.builder().book(book)
            .setDisplay("Armors", "All Stellarity Armors", StellarityItems.CHORUS_PLATING)
            .save(consumer, "armors");
    }

}