package xyz.kohara.stellarity.utils;

import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;



import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

//? if forge {
/*import net.minecraftforge.registries.ForgeRegistry;
import java.lang.invoke.MethodHandle;
*///? }

public class MiscUtil {
    //When the throwable constructor doesnt support this
    public static <T extends Throwable> T initThrowableCause(T original, Throwable cause) {
        original.initCause(cause);
        return original;
    }
    
    /**
     * use this if you feel very devious<p>
     * also the built-in checker is stupid and you should place the {@code return} statement right after throwing
     * @param t throwable
     * @param <T> throwable
     * @throws T throwable
     */
    @SuppressWarnings("unchecked")
    public static <T extends Throwable> void sneakyThrow(Throwable t) throws T {
        throw (T) t;
    }
    
    public static void temporarilyUnfreezeRegistry(Registry<?> registry, Runnable action) {
        try {
            //? if forge {
            /*boolean forge = false;
            Class<?> namespacedWrapperClass = Class.forName("net.minecraftforge.registries.NamespacedWrapper");
            try {
                namespacedWrapperClass.cast(registry);
                // forge registry confirmed
                forge = true;
            } catch (ClassCastException e) {
                // vanilla registry, fall through
            }
            if (forge) {
                Class<?> cls;
                MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(namespacedWrapperClass, MethodHandles.lookup());
                VarHandle frozen0 = lookup.findVarHandle(namespacedWrapperClass, "frozen", boolean.class);
                VarHandle locked = lookup.findVarHandle(namespacedWrapperClass, "locked", boolean.class);
                MethodHandle forgeRegistryGetter = lookup.findGetter(namespacedWrapperClass, "delegate", ForgeRegistry.class);
                ForgeRegistry<?> forgeRegistry = (ForgeRegistry<?>) forgeRegistryGetter.invoke(registry);
                VarHandle frozen1 = MethodHandles.privateLookupIn((cls = forgeRegistry.getClass()), MethodHandles.lookup())
                    .findVarHandle(cls, "isFrozen", boolean.class);
                boolean[] bools = {(boolean) frozen0.get(registry), (boolean) locked.get(registry), (boolean) frozen1.get(forgeRegistry)};
                // unfrozen
                frozen0.set(registry, false);
                locked.set(registry, false);
                frozen1.set(forgeRegistry, false);
                // before reverting
                action.run();
                // reverted
                frozen0.set(registry, bools[0]);
                locked.set(registry, bools[1]);
                frozen1.set(forgeRegistry, bools[2]);
                return;
            }
            *///? }
            // impl is always vanilla, neo just adds another base layer to it
            MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(MappedRegistry.class, MethodHandles.lookup());
            VarHandle frozen = lookup.findVarHandle(MappedRegistry.class, "frozen", boolean.class);
            boolean wasFrozen = (boolean) frozen.get(registry);
            // unfreeze
            frozen.set(registry, false);
            // before reverting
            action.run();
            // reverted
            frozen.set(registry, wasFrozen);
        } catch (Throwable t) {
            // this is not normal
            throw initThrowableCause(new UnknownError("this is definitely not normal"), t);
        }
    }
}