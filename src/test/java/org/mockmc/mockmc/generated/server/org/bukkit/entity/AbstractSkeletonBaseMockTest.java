// Auto-generated mechanical sanity test for AbstractSkeletonBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class AbstractSkeletonBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AbstractSkeletonBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSkeletonType());
	}

	private static class Stub implements AbstractSkeletonBaseMock
	{
	}
}
