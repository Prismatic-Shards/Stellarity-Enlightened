package xyz.kohara.stellarity.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.advancements.*;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;

import net.minecraft.network.chat.Component;
//? <= 1.21.10 {
import net.minecraft.advancements.critereon.*;
 //? } else {
/*import net.minecraft.advancements.critereon.*;
	*///? }
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityItems;

import java.util.function.Consumer;

import xyz.kohara.stellarity.registry.advancement_criterion.DragonSummonedTrigger;
import xyz.kohara.stellarity.registry.advancement_criterion.VoidFishedTrigger;
//? >= 1.21.1 {
/*import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;
import java.util.List;
import java.util.Optional;

import net.minecraft.core.registries.Registries;
*///?} else {
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.CriterionTriggerInstance;

import java.util.HashMap;
//?}

public class AdvancementProvider extends FabricAdvancementProvider {

	//? >= 1.21.1 {
	/*public final AdvancementType TASK = AdvancementType.TASK;
	public final AdvancementType GOAL = AdvancementType.GOAL;
	public final AdvancementType CHALLENGE = AdvancementType.CHALLENGE;

	public AdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup);
	}

	public static AdvancementHolder dummy(ResourceLocation id) {
		return new AdvancementHolder(id, null);
	}

	*///?} else {

	public final FrameType TASK = FrameType.TASK;
	public final FrameType GOAL = FrameType.GOAL;
	public final FrameType CHALLENGE = FrameType.CHALLENGE;

	public AdvancementProvider(FabricDataOutput output) {
		super(output);
	}

	public static Advancement dummy(ResourceLocation id) {
		return new Advancement(id,
			null,
			null,
			null,
			new HashMap<>(),
			null,
			true
		);
	}
	//?}

	@Override
	public void generateAdvancement(
		//? >= 1.21.1 {
		/*HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer
		*///?} else {
		Consumer<Advancement> consumer
		 //?}
	) {
		//? >= 1.21.1 {
		/*final HolderLookup.RegistryLookup<Item> itemLookup = registryLookup.lookupOrThrow(Registries.ITEM);
		*///?}
		var ENTER_END_GATEWAY = dummy(Stellarity.mcId("end/enter_end_gateway"));
		var END_ROOT = dummy(Stellarity.mcId("end/root"));
		var KILL_DRAGON = dummy(Stellarity.mcId("minecraft:end/kill_dragon"));


		var VOID_REELS = Advancement.Builder.advancement()
			.display(StellarityItems.FISHER_OF_VOIDS,
				Component.translatable("advancements.stellarity.void_reels"),
				Component.translatable("advancements.stellarity.void_reels.description"),
				null,
				TASK,
				true,
				true,
				false
			)
			.parent(ENTER_END_GATEWAY)
			.addCriterion("fishing", VoidFishedTrigger.TriggerInstance.fishedItem(
				/*? > 1.21 {*/ /*Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()
				*//*? } else {*/ItemPredicate.ANY, EntityPredicate.ANY, ItemPredicate.ANY /*? }*/
			)).requirements(
				/*? > 1.21 {*/ /*new AdvancementRequirements(List.of(List.of("fishing")))
				*//*? } else {*/new String[][]{{"fishing"}} /*? }*/
			)
			.build(Stellarity.id("void_fishing/void_reels"));

		var TOPPED_OFF = Advancement.Builder.advancement()
			.display(
				StellarityItems.CRYSTAL_HEARTFISH,
				Component.translatable("advancements.stellarity.topped_off"),
				Component.translatable("advancements.stellarity.topped_off.description"),
				null,
				TASK,
				true,
				true,
				false
			)
			.parent(VOID_REELS)
			.addCriterion("impossible", impossible())
			.build(Stellarity.id("void_fishing/topped_off"));

		var SACRIFICAL_RITUAL = Advancement.Builder.advancement().display(
				Items.END_CRYSTAL,
				Component.translatable("advancements.stellarity.sacrificial_ritual"),
				Component.translatable("advancements.stellarity.sacrificial_ritual.description"),
				null,
				GOAL,
				true,
				true,
				false
			).parent(END_ROOT)
			.addCriterion("summon", DragonSummonedTrigger.TriggerInstance.triggerInstance(1))
			.requirements(
				/*? > 1.21 {*/ /*new AdvancementRequirements(List.of(List.of("summon")))
				*//*? } else {*/new String[][]{{"summon"}} /*? }*/
			).build(Stellarity.id("ender_dragon/sacrificial_ritual"));

		var RESPAWN_DRAGON = Advancement.Builder.advancement().display(
				Items.END_CRYSTAL,
				Component.translatable("advancements.end.respawn_dragon.title"),
				Component.translatable("advancements.end.respawn_dragon.description"),
				null,
				TASK,
				true,
				true,
				false
			).parent(KILL_DRAGON)
			.addCriterion("summon", DragonSummonedTrigger.TriggerInstance.triggerInstance(2))
			.requirements(
				/*? > 1.21 {*/ /*new AdvancementRequirements(List.of(List.of("summon")))
				*//*? } else {*/new String[][]{{"summon"}} /*? }*/
			).build(Stellarity.mcId("end/respawn_dragon"));


		consumer.accept(TOPPED_OFF);
		consumer.accept(VOID_REELS);
		consumer.accept(SACRIFICAL_RITUAL);

	}

	//? < 1.21.1 {
	private CriterionTriggerInstance impossible() {
		return new ImpossibleTrigger.TriggerInstance();
	}

	//?} else {
	/*private Criterion<ImpossibleTrigger.TriggerInstance> impossible() {
		return CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance());
	}
	*///?}
}
