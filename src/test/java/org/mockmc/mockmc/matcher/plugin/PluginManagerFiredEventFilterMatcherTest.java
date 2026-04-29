package org.mockmc.mockmc.matcher.plugin;

import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.plugin.PluginManagerMock;
import org.mockmc.testutils.matcher.AbstractMatcherTest;

import static org.mockmc.mockmc.matcher.plugin.PluginManagerFiredEventFilterMatcher.hasFiredFilteredEvent;

@ExtendWith(MockMCExtension.class)
class PluginManagerFiredEventFilterMatcherTest extends AbstractMatcherTest
{

	@MockMCInject
	private ServerMock serverMock;
	private PluginManagerMock pluginManager;

	@BeforeEach
	void setUp()
	{
		this.pluginManager = serverMock.getPluginManager();
	}

	@Test
	void hasFiredEvent_matches()
	{
		pluginManager.callEvent(new AnEvent());
		assertMatches(hasFiredFilteredEvent(AnEvent.class, ignored -> true), pluginManager);
	}

	@Test
	void hasFiredEvent_noEventFired()
	{
		assertDoesNotMatch(hasFiredFilteredEvent(AnEvent.class, ignored -> true), pluginManager);
	}

	@Test
	void hasFiredEvent_filteredOut()
	{
		pluginManager.callEvent(new AnEvent());
		assertDoesNotMatch(hasFiredFilteredEvent(AnEvent.class, ignored -> false), pluginManager);
	}

	@Test
	void hasFiredEvent_differentClass()
	{
		pluginManager.callEvent(new AnEvent());
		assertDoesNotMatch(hasFiredFilteredEvent(AsyncPlayerPreLoginEvent.class, ignored -> true), pluginManager);
	}

	@Test
	void nullSafe()
	{
		testIsNullSafe();
	}

	@Override
	protected Matcher<?> createMatcher()
	{
		return hasFiredFilteredEvent(AsyncPlayerPreLoginEvent.class, ignored -> true);
	}

}
