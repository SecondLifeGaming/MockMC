// Auto-generated mechanical sanity test for LifecycleEventTypeBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin.lifecycle.event.types;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEvent;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventOwner;
import io.papermc.paper.plugin.lifecycle.event.handler.configuration.LifecycleEventHandlerConfiguration;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class LifecycleEventTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LifecycleEventTypeBaseMock<?, ?, ?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.name());
	}

	private static class Stub<O extends LifecycleEventOwner, E extends LifecycleEvent, C extends LifecycleEventHandlerConfiguration<O>>
			implements
				LifecycleEventTypeBaseMock<O, E, C>
	{
	}
}
