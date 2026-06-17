// Auto-generated mechanical sanity test for PaperLeashableBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PaperLeashableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PaperLeashableBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getHandle());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getLeashHolder());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PaperLeashableBaseMock
	{
	}
}
