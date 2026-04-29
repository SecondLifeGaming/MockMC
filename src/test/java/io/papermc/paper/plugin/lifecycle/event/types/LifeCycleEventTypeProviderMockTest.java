package io.papermc.paper.plugin.lifecycle.event.types;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.plugin.PluginMock;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockMCExtension.class)
class LifeCycleEventTypeProviderMockTest
{

	@MockMCInject
	private PluginMock lifeCycleEventOwner;

	@Test
	void monitor()
	{
		assertDoesNotThrow(
				() -> LifecycleEventTypeProviderMock.INSTANCE.orElseThrow().monitor("test", PluginMock.class));
	}

	@Test
	void prioritized()
	{
		assertDoesNotThrow(
				() -> LifecycleEventTypeProviderMock.INSTANCE.orElseThrow().prioritized("test", PluginMock.class));
	}

}
