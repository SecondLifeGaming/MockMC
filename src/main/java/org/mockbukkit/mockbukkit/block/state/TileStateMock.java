package org.mockbukkit.mockbukkit.block.state;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.persistence.PersistentDataContainerMock;
import java.util.Objects;

/**
 * Mock implementation of a {@link TileState}.
 *
 * @see BlockStateMock
 */
public abstract class TileStateMock extends BlockStateMock
		implements
			org.mockbukkit.mockbukkit.generated.org.bukkit.block.TileStateBaseMock
{

	@NotNull
	private final PersistentDataContainerMock container;

	/**
	 * Constructs a new {@link SculkCatalystStateMock} for the provided
	 * {@link Material}.
	 *
	 * @param material
	 *            The material this state is for.
	 */
	protected TileStateMock(@NotNull Material material)
	{
		super(material);
		this.container = new PersistentDataContainerMock();
	}

	/**
	 * Constructs a new {@link SculkCatalystStateMock} for the provided
	 * {@link Block}.
	 *
	 * @param block
	 *            The block this state is for.
	 */
	protected TileStateMock(@NotNull Block block)
	{
		super(block);
		this.container = new PersistentDataContainerMock();
	}

	/**
	 * Constructs a new {@link SculkCatalystStateMock} by cloning the data from an
	 * existing one.
	 *
	 * @param state
	 *            The state to clone.
	 */
	protected TileStateMock(@NotNull TileStateMock state)
	{
		super(state);
		this.container = new PersistentDataContainerMock(state.container);
	}

	@Override
	@NotNull
	public PersistentDataContainer getPersistentDataContainer()
	{
		return container;
	}

	@Override
	@NotNull
	public abstract TileStateMock getSnapshot();

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof TileStateMock that))
		{
			return false;
		}
		if (!super.equals(o))
		{
			return false;
		}
		return Objects.equals(container, that.container);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), container);
	}

	@Override
	protected String toStringInternal()
	{
		return super.toStringInternal() + ", container=" + container;
	}
}
