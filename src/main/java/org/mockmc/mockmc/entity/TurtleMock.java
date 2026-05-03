package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Turtle;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class TurtleMock extends AnimalsMock
		implements
			Turtle,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.TurtleBaseMock
{

	private Location home = new Location(null, 0, 0, 0);

	private boolean hasEgg = false;

	/**
	 * Constructs a new @{{@link TurtleMock}} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public TurtleMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	public boolean hasEgg()
	{
		return this.hasEgg;
	}

	@Override
	@NotNull
	public Location getHome()
	{
		return this.home;
	}

	@Override
	public void setHome(@NotNull Location location)
	{
		Preconditions.checkArgument(location != null, "Location cannot be null");
		this.home = location;
	}

	@Override
	public void setHasEgg(boolean hasEgg)
	{
		this.hasEgg = hasEgg;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.TURTLE;
	}
}
