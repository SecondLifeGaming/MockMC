// Auto-generated mechanical sanity test for UnsafeValuesBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class UnsafeValuesBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		UnsafeValuesBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.componentFlattener());
		assertSafeDefault(mock.plainComponentSerializer());
		assertSafeDefault(mock.plainTextSerializer());
		assertSafeDefault(mock.gsonComponentSerializer());
		assertSafeDefault(mock.colorDownsamplingGsonComponentSerializer());
		assertSafeDefault(mock.legacyComponentSerializer());
		assertSafeDefault(mock.getVersionFetcher());
		assertSafeDefault(mock.getMainLevelName());
		assertSafeDefault(mock.createEmptyStack());
	}

	private static class Stub implements UnsafeValuesBaseMock
	{
	}
}
