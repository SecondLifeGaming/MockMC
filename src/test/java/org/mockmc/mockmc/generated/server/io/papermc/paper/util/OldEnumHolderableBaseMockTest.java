// Auto-generated mechanical sanity test for OldEnumHolderableBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.util;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.util.OldEnum;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class OldEnumHolderableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		OldEnumHolderableBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.name());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.toString());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<A extends OldEnum<A>, M> implements OldEnumHolderableBaseMock<A, M>
	{
	}
}
