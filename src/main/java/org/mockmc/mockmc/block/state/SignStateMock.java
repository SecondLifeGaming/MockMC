package org.mockmc.mockmc.block.state;

import com.google.common.base.Preconditions;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.block.sign.SignSide;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;
import java.util.Arrays;
import java.util.List;

/**
 * Mock implementation of a {@link Sign}.
 *
 * @see TileStateMock
 */
public class SignStateMock extends TileStateMock implements org.mockmc.mockmc.generated.org.bukkit.block.SignBaseMock
{

	private final SignSideMock front;

	private final SignSideMock back;

	/**
	 * Constructs a new {@link SignStateMock} for the provided {@link Material}.
	 * Only supports materials in {@link Tag#SIGNS}
	 *
	 * @param material
	 *            The material this state is for.
	 */
	public SignStateMock(@NotNull Material material)
	{
		super(material);
		checkType(material, Tag.ALL_SIGNS);
		this.front = new SignSideMock();
		this.back = new SignSideMock();
	}

	/**
	 * Constructs a new {@link SignStateMock} for the provided {@link Block}. Only
	 * supports materials in {@link Tag#SIGNS}
	 *
	 * @param block
	 *            The block this state is for.
	 */
	protected SignStateMock(@NotNull Block block)
	{
		super(block);
		checkType(block, Tag.ALL_SIGNS);
		this.front = new SignSideMock();
		this.back = new SignSideMock();
	}

	/**
	 * Constructs a new {@link SignStateMock} by cloning the data from an existing
	 * one.
	 *
	 * @param state
	 *            The state to clone.
	 */
	protected SignStateMock(@NotNull SignStateMock state)
	{
		super(state);
		this.front = new SignSideMock(state.front);
		this.back = new SignSideMock(state.back);
	}

	@Override
	@NotNull
	public List<Component> lines()
	{
		return front.lines();
	}

	@Override
	@NotNull
	public Component line(int index) throws IndexOutOfBoundsException
	{
		return front.line(index);
	}

	@Override
	public void line(int index, @NotNull Component line) throws IndexOutOfBoundsException
	{
		front.line(index, line);
	}

	/**
	 * @deprecated since 1.16. Use {@link #lines()} instead.
	 */
	@Override
	@NotNull
	@Deprecated(since = "1.16")
	public String @NotNull [] getLines()
	{
		return front.getLines();
	}

	/**
	 * @deprecated since 1.16. Use {@link #line(int)} instead.
	 */
	@Override
	@Deprecated(since = "1.16")
	@NotNull
	public String getLine(int index) throws IndexOutOfBoundsException
	{
		return front.getLine(index);
	}

	/**
	 * @deprecated since 1.16. Use {@link #line(int, Component)} instead.
	 */
	@Override
	@Deprecated(since = "1.16")
	public void setLine(int index, String line) throws IndexOutOfBoundsException
	{
		front.setLine(index, line);
	}

	@Override
	public boolean isGlowingText()
	{
		return front.isGlowingText();
	}

	@Override
	public void setGlowingText(boolean glowing)
	{
		front.setGlowingText(glowing);
	}

	@Override
	@NotNull
	public DyeColor getColor()
	{
		return front.getColor();
	}

	@Override
	public void setColor(@NotNull DyeColor color)
	{
		front.setColor(color);
	}

	@Override
	@NotNull
	public SignSide getSide(@NotNull Side side)
	{
		return switch (side)
		{
			case FRONT -> front;
			case BACK -> back;
		};
	}

	@Override
	@NotNull
	public SignStateMock getSnapshot()
	{
		return new SignStateMock(this);
	}

	@Override
	@NotNull
	public SignStateMock copy()
	{
		return new SignStateMock(this);
	}

	private static class SignSideMock implements org.mockmc.mockmc.generated.org.bukkit.block.sign.SignSideBaseMock
	{

		private final Component[] lines;

		private boolean glowing = false;

		private DyeColor color = DyeColor.BLACK;

		private SignSideMock()
		{
			this.lines = new Component[4];
			Arrays.fill(lines, Component.empty());
		}

		private SignSideMock(SignSide signSide)
		{
			this.lines = signSide.lines().toArray(Component[]::new);
			this.glowing = signSide.isGlowingText();
			this.color = signSide.getColor();
		}

		@Override
		@NotNull
		public List<Component> lines()
		{
			return List.of(lines);
		}

		@Override
		@NotNull
		public Component line(int index) throws IndexOutOfBoundsException
		{
			if (index < 0 || index >= lines.length)
			{
				throw new IndexOutOfBoundsException("Index out of bounds: " + index);
			}
			return lines[index];
		}

		@Override
		public void line(int index, @NotNull Component line) throws IndexOutOfBoundsException
		{
			Preconditions.checkNotNull(line, "Line cannot be null!");
			if (index < 0 || index >= lines.length)
			{
				throw new IndexOutOfBoundsException("Index out of bounds: " + index);
			}
			lines[index] = line;
		}

		@Override
		@NotNull
		public String[] getLines()
		{
			return Arrays.stream(lines).map(LegacyComponentSerializer.legacySection()::serialize)
					.toArray(String[]::new);
		}

		@Override
		@NotNull
		public String getLine(int index) throws IndexOutOfBoundsException
		{
			if (index < 0 || index >= lines.length)
			{
				throw new IndexOutOfBoundsException("Index out of bounds: " + index);
			}
			return LegacyComponentSerializer.legacySection().serialize(lines[index]);
		}

		// Please note: NullableProblems is suppressed because the method signature
		// requires a non-null String but
		// the implementation allows null values to be set.
		@Override
		public void setLine(int index, @SuppressWarnings("NullableProblems") String line)
				throws IndexOutOfBoundsException
		{
			if (index < 0 || index >= lines.length)
			{
				throw new IndexOutOfBoundsException("Index out of bounds: " + index);
			}
			if (line == null)
			{
				lines[index] = Component.empty();
			} else
			{
				lines[index] = LegacyComponentSerializer.legacySection().deserialize(line);
			}
		}

		@Override
		public boolean isGlowingText()
		{
			return glowing;
		}

		@Override
		public void setGlowingText(boolean glowing)
		{
			this.glowing = glowing;
		}

		@Override
		@NotNull
		public DyeColor getColor()
		{
			return color;
		}

		@Override
		public void setColor(@NotNull DyeColor color)
		{
			Preconditions.checkNotNull(color, "Color cannot be null!");
			this.color = color;
		}

		@Override
		public String toString()
		{
			return "SignSideMock{" + "color=" + color + ", lines=" + Arrays.toString(lines) + ", glowing=" + glowing
					+ '}';
		}

		@Override
		public boolean equals(Object o)
		{
			if (this == o)
			{
				return true;
			}
			if (!(o instanceof SignSideMock that))
			{
				return false;
			}
			return glowing == that.glowing && Arrays.equals(lines, that.lines) && color == that.color;
		}

		@Override
		public int hashCode()
		{
			int result = Objects.hash(glowing, color);
			result = 31 * result + Arrays.hashCode(lines);
			return result;
		}
	}

	@Override
	protected String toStringInternal()
	{
		return super.toStringInternal() + ", back=" + back + ", front=" + front;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof SignStateMock that))
		{
			return false;
		}
		if (!super.equals(o))
		{
			return false;
		}
		return Objects.equals(front, that.front) && Objects.equals(back, that.back);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), front, back);
	}
}
