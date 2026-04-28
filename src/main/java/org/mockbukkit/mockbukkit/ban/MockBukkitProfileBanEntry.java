package org.mockbukkit.mockbukkit.ban;

import com.google.common.base.Preconditions;
import org.bukkit.BanEntry;
import org.bukkit.profile.PlayerProfile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Date;

@SuppressWarnings(
{"deprecation"})
public class MockBukkitProfileBanEntry
		implements
			BanEntry<PlayerProfile>,
			org.mockbukkit.mockbukkit.generated.org.bukkit.BanEntryBaseMock<PlayerProfile>
{

	private final PlayerProfile target;

	private Date created;

	private String source;

	private Date expiration;

	private String reason;

	public MockBukkitProfileBanEntry(PlayerProfile target, String source, Date expiration, String reason)
	{
		this.target = target;
		this.created = new Date();
		this.source = source;
		this.expiration = expiration;
		this.reason = reason;
	}

	@Override
	@Deprecated(since = "1.20")
	@NotNull
	public String getTarget()
	{
		return this.target.getName() != null ? this.target.getName() : "";
	}

	@Override
	@NotNull
	public PlayerProfile getBanTarget()
	{
		return this.target;
	}

	@Override
	@NotNull
	public Date getCreated()
	{
		return this.created;
	}

	@Override
	public void setCreated(@NotNull Date created)
	{
		Preconditions.checkNotNull(created, "Creation Date cannot be null");
		this.created = created;
	}

	@Override
	@NotNull
	public String getSource()
	{
		return this.source;
	}

	@Override
	public void setSource(@NotNull String source)
	{
		Preconditions.checkNotNull(source, "Source cannot be null");
		this.source = source;
	}

	@Override
	@Nullable
	public Date getExpiration()
	{
		return this.expiration;
	}

	@Override
	public void setExpiration(@Nullable Date expiration)
	{
		this.expiration = expiration;
	}

	@Override
	@Nullable
	public String getReason()
	{
		return this.reason;
	}

	@Override
	public void setReason(@Nullable String reason)
	{
		this.reason = reason;
	}

}
