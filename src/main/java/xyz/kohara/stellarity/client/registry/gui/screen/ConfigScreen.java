package xyz.kohara.stellarity.client.registry.gui.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import xyz.kohara.stellarity.client.registry.gui.widget.BooleanButton;
import xyz.kohara.stellarity.client.registry.gui.widget.SliderButton;
import xyz.kohara.stellarity.utils.StellarityConfig;

import java.util.List;

@Environment(EnvType.CLIENT)
public class ConfigScreen extends Screen {

	private final SliderButton dragonHealth;
	private final BooleanButton enableEndCrystalDrop;
	private final BooleanButton joinMessage;
	private final BooleanButton alwaysGenerateEgg;
	private final BooleanButton enableTotemVoidSaving;

	private final List<AbstractWidget> configWidgets;

	private static final Tooltip DRAGON_HEALTH_DESC = Tooltip.create(Component.translatable("stellarity.config.dragon_health.description"));
	private static final Tooltip ENABLE_END_CRYSTAL_DROP_DESC = Tooltip.create(Component.translatable("stellarity.config.enable_end_crystal_drop.description"));
	private static final Tooltip ALWAYS_GENERATE_EGG_DESC = Tooltip.create(Component.translatable("stellarity.config.always_generate_egg.description"));
	private static final Tooltip ENABLE_TOTEM_VOID_SAVING_DESC = Tooltip.create(Component.translatable("stellarity.config.enable_totem_void_saving.description"));
	private static final Tooltip JOIN_MESSAGE_DESC = Tooltip.create(Component.translatable("stellarity.config.join_message.description"));

	private final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this);

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	public ConfigScreen(StellarityConfig config) {
		super(Component.translatable("stellarity.config.title"));

		this.dragonHealth = new SliderButton(1, 10000, (d) -> Component.translatable("stellarity.config.dragon_health.title", d.intValue()), (d) -> DRAGON_HEALTH_DESC, config.getDragonHealth(), (b) -> {
		});
		this.enableEndCrystalDrop = new BooleanButton(config.isEnableEndCrystalDrop(), Component.translatable("stellarity.config.enable_end_crystal_drop.title"), (b) -> ENABLE_END_CRYSTAL_DROP_DESC, (b) -> {
		});
		this.joinMessage = new BooleanButton(config.isJoinMessage(), Component.translatable("stellarity.config.join_message.title"), (b) -> JOIN_MESSAGE_DESC, (b) -> {
		});
		this.alwaysGenerateEgg = new BooleanButton(config.isAlwaysGenerateEgg(), Component.translatable("stellarity.config.always_generate_egg.title"), (b) -> ALWAYS_GENERATE_EGG_DESC, (b) -> {
		});
		this.enableTotemVoidSaving = new BooleanButton(config.isEnableTotemVoidSaving(), Component.translatable("stellarity.config.enable_totem_void_saving.title"), (b) -> ENABLE_TOTEM_VOID_SAVING_DESC, (b) -> {
		});

		configWidgets = List.of(dragonHealth, enableEndCrystalDrop, joinMessage, alwaysGenerateEgg, enableTotemVoidSaving);
	}

	@Override
	protected void init() {
		super.init();
		layout.addToHeader(new StringWidget(getTitle(), this.font));
		for (AbstractWidget widget : configWidgets) {
			layout.addToContents(widget);
		}
	}

	@Override
	public List<? extends GuiEventListener> children() {
		return configWidgets;
	}


	@Override
	public void render(GuiGraphics guiGraphics, int i, int j, float f) {

		this.layout.arrangeElements();
		int y = layout.getHeaderHeight();
		for (AbstractWidget widget : configWidgets) {
			widget.setY(y);
			y += widget.getHeight() + 5;
		}

		super.render(guiGraphics, i, j, f);
		//? 1.20.1
		this.renderBackground(guiGraphics);

		layout.visitWidgets(widget -> widget.render(guiGraphics, i, j, f));
	}

}
