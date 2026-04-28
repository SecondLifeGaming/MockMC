package org.mockbukkit.mockbukkit.entity;

import com.google.common.base.Preconditions;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.inventory.meta.FireworkMetaMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Firework}.
 *
 * @see ProjectileMock
 */
@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class FireworkMock extends ProjectileMock
		implements
			Firework,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.FireworkBaseMock
{

	private FireworkMeta meta;

	private boolean shotAtAngle = false;

	/**
	 * Constructs a new {@link FireworkMock} on the provided {@link ServerMock} with
	 * a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public FireworkMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		this(server, uuid, new FireworkMetaMock());
	}

	/**
	 * Constructs a new {@link FireworkMock} on the provided {@link ServerMock} with
	 * a specified {@link UUID} and {@link FireworkMeta}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 * @param meta
	 *            The FireworkMeta to apply.
	 */
	public FireworkMock(@NotNull ServerMock server, @NotNull UUID uuid, @NotNull FireworkMeta meta)
	{
		super(server, uuid);
		this.meta = meta.clone();
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.FIREWORK_ROCKET;
	}

	@Override
	@NotNull
	public FireworkMeta getFireworkMeta()
	{
		return meta;
	}

	@Override
	public void setFireworkMeta(@NotNull FireworkMeta meta)
	{
		Preconditions.checkNotNull(meta, "FireworkMeta cannot be null!");
		this.meta = meta.clone();
	}

	@Override
	public boolean isShotAtAngle()
	{
		return shotAtAngle;
	}

	@Override
	public void setShotAtAngle(boolean shotAtAngle)
	{
		this.shotAtAngle = shotAtAngle;
	}
}
