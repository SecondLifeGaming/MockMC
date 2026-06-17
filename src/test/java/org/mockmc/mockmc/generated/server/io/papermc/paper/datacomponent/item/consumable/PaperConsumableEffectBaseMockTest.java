// Auto-generated mechanical sanity test for PaperConsumableEffectBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item.consumable;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PaperConsumableEffectBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PaperConsumableEffectBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.internal());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PaperConsumableEffectBaseMock
	{
	}
}
