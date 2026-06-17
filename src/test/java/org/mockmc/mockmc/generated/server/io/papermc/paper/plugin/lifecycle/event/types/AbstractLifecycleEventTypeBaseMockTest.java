// Auto-generated mechanical sanity test for AbstractLifecycleEventTypeBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin.lifecycle.event.types;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEvent;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventOwner;
import io.papermc.paper.plugin.lifecycle.event.handler.configuration.LifecycleEventHandlerConfiguration;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class AbstractLifecycleEventTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AbstractLifecycleEventTypeBaseMock<?, ?, ?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.name());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<O extends LifecycleEventOwner, E extends LifecycleEvent, C extends LifecycleEventHandlerConfiguration<O>>
			implements
				AbstractLifecycleEventTypeBaseMock<O, E, C>
	{
	}
}
