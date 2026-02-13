package xyz.kohara.stellarity.registry.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class VoidShot extends Enchantment {
    public VoidShot() {
        super(Rarity.VERY_RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{
            EquipmentSlot.MAINHAND
        });
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public Component getFullname(int i) {
        return super.getFullname(i).copy().withStyle(ChatFormatting.DARK_PURPLE);
    }

    @Override
    public int getMinCost(int i) {
        return 75;
    }

    @Override
    public int getMaxCost(int i) {
        return 100;
    }


}
