package org.mockmc.mockmc.command;

import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.command.brigadier.bukkit.BukkitBrigadierForwardingMapMock;

/**
 * Mock implementation of a {@link CommandMap}.
 */
public class CommandMapMock extends SimpleCommandMap
		implements
			CommandMap,
			org.mockmc.mockmc.generated.server.org.bukkit.command.CommandMapBaseMock
{

	/**
	 * @param server
	 *            The ServerMock this command map is for.
	 */
	@ApiStatus.Internal
	public CommandMapMock(@NotNull ServerMock server)
	{
		super(server, BukkitBrigadierForwardingMapMock.INSTANCE);
	}
}
