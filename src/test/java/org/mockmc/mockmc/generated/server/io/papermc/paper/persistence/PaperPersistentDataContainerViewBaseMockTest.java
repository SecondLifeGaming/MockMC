// Auto-generated mechanical sanity test for PaperPersistentDataContainerViewBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PaperPersistentDataContainerViewBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		PaperPersistentDataContainerViewBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getKeys());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.serializeToBytes());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.toTagCompound());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getAdapterContext());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PaperPersistentDataContainerViewBaseMock
	{
	}
}
