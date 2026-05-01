package org.mockmc.mockmc.block.state;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Crafter;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.inventory.CrafterInventoryMock;
import org.mockmc.mockmc.inventory.InventoryMock;

/**
 * Mock implementation of a {@link Crafter}.
 *
 * @see ContainerStateMock
 */
public class CrafterStateMock extends ContainerStateMock
		implements
			Crafter,
			org.mockmc.mockmc.generated.server.org.bukkit.block.CrafterBaseMock
{

	private final boolean[] disabledSlots = new boolean[9];

	private boolean triggered = false;

	public CrafterStateMock(@NotNull Material material)
	{
		super(material);
	}

	protected CrafterStateMock(@NotNull Block block)
	{
		super(block);
	}

	protected CrafterStateMock(@NotNull CrafterStateMock state)
	{
		super(state);
		System.arraycopy(state.disabledSlots, 0, this.disabledSlots, 0, 9);
		this.triggered = state.triggered;
	}

	@Override
	@NotNull
	protected InventoryMock createInventory()
	{
		return new CrafterInventoryMock(this);
	}

	@Override
	@NotNull
	public CrafterStateMock getSnapshot()
	{
		return new CrafterStateMock(this);
	}

	@Override
	@NotNull
	public CrafterStateMock copy()
	{
		return new CrafterStateMock(this);
	}

	@Override
	public boolean isSlotDisabled(int i)
	{
		return this.disabledSlots[i];
	}

	@Override
	public void setSlotDisabled(int i, boolean b)
	{
		this.disabledSlots[i] = b;
	}

	@Override
	public boolean isTriggered()
	{
		return this.triggered;
	}

	@Override
	public void setTriggered(boolean b)
	{
		this.triggered = b;
	}
}
