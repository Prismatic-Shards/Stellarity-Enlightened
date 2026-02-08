package xyz.kohara.stellarity.registry;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import xyz.kohara.stellarity.Stellarity;

//? > 1.21 {
/*import net.minecraft.core.Holder;
*///? }

import java.util.HashMap;

public class StellarityPotions {
    private static final Registrar<Potion> POTIONS = StellarityRegistries.MANAGER.get().get(Registries.POTION);
    
    public static final HashMap<Potion, Integer> COLORS = new HashMap<>();

    public static final Potion BLIND_RAGE = register("blind_rage", "blind_rage",
        new MobEffectInstance(MobEffects.DARKNESS, 15 * 20, 0),
        new MobEffectInstance(
            //? < 1.21.9 {
            MobEffects.DAMAGE_BOOST
             //? } else {
            /*MobEffects.STRENGTH
            *///? }
            , 15 * 20, 2));

    //? > 1.21 {
    /*public static final Holder<Potion> BLIND_RAGE_HOLDER = BuiltInRegistries.POTION.wrapAsHolder(BLIND_RAGE);
    *///? }


    private static Potion register(String id, String name, MobEffectInstance... effects) {
        var ret = new Potion("stellarity." + name, effects);
        POTIONS.register(Stellarity.id(id), () -> ret);
        return ret;
    }

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Potions");
        //please use hex for colors :>
        COLORS.put(BLIND_RAGE, 0x6e5600);
    }
}
