// Auto-generated mechanical sanity test for SimpleAttachableMaterialDataBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.material;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.material.SimpleAttachableMaterialData;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class SimpleAttachableMaterialDataBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		SimpleAttachableMaterialDataBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.toString());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.clone());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getFacing());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements SimpleAttachableMaterialDataBaseMock
	{
		@Override
		public SimpleAttachableMaterialData clone()
		{
			return null;
		}
	}
}
