package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Cow}.
 *
 * @see AnimalsMock
 */
public class CowMock extends AbstractCowMock
		implements
			Cow,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.CowBaseMock
{

	private SoundVariant soundVariant = SoundVariant.CLASSIC;

	@NotNull
	private Variant variant = Variant.TEMPERATE;

	/**
	 * Constructs a new {@link CowMock} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public CowMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.COW;
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
		Preconditions.checkArgument(variant != null, "Variant cannot be null");
		this.variant = variant;
	}

	@Override
	public SoundVariant getSoundVariant()
	{
		return this.soundVariant;
	}

	@Override
	public void setSoundVariant(@NonNull SoundVariant variant)
	{
		Preconditions.checkArgument(variant != null, "variant cannot be null");
		this.soundVariant = variant;
	}
}
