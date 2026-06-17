// Auto-generated mechanical sanity test for PaperDataComponentTypeBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PaperDataComponentTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PaperDataComponentTypeBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getAdapter());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<T, NMS> implements PaperDataComponentTypeBaseMock<T, NMS>
	{
	}
}
