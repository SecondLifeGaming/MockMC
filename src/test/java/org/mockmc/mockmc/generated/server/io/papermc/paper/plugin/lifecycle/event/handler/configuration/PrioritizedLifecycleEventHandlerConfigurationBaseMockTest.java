// Auto-generated mechanical sanity test for PrioritizedLifecycleEventHandlerConfigurationBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin.lifecycle.event.handler.configuration;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEventOwner;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PrioritizedLifecycleEventHandlerConfigurationBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PrioritizedLifecycleEventHandlerConfigurationBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.monitor());
	}

	private static class Stub<O extends LifecycleEventOwner>
			implements
				PrioritizedLifecycleEventHandlerConfigurationBaseMock<O>
	{
	}
}
