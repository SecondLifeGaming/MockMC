// Auto-generated mechanical sanity test for PotDecorationsBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PotDecorationsBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PotDecorationsBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.left());
		assertSafeDefault(mock.right());
		assertSafeDefault(mock.front());
		assertSafeDefault(mock.back());
	}

	private static class Stub implements PotDecorationsBaseMock
	{
	}
}
