// Auto-generated mechanical sanity test for FilteredBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.text;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FilteredBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FilteredBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.raw());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.filtered());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<T> implements FilteredBaseMock<T>
	{
	}
}
