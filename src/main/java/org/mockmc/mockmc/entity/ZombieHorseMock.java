package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.ZombieHorse;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link ZombieHorse}.
 *
 * @see AbstractHorseMock
 */
@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class ZombieHorseMock extends AbstractHorseMock
		implements
			ZombieHorse,
			org.mockmc.mockmc.generated.org.bukkit.entity.ZombieHorseBaseMock
{

	/**
	 * Constructs a new {@link ZombieHorseMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public ZombieHorseMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@Deprecated(since = "1.11")
	public Horse.@NotNull Variant getVariant()
	{
		return Horse.Variant.UNDEAD_HORSE;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.ZOMBIE_HORSE;
	}
}
