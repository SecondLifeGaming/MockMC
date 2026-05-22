// Auto-generated mechanical sanity test for ChargedProjectilesBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ChargedProjectilesBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ChargedProjectilesBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.projectiles());
	}

	private static class Stub implements ChargedProjectilesBaseMock
	{
	}
}
