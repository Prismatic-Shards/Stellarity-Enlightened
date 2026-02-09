package xyz.kohara.stellarity.registry.item;

import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;
//? if forge {
/*import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
*///? } else if neoforge {

/*import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
*///? }

public class FisherOfVoids extends FishingRodItem {
    public FisherOfVoids(Properties properties) {
        super(properties);
    }

    public static Properties properties() {
        return new Properties().stacksTo(1).durability(100);
    }
    
    //? neoforge {
    /*public boolean canPerformAction(
        @NotNull ItemStack stack, ItemAbility itemAbility
 
    ) {
        if (itemAbility.equals(ItemAbilities.FISHING_ROD_CAST)) return true;
        return super.canPerformAction(stack, itemAbility);

    }
    *///? } else if forge {
    /*public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        if (toolAction.equals(ToolActions.FISHING_ROD_CAST)) return true;
        return super.canPerformAction(stack, toolAction);
    }
    *///? }
}
