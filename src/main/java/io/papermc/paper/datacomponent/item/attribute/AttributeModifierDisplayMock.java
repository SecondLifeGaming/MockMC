package io.papermc.paper.datacomponent.item.attribute;

import net.kyori.adventure.text.Component;
import org.jspecify.annotations.NullMarked;

@NullMarked
@SuppressWarnings(
{"NonExtendableApiUsage", "UnstableApiUsage"})
public final class AttributeModifierDisplayMock implements AttributeModifierDisplay
{

	private AttributeModifierDisplayMock()
	{
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	record HiddenMock() implements Hidden
	{
	}

	record DefaultMock() implements Default
	{
	}

	record OverrideTextMock(Component text) implements OverrideText
	{
	}

}
