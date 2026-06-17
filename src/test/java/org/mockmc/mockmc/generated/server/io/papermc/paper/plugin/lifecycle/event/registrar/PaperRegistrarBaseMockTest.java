// Auto-generated mechanical sanity test for PaperRegistrarBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin.lifecycle.event.registrar;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEventOwner;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PaperRegistrarBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PaperRegistrarBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
	}

	private static class Stub<O extends LifecycleEventOwner> implements PaperRegistrarBaseMock<O>
	{
	}
}
