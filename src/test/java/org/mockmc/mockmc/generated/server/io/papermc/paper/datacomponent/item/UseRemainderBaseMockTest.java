// Auto-generated mechanical sanity test for UseRemainderBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class UseRemainderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		UseRemainderBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.transformInto());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements UseRemainderBaseMock
	{
	}
}
