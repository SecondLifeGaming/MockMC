package org.mockmc.mockmc.block.state;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Jukebox;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.inventory.ItemStackMock;

/**
 * Mock implementation of a {@link Jukebox}.
 *
 * @see TileStateMock
 */
public class JukeboxStateMock extends TileStateMock
		implements
			Jukebox,
			org.mockmc.mockmc.generated.org.bukkit.block.JukeboxBaseMock
{

	private ItemStack recordItem;

	private boolean playing;

	/**
	 * Constructs a new {@link JukeboxStateMock} for the provided {@link Material}.
	 * Only supports {@link Material#JUKEBOX}
	 *
	 * @param material
	 *            The material this state is for.
	 */
	public JukeboxStateMock(@NotNull Material material)
	{
		super(material);
		checkType(material, Material.JUKEBOX);
		setRecord(null);
	}

	/**
	 * Constructs a new {@link JukeboxStateMock} for the provided {@link Block}.
	 * Only supports {@link Material#JUKEBOX}
	 *
	 * @param block
	 *            The block this state is for.
	 */
	protected JukeboxStateMock(@NotNull Block block)
	{
		super(block);
		checkType(block, Material.JUKEBOX);
		setRecord(null);
	}

	/**
	 * Constructs a new {@link JukeboxStateMock} by cloning the data from an
	 * existing one.
	 *
	 * @param state
	 *            The state to clone.
	 */
	protected JukeboxStateMock(@NotNull JukeboxStateMock state)
	{
		super(state);
		this.recordItem = state.recordItem;
		this.playing = state.playing;
	}

	@Override
	@NotNull
	public JukeboxStateMock getSnapshot()
	{
		return new JukeboxStateMock(this);
	}

	@Override
	@NotNull
	public JukeboxStateMock copy()
	{
		return new JukeboxStateMock(this);
	}

	@Override
	@NotNull
	public Material getPlaying()
	{
		return this.recordItem.getType();
	}

	@Override
	public void setPlaying(@Nullable Material recordType)
	{
		setRecord(new ItemStackMock(recordType == null ? Material.AIR : recordType));
	}

	@Override
	@NotNull
	public ItemStack getRecord()
	{
		return this.recordItem;
	}

	@Override
	public void setRecord(@Nullable ItemStack recordItem)
	{
		this.recordItem = recordItem == null ? new ItemStackMock(Material.AIR) : recordItem;
		this.playing = !this.recordItem.getType().isAir();
	}

	@Override
	public boolean isPlaying()
	{
		return this.playing;
	}

	@Override
	public void stopPlaying()
	{
		this.playing = false;
	}

	@Override
	public boolean eject()
	{
		if (!isPlaced())
		{
			throw new IllegalStateException("Cannot eject from an unplaced jukebox");
		}
		if (this.getRecord().getType().isAir())
		{
			return false;
		}
		getWorld().dropItem(getLocation().add(0, 1, 0), getRecord());
		setRecord(null);
		return true;
	}

	@Override
	protected String toStringInternal()
	{
		return super.toStringInternal() + ", playing=" + playing + ", recordItem=" + recordItem;
	}
}
