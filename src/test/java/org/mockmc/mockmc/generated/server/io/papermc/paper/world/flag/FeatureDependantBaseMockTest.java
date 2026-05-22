// Auto-generated mechanical sanity test for FeatureDependantBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.world.flag;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FeatureDependantBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FeatureDependantBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.requiredFeatures());
	}

	private static class Stub implements FeatureDependantBaseMock
	{
	}
}
