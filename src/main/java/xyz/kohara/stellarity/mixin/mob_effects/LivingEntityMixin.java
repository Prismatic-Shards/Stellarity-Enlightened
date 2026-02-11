package xyz.kohara.stellarity.mixin.mob_effects;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;


import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.registry.StellarityDamageTypes;
import xyz.kohara.stellarity.registry.StellarityMobEffects;
import xyz.kohara.stellarity.utils.DamageUtility;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//? 1.20.1 {

//? } else {

import net.minecraft.tags.EntityTypeTags;

import net.minecraft.core.Holder;
    //? }

//? > 1.21.10 {
/*import net.minecraft.server.level.ServerLevel;
 *///?}

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow
    @Final
    private Map</*? 1.20.1 { *//*MobEffect*//*? } else { */ Holder<MobEffect> /*? }*/, MobEffectInstance> activeEffects;

    @Shadow
        //? 1.20.1
    //public abstract boolean hasEffect(MobEffect par1);

    //? > 1.21
    public abstract boolean hasEffect(Holder<MobEffect> par1);


    @Unique
    private static DamageUtility damageUtility;

    @Unique
    private boolean appliedBrittle = false;

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }


    @WrapMethod(method = /*? < 1.21.11 { */"hurt"/*? } else { */ /*"hurtServer" *//*?} */)
    private boolean applyBrittleEffect(/*? if > 1.21.10 >> 'Dam' *//*ServerLevel serverLevel, */DamageSource damageSource, float f, Operation<Boolean> original) {
        boolean hurt = original.call(/*? if > 1.21.10 >> 'dam' *//*serverLevel, */damageSource, f);

        if (!activeEffects.containsKey(StellarityMobEffects.BRITTLE) || !hurt || appliedBrittle || damageSource.is(StellarityDamageTypes.BRITTLE))
            return hurt;

        int amplifier = activeEffects.get(StellarityMobEffects.BRITTLE).getAmplifier();

        var sources = this.damageSources();


        if (damageUtility == null) damageUtility = DamageUtility.builder()
            .setDamageSource(sources.source(StellarityDamageTypes.BRITTLE))
            .setApDamageSource(sources.source(StellarityDamageTypes.BRITTLE))
            .setApRatio(0.4f)
            .build();

        setTicksFrozen(150);

        appliedBrittle = true;

        damageUtility.damageEntity((LivingEntity) (Object) this, (float) amplifier + 1);

        appliedBrittle = false;


        return true;
    }
}
