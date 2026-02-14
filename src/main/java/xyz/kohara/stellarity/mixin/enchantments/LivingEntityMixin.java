//? 1.20.1 {
package xyz.kohara.stellarity.mixin.enchantments;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.registry.StellarityEnchantments;
import xyz.kohara.stellarity.tags.StellarityBlockTags;

import java.util.UUID;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow
    public abstract ItemStack getItemBySlot(EquipmentSlot equipmentSlot);

    @Shadow
    @Final
    private AttributeMap attributes;

    @Shadow
    public abstract boolean isFallFlying();

    @Shadow
    private float speed;
    @Unique
    private static final UUID PLATED_ARMOR_UUID = UUID.fromString("5c2baa3b-f98a-4e21-b268-02fcbcc9999e");
    @Unique
    private static final UUID PLATED_TOUGHNESS_UUID = UUID.fromString("ffc294d1-1111-44c6-bc2e-7c65a67df088");
    @Unique
    private static final UUID DUNE_SPEED_UUID = UUID.fromString("09a0091a-bf03-46ff-abcf-b34e99d2869a");

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isInWall()Z"))
    private void plated(CallbackInfo ci) {
        var enchantLevel = EnchantmentHelper.getItemEnchantmentLevel(StellarityEnchantments.PLATED, getItemBySlot(EquipmentSlot.CHEST));
        var armor = attributes.getInstance(Attributes.ARMOR);
        var toughness = attributes.getInstance(Attributes.ARMOR_TOUGHNESS);

        if (armor == null || toughness == null) return;

        if (enchantLevel < 3) {
            toughness.removeModifier(PLATED_TOUGHNESS_UUID);
        }
        if (enchantLevel < 1) {
            armor.removeModifier(PLATED_ARMOR_UUID);
            return;
        }

        var armorModifier = armor.getModifier(PLATED_ARMOR_UUID);
        var toughnessModifier = toughness.getModifier(PLATED_TOUGHNESS_UUID);


        var armorAdded = armorModifier != null ? armorModifier.getAmount() : 0;

        if (armorAdded != enchantLevel + 1.0) {
            armor.removeModifier(PLATED_ARMOR_UUID);
            armor.addTransientModifier(new AttributeModifier(
                PLATED_ARMOR_UUID, "stellarity:plated_armor", enchantLevel + 1.0, AttributeModifier.Operation.ADDITION
            ));
        }

        if (toughnessModifier != null && enchantLevel >= 3) {
            toughness.addTransientModifier(new AttributeModifier(
                PLATED_TOUGHNESS_UUID, "stellarity:plated_toughness", 1.0, AttributeModifier.Operation.ADDITION
            ));
        }
    }

    @ModifyVariable(method = "travel", at = @At(value = "STORE", ordinal = 0))
    private double enchantmentGravity(double d) {
        var itemStack = getItemBySlot(EquipmentSlot.CHEST);
        var platedLevel = EnchantmentHelper.getItemEnchantmentLevel(StellarityEnchantments.PLATED, itemStack);
        var soaringLevel = EnchantmentHelper.getItemEnchantmentLevel(StellarityEnchantments.SOARING, itemStack);

        return d * (1 + (platedLevel > 0 ? (0.01 + platedLevel * 0.03) : 0) + (soaringLevel * -0.05));
    }

    @Inject(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isInWall()Z"))
    private void duneSpeed(CallbackInfo ci) {
        var enchantLevel = EnchantmentHelper.getItemEnchantmentLevel(StellarityEnchantments.DUNE_SPEED, getItemBySlot(EquipmentSlot.FEET));
        var speed = attributes.getInstance(Attributes.MOVEMENT_SPEED);

        if (speed == null) return;

        if (enchantLevel < 1 || !level().getBlockState(this.getBlockPosBelowThatAffectsMyMovement()).is(StellarityBlockTags.DUNE_SPEED_BLOCKS) || isFallFlying()) {
            speed.removeModifier(DUNE_SPEED_UUID);
            return;
        }

        var speedModifier = speed.getModifier(DUNE_SPEED_UUID);
        var speedAdded = speedModifier != null ? speedModifier.getAmount() : 0;

        var targetSpeed = 0.016 + enchantLevel * 0.009;

        if (speedAdded != targetSpeed) {
            speed.removeModifier(DUNE_SPEED_UUID);
            speed.addTransientModifier(new AttributeModifier(
                DUNE_SPEED_UUID, "stellarity:dune_speed", targetSpeed, AttributeModifier.Operation.ADDITION
            ));
        }
    }
}

//? }