// Auto-generated mechanical sanity test for RepairableBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RepairableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RepairableBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.types());
	}

	private static class Stub implements RepairableBaseMock
	{
	}
}
