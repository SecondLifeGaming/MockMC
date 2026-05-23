// Auto-generated mechanical sanity test for WritableBookContentBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class WritableBookContentBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WritableBookContentBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.pages());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements WritableBookContentBaseMock
	{
	}
}
