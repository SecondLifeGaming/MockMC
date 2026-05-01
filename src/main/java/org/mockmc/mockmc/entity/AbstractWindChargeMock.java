package org.mockmc.mockmc.entity;

import org.bukkit.entity.AbstractWindCharge;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link AbstractWindCharge}.
 *
 * @see FireballMock
 */
public abstract class AbstractWindChargeMock extends FireballMock
		implements
			AbstractWindCharge,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.AbstractWindChargeBaseMock
{

	/**
	 * Constructs a new {@link AbstractWindChargeMock} on the provided
	 * {@link ServerMock} with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	protected AbstractWindChargeMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}
}
