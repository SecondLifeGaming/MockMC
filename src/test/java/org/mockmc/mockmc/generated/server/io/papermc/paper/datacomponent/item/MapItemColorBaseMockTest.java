// Auto-generated mechanical sanity test for MapItemColorBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class MapItemColorBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MapItemColorBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.color());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements MapItemColorBaseMock
	{
	}
}
