package xyz.kohara.stellarity.registry.enchantment;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import xyz.kohara.stellarity.registry.StellaritySounds;

public class AmbushEnchantment extends Enchantment {
    public AmbushEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    private static final SoundEvent[] SOUNDS = new SoundEvent[]{
        StellaritySounds.AMBUSH_LEVEL_1,
        StellaritySounds.AMBUSH_LEVEL_2,
        StellaritySounds.AMBUSH_LEVEL_3
    };

    @Override
    public int getMinCost(int i) {
        return 12 + 17 * (i - 1);
    }

    @Override
    public int getMaxCost(int i) {
        return 28 + 17 * (i - 1);
    }

    @Override
    public void doPostAttack(LivingEntity livingEntity, Entity entity, int i) {

        if (entity instanceof Mob mob && mob.level() instanceof ServerLevel level) {
            if (mob.isNoAi()) return;
            var target = mob.getTarget();
            if (target != null && target.equals(livingEntity)) return;

            mob.hurt(mob.getLastDamageSource(), mob.lastHurt * (1f + i * 0.5f));

            entity.playSound(SOUNDS[i - 1]);

            float height = entity.getBbHeight() * 0.7f;
            float width = entity.getBbWidth() * 0.7f;

            level.sendParticles(ParticleTypes.WAX_OFF, entity.getX(), entity.getY(0.5), entity.getZ(), 50, width, height, width, 0.3);

        }
    }
}
