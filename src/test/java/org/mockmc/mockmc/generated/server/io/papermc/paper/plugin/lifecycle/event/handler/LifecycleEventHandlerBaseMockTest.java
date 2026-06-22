// Auto-generated mechanical sanity test for LifecycleEventHandlerBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin.lifecycle.event.handler;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEvent;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class LifecycleEventHandlerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LifecycleEventHandlerBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
	}

	private static class Stub<E extends LifecycleEvent> implements LifecycleEventHandlerBaseMock<E>
	{
	}
}
