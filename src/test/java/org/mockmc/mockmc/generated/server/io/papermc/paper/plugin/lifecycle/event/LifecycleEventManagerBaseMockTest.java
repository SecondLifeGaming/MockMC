// Auto-generated mechanical sanity test for LifecycleEventManagerBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin.lifecycle.event;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEventOwner;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class LifecycleEventManagerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LifecycleEventManagerBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
	}

	private static class Stub<O extends LifecycleEventOwner> implements LifecycleEventManagerBaseMock<O>
	{
	}
}
