package xyz.kohara.stellarity.registry.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import xyz.kohara.stellarity.registry.StellarityEnchantments;
import xyz.kohara.stellarity.tags.StellarityItemTags;

public class SoaringEnchantment extends Enchantment {
    public SoaringEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR, new EquipmentSlot[]{EquipmentSlot.CHEST});
    }

    @Override
    public boolean canEnchant(ItemStack itemStack) {
        return itemStack.is(StellarityItemTags.ELYTRA_ENCHANTABLE);
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return enchantment != StellarityEnchantments.PLATED;
    }

    @Override
    public int getMinCost(int i) {
        return 8 + (i - 1) * 5;
    }

    @Override
    public int getMaxCost(int i) {
        return 19 + (i - 1) * 8;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }
}
