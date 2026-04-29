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

import static org.mockmc.mockmc.matcher.plugin.PluginManagerFiredEventClassMatcher.hasFiredEventInstance;

@ExtendWith(MockMCExtension.class)
class PluginManagerFiredEventClassMatcherTest extends AbstractMatcherTest
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
		this.pluginManager.callEvent(new AnEvent());
		assertMatches(hasFiredEventInstance(AnEvent.class), pluginManager);
	}

	@Test
	void hasFiredEvent_noEventFired()
	{
		assertDoesNotMatch(hasFiredEventInstance(AnEvent.class), pluginManager);
	}

	@Test
	void hasFiredEvent_differentClass()
	{
		pluginManager.callEvent(new AnEvent());
		assertDoesNotMatch(hasFiredEventInstance(AsyncPlayerPreLoginEvent.class), pluginManager);
	}

	@Test
	void nullSafe()
	{
		testIsNullSafe();
	}

	@Override
	protected Matcher<?> createMatcher()
	{
		return hasFiredEventInstance(AsyncPlayerPreLoginEvent.class);
	}

}
