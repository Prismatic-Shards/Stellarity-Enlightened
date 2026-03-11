package xyz.kohara.stellarity.registry;

import net.minecraft.advancements.CriteriaTriggers;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.advancement_criterion.DragonSummonedTrigger;
import xyz.kohara.stellarity.registry.advancement_criterion.VoidFishedTrigger;

public class StellarityCriteriaTriggers {
	public static final VoidFishedTrigger VOID_FISHED = CriteriaTriggers.register(
		//? > 1.21
		//"stellarity:void_fished",
		new VoidFishedTrigger()
	);

	public static final DragonSummonedTrigger DRAGON_SUMMONED = CriteriaTriggers.register(
		//? > 1.21
		//"stellarity:dragon_summoned",
		new DragonSummonedTrigger()
	);

	public static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Criteria Triggers");
	}
}
