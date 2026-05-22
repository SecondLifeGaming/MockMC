// Auto-generated mechanical sanity test for OcelotBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class OcelotBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		OcelotBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getCatType());
	}

	private static class Stub implements OcelotBaseMock
	{
	}
}
