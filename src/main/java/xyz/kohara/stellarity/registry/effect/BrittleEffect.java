package xyz.kohara.stellarity.registry.effect;

//? if >= 1.21.1 {

/*import net.minecraft.server.level.ServerLevel;
    *///?}
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import xyz.kohara.stellarity.registry.StellarityDamageTypes;
import xyz.kohara.stellarity.utils.DamageUtility;

public class BrittleEffect extends MobEffect {
    public BrittleEffect() {
        super(MobEffectCategory.HARMFUL, 0xddddff);
    }

}