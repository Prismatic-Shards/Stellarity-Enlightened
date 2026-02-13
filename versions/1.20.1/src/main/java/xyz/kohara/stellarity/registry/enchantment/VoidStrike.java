package xyz.kohara.stellarity.registry.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import xyz.kohara.stellarity.registry.StellarityMobEffects;
import xyz.kohara.stellarity.registry.StellaritySounds;
import xyz.kohara.stellarity.tags.StellarityDamageTypeTags;

public class VoidStrike extends Enchantment {
    public VoidStrike() {
        super(Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }


    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public int getMinCost(int i) {
        return 75;
    }

    @Override
    public int getMaxCost(int i) {
        return 100;
    }

    @Override
    public Component getFullname(int i) {
        return super.getFullname(i).copy().withStyle(ChatFormatting.DARK_PURPLE);
    }

    @Override
    public boolean canEnchant(ItemStack itemStack) {
        return itemStack.getItem() instanceof AxeItem || super.canEnchant(itemStack);
    }

    @Override
    public void doPostAttack(LivingEntity livingEntity, Entity entity, int i) {
        if (entity instanceof LivingEntity victim && victim.level() instanceof ServerLevel level) {
            var dmgSource = victim.getLastDamageSource();
            if (dmgSource == null || victim.isInvulnerable() || !dmgSource.is(StellarityDamageTypeTags.MELEE) || dmgSource.isIndirect())
                return;

            victim.addEffect(new MobEffectInstance(StellarityMobEffects.VOIDED, 160));
        }
    }
}
