// Auto-generated mechanical sanity test for DatapackBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datapack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class DatapackBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DatapackBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.computeDisplayName());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements DatapackBaseMock
	{
	}
}
