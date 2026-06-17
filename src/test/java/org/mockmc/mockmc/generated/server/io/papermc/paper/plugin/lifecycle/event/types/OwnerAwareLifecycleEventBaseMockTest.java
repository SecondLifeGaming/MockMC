// Auto-generated mechanical sanity test for OwnerAwareLifecycleEventBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin.lifecycle.event.types;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEventOwner;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class OwnerAwareLifecycleEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		OwnerAwareLifecycleEventBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
	}

	private static class Stub<O extends LifecycleEventOwner> implements OwnerAwareLifecycleEventBaseMock<O>
	{
	}
}
