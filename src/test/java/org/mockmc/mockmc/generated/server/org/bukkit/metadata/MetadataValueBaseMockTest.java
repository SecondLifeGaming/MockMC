// Auto-generated mechanical sanity test for MetadataValueBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.metadata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class MetadataValueBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MetadataValueBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.value());
		assertSafeDefault(mock.asString());
		assertSafeDefault(mock.getOwningPlugin());
	}

	private static class Stub implements MetadataValueBaseMock
	{
	}
}
