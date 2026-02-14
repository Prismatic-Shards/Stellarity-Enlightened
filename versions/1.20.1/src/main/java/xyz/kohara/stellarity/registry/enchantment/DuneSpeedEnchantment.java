package xyz.kohara.stellarity.registry.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class DuneSpeedEnchantment extends Enchantment {

    public DuneSpeedEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMinCost(int i) {
        return 35 + (i - 1) * 10;
    }

    @Override
    public int getMaxCost(int i) {
        return 50 + (i - 1) * 10;
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return enchantment != Enchantments.SOUL_SPEED;
    }

    @Override
    public Component getFullname(int i) {
        return super.getFullname(i).copy().withStyle(ChatFormatting.GOLD);
    }


}
