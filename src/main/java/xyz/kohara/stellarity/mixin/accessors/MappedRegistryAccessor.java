package xyz.kohara.stellarity.mixin.accessors;

import net.minecraft.core.MappedRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MappedRegistry.class)
public interface MappedRegistryAccessor<T> {
    @Accessor
    boolean getFrozen();
    
    @Accessor
    void setFrozen(boolean frozen);
}
