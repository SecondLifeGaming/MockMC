// Auto-generated mechanical sanity test for HangingEventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event.hanging;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class HangingEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		HangingEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getEntity());
	}

	private static class Stub implements HangingEventBaseMock
	{
	}
}
