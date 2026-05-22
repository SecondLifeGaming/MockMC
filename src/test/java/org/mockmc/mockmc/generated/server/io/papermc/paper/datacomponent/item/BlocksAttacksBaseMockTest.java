// Auto-generated mechanical sanity test for BlocksAttacksBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BlocksAttacksBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BlocksAttacksBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.itemDamage());
		assertSafeDefault(mock.damageReductions());
		assertSafeDefault(mock.bypassedBy());
		assertSafeDefault(mock.blockSound());
		assertSafeDefault(mock.disableSound());
	}

	private static class Stub implements BlocksAttacksBaseMock
	{
	}
}
