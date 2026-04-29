package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import org.bukkit.DyeColor;
import org.bukkit.entity.Cat;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Cat}.
 *
 * @see TameableAnimalMock
 */
public class CatMock extends TameableAnimalMock
		implements
			Cat,
			org.mockmc.mockmc.generated.org.bukkit.entity.CatBaseMock
{

	private SoundVariant soundVariant = SoundVariant.CLASSIC;

	private Type type = Type.CALICO;

	private DyeColor collarColour = DyeColor.RED;

	private boolean isLyingDown = false;

	private boolean isHeadUp = false;

	/**
	 * Constructs a new {@link CatMock} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public CatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public Type getCatType()
	{
		return this.type;
	}

	@Override
	public void setCatType(@NotNull Type type)
	{
		Preconditions.checkNotNull(type, "Type cannot be null");
		this.type = type;
	}

	@Override
	@NotNull
	public SoundVariant getSoundVariant()
	{
		return this.soundVariant;
	}

	@Override
	public void setSoundVariant(@NotNull SoundVariant variant)
	{
		Preconditions.checkArgument(variant != null, "variant cannot be null");
		this.soundVariant = variant;
	}

	@Override
	@NotNull
	public DyeColor getCollarColor()
	{
		return this.collarColour;
	}

	@Override
	public void setCollarColor(@NotNull DyeColor color)
	{
		Preconditions.checkNotNull(color, "Color cannot be null");
		this.collarColour = color;
	}

	@Override
	public void setLyingDown(boolean lyingDown)
	{
		this.isLyingDown = lyingDown;
	}

	@Override
	public boolean isLyingDown()
	{
		return this.isLyingDown;
	}

	@Override
	public void setHeadUp(boolean headUp)
	{
		this.isHeadUp = headUp;
	}

	@Override
	public boolean isHeadUp()
	{
		return this.isHeadUp;
	}

	@Override
	public EntityType getType()
	{
		return EntityType.CAT;
	}
}
