// Auto-generated mechanical sanity test for UndefinedNullabilityBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class UndefinedNullabilityBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		UndefinedNullabilityBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.value());
	}

	private static class Stub implements UndefinedNullabilityBaseMock
	{
	}
}
