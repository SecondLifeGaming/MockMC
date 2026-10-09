package io.papermc.paper.datacomponent.item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import org.bukkit.DyeColor;
import org.jspecify.annotations.NullMarked;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NullMarked
@SuppressWarnings(
{"NonExtendableApiUsage", "UnstableApiUsage"})
public record SignTextMock(List<Component> lines, DyeColor color, boolean hasGlowingText) implements SignText
{

	public SignTextMock
	{
		lines = List.copyOf(lines);
	}

	@Override
	public Builder toBuilder()
	{
		return new BuilderMock().lines(lines).color(color).hasGlowingText(hasGlowingText);
	}

	static class BuilderMock implements Builder
	{

		private final List<Component> lines = new ArrayList<>(Collections.nCopies(4, Component.empty()));
		private DyeColor color = DyeColor.BLACK;
		private boolean hasGlowingText = false;

		@Override
		public Builder lines(List<? extends ComponentLike> lines)
		{
			this.lines.clear();
			for (ComponentLike cl : lines)
			{
				this.lines.add(cl.asComponent());
			}
			return this;
		}

		@Override
		public Builder line(int index, ComponentLike line)
		{
			while (this.lines.size() <= index)
			{
				this.lines.add(Component.empty());
			}
			this.lines.set(index, line.asComponent());
			return this;
		}

		@Override
		public Builder color(DyeColor color)
		{
			this.color = color;
			return this;
		}

		@Override
		public Builder hasGlowingText(boolean hasGlowingText)
		{
			this.hasGlowingText = hasGlowingText;
			return this;
		}

		@Override
		public SignText build()
		{
			return new SignTextMock(lines, color, hasGlowingText);
		}

	}

}
