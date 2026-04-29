package org.mockmc.mockmc.matcher.plugin;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;

/**
 * An event constructor can always change, let's instead have a custom event
 * that does not change constructor.
 */
@ExtendWith(MockMCExtension.class)
class AnEvent extends Event
{

	private static final HandlerList handlers = new HandlerList();

	@Override
	public @NotNull HandlerList getHandlers()
	{
		return handlers;
	}

	public static @NotNull HandlerList getHandlerList()
	{
		return handlers;
	}

}
