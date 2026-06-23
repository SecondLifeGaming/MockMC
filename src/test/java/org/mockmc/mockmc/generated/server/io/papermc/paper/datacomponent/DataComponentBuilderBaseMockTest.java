// Auto-generated mechanical sanity test for DataComponentBuilderBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class DataComponentBuilderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DataComponentBuilderBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.build());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<C> implements DataComponentBuilderBaseMock<C>
	{
	}
}
