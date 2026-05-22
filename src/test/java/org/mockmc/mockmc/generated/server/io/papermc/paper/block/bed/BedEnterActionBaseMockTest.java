// Auto-generated mechanical sanity test for BedEnterActionBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.block.bed;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BedEnterActionBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BedEnterActionBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.errorMessage());
		assertSafeDefault(mock.canSleep());
		assertSafeDefault(mock.canSetSpawn());
		assertSafeDefault(mock.problem());
	}

	private static class Stub implements BedEnterActionBaseMock
	{
	}
}
