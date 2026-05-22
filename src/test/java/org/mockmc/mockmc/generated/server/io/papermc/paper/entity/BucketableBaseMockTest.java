// Auto-generated mechanical sanity test for BucketableBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BucketableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BucketableBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getBaseBucketItem());
		assertSafeDefault(mock.getPickupSound());
	}

	private static class Stub implements BucketableBaseMock
	{
	}
}
