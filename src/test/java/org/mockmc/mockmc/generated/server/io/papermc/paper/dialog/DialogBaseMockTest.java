// Auto-generated mechanical sanity test for DialogBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.dialog;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class DialogBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DialogBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getKey());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.key());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements DialogBaseMock
	{
	}
}
