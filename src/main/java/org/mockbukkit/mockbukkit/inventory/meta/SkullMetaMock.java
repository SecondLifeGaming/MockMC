package org.mockbukkit.mockbukkit.inventory.meta;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.codehaus.plexus.util.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockbukkit.mockbukkit.entity.OfflinePlayerMock;
import org.mockbukkit.mockbukkit.inventory.SerializableMeta;
import org.mockbukkit.mockbukkit.inventory.serializer.SerializationUtils;
import org.mockbukkit.mockbukkit.profile.PlayerProfileMock;
import java.util.Map;
import java.util.UUID;

/**
 * Mock implementation of a {@link SkullMeta}.
 *
 * @see ItemMetaMock
 */
@DelegateDeserialization(SerializableMeta.class)
@SuppressWarnings("unchecked")
public class SkullMetaMock extends ItemMetaMock
		implements
			org.mockbukkit.mockbukkit.generated.org.bukkit.inventory.meta.SkullMetaBaseMock
{

	private static final int MAX_OWNER_LENGTH = 16;

	@Nullable
	private com.destroystokyo.paper.profile.PlayerProfile playerProfile;

	/**
	 * Constructs a new {@link SkullMetaMock}.
	 */
	public SkullMetaMock()
	{
		super();
	}

	/**
	 * Constructs a new {@link SkullMetaMock}, cloning the data from another.
	 *
	 * @param meta
	 *            The meta to clone.
	 */
	public SkullMetaMock(@NotNull ItemMeta meta)
	{
		super(meta);
		if (meta instanceof SkullMeta skullMeta)
		{
			this.playerProfile = skullMeta.getPlayerProfile();
		}
	}

	@Override
	@SuppressWarnings(
	{"MethodDoesntCallSuperMethod", "java:S2975", "java:S1182"})
	@NotNull
	public SkullMetaMock clone()
	{
		return new SkullMetaMock(this);
	}

	@Override
	@Deprecated(since = "1.13")
	public String getOwner()
	{
		return hasOwner() ? playerProfile.getName() : null;
	}

	@Override
	public boolean hasOwner()
	{
		return playerProfile != null && !StringUtils.isEmpty(playerProfile.getName());
	}

	@Override
	@Deprecated(since = "1.13")
	public boolean setOwner(String name)
	{
		if (name != null && name.length() > MAX_OWNER_LENGTH)
		{
			return false;
		}
		if (name == null)
		{
			setPlayerProfile(null);
		} else
		{
			Player player = Bukkit.getPlayer(name);
			if (player != null)
			{
				setPlayerProfile(player.getPlayerProfile());
			} else
			{
				setPlayerProfile(new PlayerProfileMock(name, new UUID(0L, 0L)));
			}
		}
		return true;
	}

	@Override
	public void setPlayerProfile(com.destroystokyo.paper.profile.@Nullable PlayerProfile profile)
	{
		this.playerProfile = profile;
	}

	@Override
	public com.destroystokyo.paper.profile.@Nullable PlayerProfile getPlayerProfile()
	{
		return playerProfile;
	}

	@Override
	public OfflinePlayer getOwningPlayer()
	{
		if (hasOwner())
		{
			return new OfflinePlayerMock(playerProfile.getName());
		}
		return null;
	}

	@Override
	public boolean setOwningPlayer(@Nullable OfflinePlayer owner)
	{
		if (owner == null)
		{
			setPlayerProfile(null);
		} else
		{
			setPlayerProfile(new PlayerProfileMock(owner.getName(), owner.getUniqueId()));
		}
		return true;
	}

	@Override
	@Deprecated(since = "1.18")
	public PlayerProfile getOwnerProfile()
	{
		if (!hasOwner())
		{
			return null;
		}
		return new PlayerProfileMock(playerProfile);
	}

	@Override
	@Deprecated(since = "1.18")
	public void setOwnerProfile(@Nullable PlayerProfile profile)
	{
		if (profile == null)
		{
			setPlayerProfile(null);
		} else
		{
			setPlayerProfile(new PlayerProfileMock(profile.getName(), profile.getUniqueId()));
		}
	}

	/**
	 * Required method for Bukkit deserialization.
	 *
	 * @param args
	 *            A serialized SkullMetaMock object in a Map&lt;String, Object&gt;
	 *            format.
	 * @return A new instance of the SkullMetaMock class.
	 */
	@NotNull
	public static SkullMetaMock deserialize(@NotNull Map<String, Object> args)
	{
		SkullMetaMock serialMock = new SkullMetaMock();
		serialMock.deserializeInternal(args);
		serialMock.playerProfile = PlayerProfileMock.deserialize((Map<String, Object>) args.get("player-profile"));
		return serialMock;
	}

	/**
	 * Serializes the properties of an SkullMetaMock to a HashMap. Unimplemented
	 * properties are not present in the map.
	 *
	 * @return A HashMap of String, Object pairs representing the SkullMetaMock.
	 */
	@Override
	@NotNull
	public Map<String, Object> serialize()
	{
		final Map<String, Object> serialized = super.serialize();
		if (playerProfile != null)
		{
			serialized.put("player-profile", SerializationUtils.serialize(playerProfile));
		}
		return serialized;
	}

	@Override
	protected String getTypeName()
	{
		return "SKULL";
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false;
		}
		SkullMetaMock that = (SkullMetaMock) obj;
		if (!super.equals(obj))
		{
			return false;
		}
		return java.util.Objects.equals(playerProfile, that.playerProfile);
	}

	@Override
	public int hashCode()
	{
		return java.util.Objects.hash(super.hashCode(), playerProfile);
	}
}
