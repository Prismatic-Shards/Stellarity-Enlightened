package xyz.kohara.stellarity;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

//? if fabric {
/*import net.fabricmc.api.ModInitializer;
*///? } else if forge {
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
//? } else {
/*
*///? }

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.kohara.stellarity.client.StellarityClient;
import xyz.kohara.stellarity.registry.*;

import static net.minecraft.core.registries.BuiltInRegistries.*;
import static xyz.kohara.stellarity.utils.MiscUtil.temporarilyUnfreezeRegistry;

//? if forgelike
@Mod(Stellarity.MOD_ID)
//? if forge {
@Mod.EventBusSubscriber
//? } else if neoforge {
/*@EventBusSubscriber
*///? }
public class Stellarity /*? if fabric >> ' {'*//*implements ModInitializer*/ {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final String MOD_ID = "stellarity";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final String VERSION = /*$ mod_version*/ "0.3.1";
    public static final String MINECRAFT = /*$ minecraft*/ "1.20.1";
    
    //? if forge {
    public Stellarity() {
        IEventBus event = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(MOD_ID, event);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> event.addListener(EventPriority.HIGHEST, StellarityClient::clientSetup));
        //very funny code
        temporarilyUnfreezeRegistry(ENTITY_TYPE, StellarityEntities::init);
        temporarilyUnfreezeRegistry(BLOCK, StellarityBlocks::init);
        temporarilyUnfreezeRegistry(ITEM, StellarityItems::init);
        StellarityNetworking.init();
        temporarilyUnfreezeRegistry(POTION, StellarityPotions::init);
        temporarilyUnfreezeRegistry(CREATIVE_MODE_TAB, StellarityCreativeModeTabs::init);
        temporarilyUnfreezeRegistry(BLOCK_ENTITY_TYPE, StellarityBlockEntityTypes::init);
        StellarityLootTables.init();
        StellarityCriteriaTriggers.init();
        temporarilyUnfreezeRegistry(RECIPE_TYPE, StellarityRecipeTypes::init);
        temporarilyUnfreezeRegistry(RECIPE_SERIALIZER, StellarityRecipeSerializers::init);
        temporarilyUnfreezeRegistry(PAINTING_VARIANT, StellarityPaintings::init);
        temporarilyUnfreezeRegistry(MOB_EFFECT, StellarityMobEffects::init);
        temporarilyUnfreezeRegistry(SOUND_EVENT, StellaritySounds::init);
    }
    //? } else if neoforge {
    /*
    *///? }

    public static ResourceLocation id(String path) {
        //deduplication of redundant code
        return id(MOD_ID, path);
    }

    public static ResourceLocation id(String namespace, String path) {
        //? if = 1.20.1 {
        return new ResourceLocation(namespace, path);
        //?} else {
        /*return ResourceLocation.fromNamespaceAndPath(namespace, path);
         *///?}
    }

    // cant we just hardhode this to use "minecraft" in the namespace?
    public static ResourceLocation mcId(String path) {
        //proposal:
        //return id("minecraft", path);
        //? if = 1.20.1 {
        return new ResourceLocation(path);
        //?} else {
        /*return ResourceLocation.withDefaultNamespace(path);
         *///?}
    }

    public static <T extends Registry<U>, U> ResourceKey<U> key(ResourceKey<T> registry, String path) {
        return ResourceKey.create(registry, id(path));
    }

    public static <T extends Registry<U>, U> ResourceKey<U> mcKey(ResourceKey<T> registry, String path) {
        return ResourceKey.create(registry, mcId(path));
    }

    //? if fabric {
    /*@Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        StellarityItems.init();
        StellarityNetworking.init();
        StellarityPotions.init();
        StellarityBlocks.init();
        StellarityCreativeModeTabs.init();
        StellarityEntities.init();
        StellarityBlockEntityTypes.init();
        StellarityLootTables.init();
        StellarityCriteriaTriggers.init();
        StellarityRecipeTypes.init();
        StellarityRecipeSerializers.init();
        StellarityPaintings.init();
        StellarityMobEffects.init();
        StellaritySounds.init();
        
    }
    *///? }
}