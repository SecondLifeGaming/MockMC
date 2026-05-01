package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of an {@link ExperienceOrb}.
 *
 * @see EntityMock
 */
public class ExperienceOrbMock extends EntityMock
		implements
			ExperienceOrb,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.ExperienceOrbBaseMock
{

	private int experience;

	/**
	 * Constructs a new {@link ExperienceOrbMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public ExperienceOrbMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		this(server, uuid, 0);
	}

	/**
	 * Constructs a new {@link ExperienceOrbMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID} an experience amount.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 * @param experience
	 *            The amount of experience the orb has.
	 */
	public ExperienceOrbMock(@NotNull ServerMock server, @NotNull UUID uuid, int experience)
	{
		super(server, uuid);
		this.experience = experience;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.EXPERIENCE_ORB;
	}

	@Override
	public int getExperience()
	{
		return experience;
	}

	@Override
	public void setExperience(int value)
	{
		this.experience = value;
	}

}
