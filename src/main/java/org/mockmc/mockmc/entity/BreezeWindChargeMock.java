package org.mockmc.mockmc.entity;

import org.bukkit.entity.BreezeWindCharge;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link BreezeWindCharge}.
 *
 * @see AbstractWindChargeMock
 */
public class BreezeWindChargeMock extends AbstractWindChargeMock
		implements
			BreezeWindCharge,
			org.mockmc.mockmc.generated.org.bukkit.entity.BreezeWindChargeBaseMock
{

	/**
	 * Constructs a new {@link BreezeWindChargeMock} on the provided
	 * {@link ServerMock} with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public BreezeWindChargeMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	public void explode()
	{
		if (!isInWorld())
		{
			return;
		}
		getWorld().createExplosion(this, getLocation(), 3.0F, false);
		remove();
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.BREEZE_WIND_CHARGE;
	}
}
