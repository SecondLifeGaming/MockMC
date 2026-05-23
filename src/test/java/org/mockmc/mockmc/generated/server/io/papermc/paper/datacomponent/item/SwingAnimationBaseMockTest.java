// Auto-generated mechanical sanity test for SwingAnimationBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SwingAnimationBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SwingAnimationBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.type());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements SwingAnimationBaseMock
	{
	}
}
