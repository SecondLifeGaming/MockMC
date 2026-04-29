package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Parrot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

public class ParrotMock extends AnimalsMock
		implements
			Parrot,
			org.mockmc.mockmc.generated.org.bukkit.entity.ParrotBaseMock
{

	@NotNull
	private Parrot.Variant variant = Variant.RED;

	private boolean isSitting = false;

	private boolean isTamed = false;

	private AnimalTamer animalTamer = null;

	/**
	 * Constructs a new {@link ParrotMock} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public ParrotMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
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
	public boolean isTamed()
	{
		return this.isTamed;
	}

	@Override
	public void setTamed(boolean tame)
	{
		this.isTamed = tame;
	}

	@Override
	@Nullable
	public UUID getOwnerUniqueId()
	{
		return this.animalTamer.getUniqueId();
	}

	@Override
	@Nullable
	public AnimalTamer getOwner()
	{
		return this.animalTamer;
	}

	@Override
	public void setOwner(@Nullable AnimalTamer tamer)
	{
		this.animalTamer = tamer;
	}

	@Override
	public boolean isSitting()
	{
		return this.isSitting;
	}

	@Override
	public void setSitting(boolean sitting)
	{
		this.isSitting = sitting;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.PARROT;
	}
}
