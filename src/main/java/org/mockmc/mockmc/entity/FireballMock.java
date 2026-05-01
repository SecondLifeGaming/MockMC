package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import org.bukkit.entity.Fireball;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Fireball}.
 *
 * @see ProjectileMock
 */
@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class FireballMock extends ProjectileMock
		implements
			Fireball,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.FireballBaseMock
{

	private boolean isIncendiary;

	@NotNull
	private Vector direction = new Vector();

	private float blastYield = 1.0f;

	@NotNull
	private Vector acceleration = new Vector();

	/**
	 * Constructs a new {@link FireballMock} on the provided {@link ServerMock} with
	 * a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public FireballMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	public void setDirection(@NotNull Vector direction)
	{
		Preconditions.checkNotNull(direction, "Direction cannot be null");
		this.direction = direction;
	}

	@Override
	@NotNull
	public Vector getDirection()
	{
		return this.direction;
	}

	@Override
	public void setAcceleration(@NotNull Vector acceleration)
	{
		Preconditions.checkArgument(acceleration != null, "Vector acceleration cannot be null");
		this.acceleration = acceleration;
	}

	@Override
	@NotNull
	public Vector getAcceleration()
	{
		return this.acceleration;
	}

	@Override
	@Deprecated(since = "1.20.6")
	public void setPower(@NotNull Vector power)
	{
		this.setAcceleration(power);
	}

	@Override
	@Deprecated(since = "1.20.6")
	@NotNull
	public Vector getPower()
	{
		return this.getAcceleration();
	}

	@Override
	public void setYield(float blastYield)
	{
		this.blastYield = blastYield;
	}

	@Override
	public float getYield()
	{
		return this.blastYield;
	}

	@Override
	public void setIsIncendiary(boolean isIncendiary)
	{
		this.isIncendiary = isIncendiary;
	}

	@Override
	public boolean isIncendiary()
	{
		return this.isIncendiary;
	}
}
