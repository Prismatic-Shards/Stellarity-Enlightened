package xyz.kohara.stellarity.registry.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import xyz.kohara.stellarity.registry.StellarityParticles;
import xyz.kohara.stellarity.registry.StellaritySounds;

public class CriticalStrikeEnchantment extends DamageEnchantment {
    private final RandomSource random = RandomSource.create();

    public CriticalStrikeEnchantment() {
        super(Rarity.VERY_RARE, 9999, EquipmentSlot.MAINHAND);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public Component getFullname(int i) {
        return super.getFullname(i).copy().withStyle(ChatFormatting.DARK_PURPLE);
    }


    public boolean checkCompatibility(Enchantment enchantment) {
        return enchantment instanceof DamageEnchantment;
    }

    @Override
    public int getMinCost(int i) {
        return 50 + 11 * (i - 1);
    }

    @Override
    public int getMaxCost(int i) {
        return 75 + 15 * (i - 1);
    }

    @Override
    public void doPostAttack(LivingEntity livingEntity, Entity entity, int i) {

        if (entity instanceof LivingEntity target && target.level() instanceof ServerLevel level && random.nextFloat() < i * 0.1f) {
            target.hurt(target.getLastDamageSource(), target.lastHurt * 2);
            target.playSound(StellaritySounds.CRITICAL_STRIKE);
            float height = target.getBbHeight() * 0.7f;
            float width = target.getBbWidth() * 0.7f;
            level.sendParticles(StellarityParticles.CRITICAL_STRIKE, target.getX(), target.getY(0.5), target.getZ(), 20, width, height, width, 0.3);

        }
    }
}
