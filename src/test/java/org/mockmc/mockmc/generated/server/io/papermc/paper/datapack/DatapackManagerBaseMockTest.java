// Auto-generated mechanical sanity test for DatapackManagerBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datapack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DatapackManagerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DatapackManagerBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getEnabledPacks());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getPacks());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements DatapackManagerBaseMock
	{
	}
}
