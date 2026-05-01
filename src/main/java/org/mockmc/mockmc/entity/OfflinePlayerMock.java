package org.mockmc.mockmc.entity;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.google.common.base.Preconditions;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.ban.ProfileBanList;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.MockMC;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.ban.ProfileBanListMock;
import org.mockmc.mockmc.profile.PlayerProfileMock;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Mock implementation of an {@link OfflinePlayer}. Not interchangeable with
 * {@link PlayerMock}.
 *
 * @see PlayerMock
 */
@SuppressWarnings(
{"deprecation", "unchecked"})
public class OfflinePlayerMock implements org.mockmc.mockmc.generated.server.org.bukkit.OfflinePlayerBaseMock
{

	@NotNull
	private final UUID uuid;

	@Nullable
	private final String name;

	/**
	 * Constructs a new {@link OfflinePlayerMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID} and name.
	 *
	 * @param uuid
	 *            The UUID of the player.
	 * @param name
	 *            The name of the player.
	 */
	public OfflinePlayerMock(@NotNull UUID uuid, @Nullable String name)
	{
		Preconditions.checkNotNull(uuid, "UUID cannot be null");
		this.uuid = uuid;
		this.name = name;
	}

	/**
	 * Constructs a new {@link OfflinePlayerMock} on the provided {@link ServerMock}
	 * with a random {@link UUID} and specified name.
	 *
	 * @param name
	 *            The name of the player.
	 */
	public OfflinePlayerMock(@Nullable String name)
	{
		this(UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes()), name);
	}

	/**
	 * Makes this offline player join the server. A new PlayerMock will be
	 * constructed, and added to the server. Will throw an
	 * {@link IllegalStateException} if the player is already online.
	 *
	 * @param server
	 *            The server to join.
	 * @return The created PlayerMock.
	 */
	@NotNull
	public PlayerMock join(@NotNull ServerMock server)
	{
		Preconditions.checkNotNull(server, "Server cannot be null");
		Preconditions.checkState(!isOnline(), "Player already online");
		PlayerMock player = new PlayerMock(server, this.name, this.uuid);
		server.addPlayer(player);
		return player;
	}

	@Override
	public boolean isOnline()
	{
		return getPlayer() != null;
	}

	@Override
	@Nullable
	public String getName()
	{
		return this.name;
	}

	@Override
	@NotNull
	public UUID getUniqueId()
	{
		return this.uuid;
	}

	@Override
	public boolean isOp()
	{
		return MockMC.getMock().getPlayerList().getOperators().contains(this);
	}

	@Override
	public void setOp(boolean value)
	{
		if (value)
		{
			MockMC.getMock().getPlayerList().addOperator(this.uuid);
		} else
		{
			MockMC.getMock().getPlayerList().removeOperator(this.uuid);
		}
	}

	@Override
	@NotNull
	public Map<String, Object> serialize()
	{
		return Map.of("UUID", this.uuid.toString());
	}

	@Override
	public boolean isBanned()
	{
		MockMC.ensureMocking();
		return ((ProfileBanListMock) Bukkit.getBanList(BanList.Type.PROFILE)).isBanned(getPlayerProfile());
	}

	@Override
	// Paper does it too ¯\_(ツ)_/¯
	@Nullable
	public BanEntry<PlayerProfile> ban(@Nullable String reason, @Nullable Date expires, @Nullable String source)
	{
		MockMC.ensureMocking();
		return ((ProfileBanList) Bukkit.getBanList(BanList.Type.PROFILE)).addBan(this.getPlayerProfile(), reason,
				expires, source);
	}

	@Override
	// Paper does it too ¯\_(ツ)_/¯
	@Nullable
	public BanEntry<PlayerProfile> ban(@Nullable String reason, @Nullable Instant expires, @Nullable String source)
	{
		MockMC.ensureMocking();
		return ((ProfileBanList) Bukkit.getBanList(BanList.Type.PROFILE)).addBan(this.getPlayerProfile(), reason,
				expires, source);
	}

	@Override
	// Paper does it too ¯\_(ツ)_/¯
	@Nullable
	public BanEntry<PlayerProfile> ban(@Nullable String reason, @Nullable Duration duration, @Nullable String source)
	{
		MockMC.ensureMocking();
		return ((ProfileBanList) Bukkit.getBanList(BanList.Type.PROFILE)).addBan(this.getPlayerProfile(), reason,
				duration, source);
	}

	@Override
	public boolean isWhitelisted()
	{
		MockMC.ensureMocking();
		return Bukkit.getWhitelistedPlayers().contains(this);
	}

	@Override
	public void setWhitelisted(boolean value)
	{
		MockMC.ensureMocking();
		Bukkit.getWhitelistedPlayers().add(this);
	}

	@Override
	@Nullable
	public Player getPlayer()
	{
		MockMC.ensureMocking();
		return Bukkit.getPlayer(this.getUniqueId());
	}

	@Override
	public long getFirstPlayed()
	{
		MockMC.ensureMocking();
		return MockMC.getMock().getPlayerList().getFirstPlayed(getUniqueId());
	}

	/**
	 * @deprecated Use {@link #getLastLogin()} or {@link #getLastSeen()} instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	public long getLastPlayed()
	{
		return getLastSeen();
	}

	@Override
	public boolean hasPlayedBefore()
	{
		MockMC.ensureMocking();
		return MockMC.getMock().getPlayerList().hasPlayedBefore(getUniqueId());
	}

	@Override
	@Nullable
	public Location getBedSpawnLocation()
	{
		return getRespawnLocation();
	}

	@Override
	public long getLastLogin()
	{
		MockMC.ensureMocking();
		return MockMC.getMock().getPlayerList().getLastLogin(getUniqueId());
	}

	@Override
	public long getLastSeen()
	{
		MockMC.ensureMocking();
		return MockMC.getMock().getPlayerList().getLastSeen(getUniqueId());
	}

	@Override
	@NotNull
	public PlayerProfile getPlayerProfile()
	{
		// Paper does not cache this.
		return new PlayerProfileMock(this);
	}
}
