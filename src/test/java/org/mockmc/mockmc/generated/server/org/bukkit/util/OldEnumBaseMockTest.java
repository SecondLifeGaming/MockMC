// Auto-generated mechanical sanity test for OldEnumBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.util;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.util.OldEnum;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class OldEnumBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		OldEnumBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.name());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<T extends OldEnum<T>> implements OldEnumBaseMock<T>
	{
	}
}
