// Auto-generated mechanical sanity test for CustomModelDataBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CustomModelDataBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CustomModelDataBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.flags());
		assertSafeDefault(mock.strings());
		assertSafeDefault(mock.colors());
		assertSafeDefault(mock.floats());
	}

	private static class Stub implements CustomModelDataBaseMock
	{
	}
}
