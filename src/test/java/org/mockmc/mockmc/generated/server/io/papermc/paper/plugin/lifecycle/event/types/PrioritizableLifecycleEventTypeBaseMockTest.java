// Auto-generated mechanical sanity test for PrioritizableLifecycleEventTypeBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin.lifecycle.event.types;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEvent;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventOwner;
import io.papermc.paper.plugin.lifecycle.event.handler.configuration.PrioritizedLifecycleEventHandlerConfiguration;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PrioritizableLifecycleEventTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PrioritizableLifecycleEventTypeBaseMock<?, ?, ?> mock = new Stub<>();
		assertNotNull(mock);
	}

	private static class Stub<O extends LifecycleEventOwner, E extends LifecycleEvent, C extends PrioritizedLifecycleEventHandlerConfiguration<O>>
			implements
				PrioritizableLifecycleEventTypeBaseMock<O, E, C>
	{
	}
}
