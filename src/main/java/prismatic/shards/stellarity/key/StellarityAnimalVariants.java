package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.animal.chicken.ChickenVariant;
import net.minecraft.world.entity.animal.cow.CowVariant;
import net.minecraft.world.entity.animal.feline.CatVariant;
import net.minecraft.world.entity.animal.frog.FrogVariant;
import net.minecraft.world.entity.animal.pig.PigVariant;
import net.minecraft.world.entity.animal.wolf.WolfVariant;

import static prismatic.shards.stellarity.Stellarity.*;

public interface StellarityAnimalVariants {
	ResourceKey<ChickenVariant> ENDER_CHICKEN = key(Registries.CHICKEN_VARIANT, "end");
	ResourceKey<PigVariant> ENDER_PIG = key(Registries.PIG_VARIANT, "end");
	ResourceKey<CowVariant> ENDER_COW = key(Registries.COW_VARIANT, "end");
	ResourceKey<WolfVariant> ENDER_WOLF = key(Registries.WOLF_VARIANT, "end");
	ResourceKey<FrogVariant> ENDER_FROG = key(Registries.FROG_VARIANT, "end");
	ResourceKey<CatVariant> ENDER_CAT = key(Registries.CAT_VARIANT, "end");

}
