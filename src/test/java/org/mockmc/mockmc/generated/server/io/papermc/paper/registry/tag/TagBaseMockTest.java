// Auto-generated mechanical sanity test for TagBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.tag;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.Keyed;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class TagBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TagBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.tagKey());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<T extends Keyed> implements TagBaseMock<T>
	{
	}
}
