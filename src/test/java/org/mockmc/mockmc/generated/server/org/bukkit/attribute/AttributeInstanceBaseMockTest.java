// Auto-generated mechanical sanity test for AttributeInstanceBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.attribute;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class AttributeInstanceBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AttributeInstanceBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getModifiers());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getAttribute());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements AttributeInstanceBaseMock
	{
	}
}
