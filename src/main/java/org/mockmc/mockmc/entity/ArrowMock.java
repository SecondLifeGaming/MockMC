package org.mockmc.mockmc.entity;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.potion.PotionUtils;
import java.util.UUID;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class ArrowMock extends AbstractArrowMock
		implements
			Arrow,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.ArrowBaseMock
{

	private PotionType potionType;

	/**
	 * Constructs a new {@link ArrowMock} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public ArrowMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	public void setBasePotionData(@Nullable PotionData data)
	{
		setBasePotionType(PotionUtils.fromBukkit(data));
	}

	@Override
	@Nullable
	public PotionData getBasePotionData()
	{
		return PotionUtils.toBukkit(getBasePotionType());
	}

	@Override
	public void setBasePotionType(@Nullable PotionType type)
	{
		this.potionType = type;
	}

	@Override
	@Nullable
	public PotionType getBasePotionType()
	{
		return this.potionType;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.ARROW;
	}
}
