package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Phantom;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Phantom}.
 *
 * @see FlyingMock
 */
public class PhantomMock extends FlyingMock
		implements
			Phantom,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.PhantomBaseMock
{

	private int size = 0;

	private boolean shouldBurnInDay = true;

	@Nullable
	private UUID spawningEntity = null;

	@NotNull
	private Location anchorLocation = new Location(null, 0, 0, 0);

	/**
	 * Constructs a new {@link PhantomMock} on the provided {@link ServerMock} with
	 * a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public PhantomMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	public int getSize()
	{
		return this.size;
	}

	@Override
	public void setSize(int size)
	{
		this.size = Math.clamp(size, 0, 64);
	}

	@Override
	@Nullable
	public UUID getSpawningEntity()
	{
		return this.spawningEntity;
	}

	/**
	 * Set the UUID of the entity that caused this phantom to spawn.
	 *
	 * @param spawningEntity
	 *            The UUID of the spawning entity.
	 */
	public void setSpawningEntity(@Nullable UUID spawningEntity)
	{
		this.spawningEntity = spawningEntity;
	}

	@Override
	public boolean shouldBurnInDay()
	{
		return this.shouldBurnInDay;
	}

	@Override
	public void setShouldBurnInDay(boolean shouldBurnInDay)
	{
		this.shouldBurnInDay = shouldBurnInDay;
	}

	@Override
	@NotNull
	public Location getAnchorLocation()
	{
		return this.anchorLocation.clone();
	}

	@Override
	public void setAnchorLocation(@NotNull Location location)
	{
		Preconditions.checkArgument(location != null, "location cannot be null");
		this.anchorLocation = location.toBlockLocation();
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.PHANTOM;
	}
}
