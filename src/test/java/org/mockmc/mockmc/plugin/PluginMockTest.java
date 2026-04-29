package org.mockmc.mockmc.plugin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class PluginMockTest
{

	@MockMCInject
	private ServerMock serverMock;

	@Test
	void onEnable_triggers()
	{
		AtomicBoolean trigger = new AtomicBoolean(false);
		PluginMock.Builder builder = PluginMock.builder().withOnEnable((ignored) -> trigger.set(true));
		assertFalse(trigger.get());
		builder.build();
		assertTrue(trigger.get());
	}

	@Test
	void onLoad_triggers()
	{
		AtomicBoolean trigger = new AtomicBoolean(false);
		PluginMock.Builder builder = PluginMock.builder().withOnLoad((ignored) -> trigger.set(true));
		assertFalse(trigger.get());
		builder.build();
		assertTrue(trigger.get());
	}

	@Test
	void onDisable_triggers()
	{
		AtomicBoolean trigger = new AtomicBoolean(false);
		PluginMock.Builder builder = PluginMock.builder().withOnDisable((ignored) -> trigger.set(true));
		assertFalse(trigger.get());
		builder.build();
		assertFalse(trigger.get());
		serverMock.getPluginManager().disablePlugins();
		assertTrue(trigger.get());
	}

}
