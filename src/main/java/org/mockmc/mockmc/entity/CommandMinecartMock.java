package org.mockmc.mockmc.entity;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.minecart.CommandMinecart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.ServerMock;
import java.util.Objects;
import java.util.UUID;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class CommandMinecartMock extends MinecartMock
		implements
			CommandMinecart,
			org.mockmc.mockmc.generated.org.bukkit.entity.minecart.CommandMinecartBaseMock
{

	private String command = "";

	private int successCount;

	/**
	 * Constructs a new {@link CommandMinecartMock} on the provided
	 * {@link ServerMock} with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public CommandMinecartMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public String getCommand()
	{
		return this.command;
	}

	@Override
	public void setCommand(@Nullable String command)
	{
		this.command = Objects.requireNonNullElse(command, "");
		this.successCount = 0;
	}

	@Override
	public int getSuccessCount()
	{
		return this.successCount;
	}

	@Override
	public void setSuccessCount(int successCount)
	{
		this.successCount = successCount;
	}

	@Override
	@NotNull
	public Material getMinecartMaterial()
	{
		return Material.COMMAND_BLOCK_MINECART;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.COMMAND_BLOCK_MINECART;
	}
}
