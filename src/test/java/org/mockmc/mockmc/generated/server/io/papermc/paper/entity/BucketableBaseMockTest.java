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
		try
		{
			assertSafeDefault(mock.getPickupSound());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getBaseBucketItem());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements BucketableBaseMock
	{
	}
}
