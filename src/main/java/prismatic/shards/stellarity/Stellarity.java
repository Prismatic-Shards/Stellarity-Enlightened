package prismatic.shards.stellarity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.MixinEnvironment;
import prismatic.shards.stellarity.registry.*;

public class Stellarity implements ModInitializer {


	public static final String MOD_ID = "stellarity";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	public static Identifier id(String namespace, String path) {
		return Identifier.fromNamespaceAndPath(namespace, path);
	}

	public static Identifier mcId(String path) {
		return Identifier.withDefaultNamespace(path);
	}

	public static <T extends Registry<U>, U> ResourceKey<U> key(ResourceKey<T> registry, String path) {
		return ResourceKey.create(registry, id(path));
	}

	public static <T extends Registry<U>, U> ResourceKey<U> mcKey(ResourceKey<T> registry, String path) {
		return ResourceKey.create(registry, mcId(path));
	}

	@SuppressWarnings("CanBeFinal")
	boolean audit = true;

	@Override
	public void onInitialize() {

		StellarityDataAttachments.init();
		StellarityItems.init();
		StellarityParticles.init();
		StellarityNetworking.init();
		StellarityPotions.init();
		StellarityBlocks.init();
		StellarityCreativeModeTabs.init();
		StellarityEnchantments.init();
		StellarityEntities.init();
		StellarityBlockEntityTypes.init();
		StellarityLootTables.init();
		StellarityCriteriaTriggers.init();
		StellarityRecipeTypes.init();
		StellarityRecipeSerializers.init();
		StellarityMobEffects.init();
		StellaritySounds.init();
		StellarityVillagerTypes.init();
		StellarityVillagerProfessions.init();

		if (FabricLoader.getInstance().isDevelopmentEnvironment() && audit)
			MixinEnvironment.getCurrentEnvironment().audit();
	}
}