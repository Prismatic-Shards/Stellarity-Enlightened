package xyz.kohara.stellarity.client.registry.gui.widget;

import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
//? > 1.21.10 {
/*import net.minecraft.client.input.MouseButtonEvent;
	*///? }

import java.util.function.Consumer;
import java.util.function.Function;

public class SliderButton extends AbstractSliderButton implements Renderable {
	private final Function<Double, Component> messageFunction;
	private final Function<Double, Tooltip> tooltipFunction;
	private final Consumer<SliderButton> onValueChange;
	private final double min;
	private final double max;
	private double scaledValue;

	public SliderButton(double min, double max, Function<Double, Component> title, Function<Double, @Nullable Tooltip> tooltip, double d, Consumer<SliderButton> onValueChange) {
		this(min, max, 310, 20, title, tooltip, d, onValueChange);
	}


	public SliderButton(double min, double max, int width, int height, Function<Double, Component> message, Function<Double, @Nullable Tooltip> tooltip, double d, Consumer<SliderButton> onValueChange) {
		super(0, 0, width, height, message.apply(d), (d - min) / (max - min));
		this.messageFunction = message;
		this.tooltipFunction = tooltip;
		this.onValueChange = onValueChange;
		this.min = min;
		this.max = max;
		this.scaledValue = d;
	}

	public double getScaledValue() {
		return scaledValue;
	}

	@Override
	protected void setValue(double d) {

		double e = this.value;
		this.value = Mth.clamp(d, 0.0, 1.0);
		scaledValue = Mth.clamp(value * (max - min) + min, min, max);
		if (e != this.value) {
			onValueChange.accept(this);
		}

		setMessage(messageFunction.apply(scaledValue));
		setTooltip(tooltipFunction.apply(scaledValue));

	}

	@Override
	protected void updateMessage() {

	}

	@Override
	protected void applyValue() {

	}

}
