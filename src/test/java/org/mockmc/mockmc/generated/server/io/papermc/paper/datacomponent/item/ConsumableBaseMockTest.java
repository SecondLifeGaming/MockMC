// Auto-generated mechanical sanity test for ConsumableBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ConsumableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ConsumableBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.sound());
		assertSafeDefault(mock.animation());
		assertSafeDefault(mock.consumeEffects());
	}

	private static class Stub implements ConsumableBaseMock
	{
	}
}
