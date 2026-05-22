package org.mockmc.mockmc.ban;

import com.google.common.base.Preconditions;
import com.google.common.net.InetAddresses;
import org.bukkit.BanEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.net.InetAddress;
import java.util.Date;

public class IpBanEntryMock
		implements
			BanEntry<InetAddress>,
			org.mockmc.mockmc.generated.server.org.bukkit.BanEntryBaseMock<InetAddress>
{

	private final String target;

	private String source;

	private String reason;

	private Date created;

	private Date expires;

	public IpBanEntryMock(@NotNull String target, @Nullable String reason, @Nullable Date expires,
			@Nullable String source)
	{
		this.target = target;
		this.source = source;
		this.reason = reason;
		this.created = new Date();
		this.expires = expires;
	}

	@Override
	@Deprecated(since = "1.20")
	@NotNull
	public String getTarget()
	{
		return this.target;
	}

	@Override
	@NotNull
	public InetAddress getBanTarget()
	{
		return InetAddresses.forString(this.target);
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
		Preconditions.checkNotNull(created, "Created date cannot be null");
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
		return this.expires;
	}

	@Override
	public void setExpiration(@Nullable Date expiration)
	{
		this.expires = expiration;
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
