// Auto-generated mechanical sanity test for AttributeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.attribute;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class AttributeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AttributeBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getSentiment());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements AttributeBaseMock
	{
	}
}
