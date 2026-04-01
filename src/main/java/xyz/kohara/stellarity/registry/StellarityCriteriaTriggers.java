package xyz.kohara.stellarity.registry;

import net.minecraft.advancements.CriteriaTriggers;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.advancement_criterion.SpecialCraftTrigger;
import xyz.kohara.stellarity.registry.advancement_criterion.VoidFishedTrigger;

public class StellarityCriteriaTriggers {
	public static final VoidFishedTrigger VOID_FISHED = CriteriaTriggers.register(

		"stellarity:void_fished",
		new VoidFishedTrigger()
	);

	public static final SpecialCraftTrigger SPECIAL_CRAFT = CriteriaTriggers.register(
		"stellarity:special_craft",
		new SpecialCraftTrigger()
	);

	public static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Criteria Triggers");
	}
}
