// Auto-generated mechanical sanity test for LifecycleEventHandlerConfigurationBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin.lifecycle.event.handler.configuration;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEventOwner;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class LifecycleEventHandlerConfigurationBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LifecycleEventHandlerConfigurationBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
	}

	private static class Stub<O extends LifecycleEventOwner> implements LifecycleEventHandlerConfigurationBaseMock<O>
	{
	}
}
