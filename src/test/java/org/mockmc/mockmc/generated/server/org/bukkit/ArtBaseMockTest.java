// Auto-generated mechanical sanity test for ArtBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class ArtBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ArtBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getKey());
		assertSafeDefault(mock.key());
		assertSafeDefault(mock.title());
		assertSafeDefault(mock.author());
		assertSafeDefault(mock.assetId());
	}

	private static class Stub implements ArtBaseMock
	{
	}
}
