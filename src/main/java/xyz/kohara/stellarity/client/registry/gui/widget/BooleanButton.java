package xyz.kohara.stellarity.client.registry.gui.widget;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
//? > 1.21.9 {
/*import net.minecraft.client.input.MouseButtonEvent;
 *///? }

import java.util.function.Consumer;
import java.util.function.Function;

public class BooleanButton extends Button implements Renderable {
	private final Function<Boolean, Component> textFunction;
	private final Function<Boolean, Tooltip> tooltipFunction;
	private boolean value;
	private final Consumer<BooleanButton> pressCallback;

	public BooleanButton(int i, int j, int k, int l, boolean init, Function<Boolean, Component> textFunction, Function<Boolean, Tooltip> tooltipFunction, Consumer<BooleanButton> onPress) {
		super(i, j, k, l, textFunction.apply(init), (b) -> {
		}, DEFAULT_NARRATION);
		this.value = init;
		this.textFunction = textFunction;
		this.pressCallback = onPress;
		this.tooltipFunction = tooltipFunction;
	}


	public BooleanButton(boolean init, Function<Boolean, Component> textFunction, Function<Boolean, Tooltip> tooltipFunction, Consumer<BooleanButton> onPress) {
		this(0, 0, 310, 20, init, textFunction, tooltipFunction, onPress);
	}

	public BooleanButton(boolean init, Component text, Function<Boolean, Tooltip> tooltipFunction, Consumer<BooleanButton> onPress) {
		this(0, 0, 310, 20, init, options(text), tooltipFunction, onPress);
	}

	public static Function<Boolean, Component> options(Component text) {
		return (b) -> Component.translatable(b ? "options.on.composed" : "options.off.composed", text);
	}


	public boolean setValue(boolean value) {
		this.value = value;
		setMessage(textFunction.apply(value));
		return value;
	}

	@Override
	public void onClick(/*? 1.21.1 {*/double a, double b/*? } else {*//*MouseButtonEvent a, boolean b*//*? }*/) {
		setValue(!value);
		super.onClick(a, b);
		setTooltip(tooltipFunction.apply(value));
		pressCallback.accept(this);
	}


	//? > 1.21.10 {
	/*public void renderContents(GuiGraphics guiGraphics, int i, int j, float f) {
		this.renderDefaultSprite(guiGraphics);
		this.renderDefaultLabel(guiGraphics.textRendererForWidget(this, GuiGraphics.HoveredTextEffects.NONE));
	}
	*///? }
}
