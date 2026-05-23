// Auto-generated mechanical sanity test for FinePositionBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FinePositionBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FinePositionBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.toBlock());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements FinePositionBaseMock
	{
	}
}
