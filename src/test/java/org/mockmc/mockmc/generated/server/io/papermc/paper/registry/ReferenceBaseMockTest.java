// Auto-generated mechanical sanity test for ReferenceBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.Keyed;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ReferenceBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ReferenceBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.value());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.valueOrNull());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<T extends Keyed> implements ReferenceBaseMock<T>
	{
	}
}
