package xyz.kohara.stellarity.registry.enchantment;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class LevitationShot extends Enchantment {
    public LevitationShot() {
        super(Rarity.RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{
            EquipmentSlot.MAINHAND
        });
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getMinCost(int i) {
        return 29 + 4 * i;
    }

    @Override
    public int getMaxCost(int i) {
        return 50 + 10 * i;
    }

    @Override
    public Component getFullname(int i) {
        return super.getFullname(i);
    }
}
