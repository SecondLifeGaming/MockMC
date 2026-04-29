package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

public class ZombieVillagerMock extends ZombieMock
		implements
			ZombieVillager,
			org.mockmc.mockmc.generated.org.bukkit.entity.ZombieVillagerBaseMock
{

	private Villager.Type type = Villager.Type.PLAINS;

	private Villager.Profession profession = Villager.Profession.NONE;

	private OfflinePlayer conversionStarter = null;

	/**
	 * Constructs a new {@link ZombieMock} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public ZombieVillagerMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@NotNull
	@Override
	public Villager.Type getVillagerType()
	{
		return type;
	}

	@Override
	public void setVillagerType(@NotNull Villager.Type type)
	{
		Preconditions.checkNotNull(type);
		this.type = type;
	}

	@Override
	@Nullable
	public OfflinePlayer getConversionPlayer()
	{
		return conversionStarter;
	}

	@Override
	public void setConversionPlayer(@Nullable OfflinePlayer offlinePlayer)
	{
		Preconditions.checkNotNull(offlinePlayer);
		this.conversionStarter = offlinePlayer;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.ZOMBIE_VILLAGER;
	}

	@Override
	public void setVillagerProfession(@NotNull Villager.Profession profession)
	{
		Preconditions.checkNotNull(profession);
		this.profession = profession;
	}

	@Override
	@NotNull
	public Villager.Profession getVillagerProfession()
	{
		return profession;
	}
}
