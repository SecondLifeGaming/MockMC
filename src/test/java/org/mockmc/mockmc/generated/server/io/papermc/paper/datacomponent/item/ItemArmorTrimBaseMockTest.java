// Auto-generated mechanical sanity test for ItemArmorTrimBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ItemArmorTrimBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ItemArmorTrimBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.armorTrim());
	}

	private static class Stub implements ItemArmorTrimBaseMock
	{
	}
}
