package xyz.kohara.stellarity.client.registry.gui.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import xyz.kohara.stellarity.client.registry.gui.widget.SliderButton;
import xyz.kohara.stellarity.utils.StellarityConfig;

import java.util.List;

@Environment(EnvType.CLIENT)
public class ConfigScreen extends Screen {

	private final SliderButton dragonHealth;
	private static final Tooltip DRAGON_HEALTH_DESC = Tooltip.create(Component.translatable("stellarity.config.dragon_health.description"));
	private static final Tooltip ENABLE_END_CRYSTAL_DROP_DESC = Tooltip.create(Component.translatable("stellarity.config.enable_end_crystal_drop.description"));
	private static final Tooltip ALWAYS_GENERATE_EGG_DESC = Tooltip.create(Component.translatable("stellarity.config.always_generate_egg.description"));
	private static final Tooltip ENABLE_TOTEM_VOID_SAVING_DESC = Tooltip.create(Component.translatable("stellarity.config.enable_totem_void_saving.description"));
	private static final Tooltip JOIN_MESSAGE_DESC = Tooltip.create(Component.translatable("stellarity.config.join_message.description"));


	@Override
	public boolean isPauseScreen() {
		return false;
	}

	public ConfigScreen(StellarityConfig config) {
		super(Component.translatable("stellarity.config.title"));

		this.dragonHealth = new SliderButton(1, 10000, (d) -> Component.translatable("stellarity.config.dragon_health.title", d.intValue()), (d) -> DRAGON_HEALTH_DESC, config.getDragonHealth(), (d) -> {
		});
	}

	@Override
	public List<? extends GuiEventListener> children() {
		return List.of(dragonHealth);
	}

	@Override
	public void render(GuiGraphics guiGraphics, int i, int j, float f) {
		super.render(guiGraphics, i, j, f);

		int p = 0;
		int q = this.width / 2 - 155;
		dragonHealth.setPosition(q, 40 + p);
		dragonHealth.render(guiGraphics, i, j, f);

		guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 16777215);

	}
}
