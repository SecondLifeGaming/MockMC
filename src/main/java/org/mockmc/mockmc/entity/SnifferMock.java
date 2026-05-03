package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sniffer;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Mock implementation of an {@link Sniffer}.
 *
 * @see AnimalsMock
 * @mockmc.version 1.21-1.0.0
 */
public class SnifferMock extends AnimalsMock
		implements
			org.mockmc.mockmc.generated.server.org.bukkit.entity.SnifferBaseMock
{

	private final List<Location> exploredLocations = new ArrayList<>();

	private State state = State.IDLING;

	private boolean canDig = true;

	/**
	 * Constructs a new {@link Sniffer} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public SnifferMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public Collection<Location> getExploredLocations()
	{
		return Collections.unmodifiableList(exploredLocations);
	}

	@Override
	public void removeExploredLocation(@NotNull Location location)
	{
		Preconditions.checkArgument(location != null, "location cannot be null");
		Location blockPosition = location.toBlockLocation();
		World world = blockPosition.getWorld() == null ? getWorld() : blockPosition.getWorld();
		blockPosition.setWorld(world);
		exploredLocations.remove(blockPosition);
	}

	@Override
	public void addExploredLocation(@NotNull Location location)
	{
		Preconditions.checkArgument(location != null, "location cannot be null");
		if (location.getWorld() != this.getWorld())
		{
			return;
		}
		exploredLocations.add(location.toBlockLocation());
	}

	@Override
	@NotNull
	public State getState()
	{
		return this.state;
	}

	@Override
	public void setState(@NotNull State state)
	{
		Preconditions.checkArgument(state != null, "state cannot be null");
		this.state = state;
		switch (state)
		{
			case FEELING_HAPPY ->
				playSound(Sound.sound(org.bukkit.Sound.ENTITY_SNIFFER_HAPPY, Sound.Source.AMBIENT, 1.0f, 1.0f));
			case SCENTING -> playSound(Sound.sound(org.bukkit.Sound.ENTITY_SNIFFER_SCENTING, Sound.Source.AMBIENT, 1.0f,
					isAdult() ? 1.0f : 1.3F));
			case SNIFFING ->
				playSound(Sound.sound(org.bukkit.Sound.ENTITY_SNIFFER_SNIFFING, Sound.Source.AMBIENT, 1.0f, 1.0f));
			case RISING ->
				playSound(Sound.sound(org.bukkit.Sound.ENTITY_SNIFFER_DIGGING_STOP, Sound.Source.AMBIENT, 1.0f, 1.0f));
			default -> {
				/* No sound is emitted for other states */
			}
		}
	}

	@Override
	public boolean canDig()
	{
		return this.canDig;
	}

	public void setCanDig(boolean canDig)
	{
		this.canDig = canDig;
	}

	@Override
	@NotNull
	public Location findPossibleDigLocation()
	{
		return this.getLocation().add(0, -1, 0);
	}

	@Override
	protected void onApplyNbt(@NotNull org.mockmc.mockmc.util.NbtStateMock nbt)
	{
		if (nbt.has("State"))
		{
			try
			{
				this.setState(State.valueOf((String) nbt.get("State")));
			} catch (IllegalArgumentException _)
			{
				// Ignore invalid states in NBT
			}
		}
		if (nbt.has("ExploredLocations"))
		{
			// TODO: In a real implementation we would parse the list of locations
		}
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.SNIFFER;
	}
}
