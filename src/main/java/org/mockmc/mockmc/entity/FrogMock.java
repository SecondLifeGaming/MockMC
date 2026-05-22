package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Frog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Frog}.
 *
 * @see AnimalsMock
 */
public class FrogMock extends AnimalsMock
		implements
			Frog,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.FrogBaseMock
{

	@Nullable
	private Entity tongueTarget = null;

	private Variant variant = Variant.TEMPERATE;

	/**
	 * Constructs a new {@link FrogMock} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public FrogMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@Nullable
	public Entity getTongueTarget()
	{
		return this.tongueTarget;
	}

	@Override
	public void setTongueTarget(@Nullable Entity target)
	{
		this.tongueTarget = target;
	}

	@Override
	@NotNull
	public Variant getVariant()
	{
		return this.variant;
	}

	@Override
	public void setVariant(@NotNull Variant variant)
	{
		Preconditions.checkNotNull(variant, "Variant cannot be null");
		this.variant = variant;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.FROG;
	}
}
