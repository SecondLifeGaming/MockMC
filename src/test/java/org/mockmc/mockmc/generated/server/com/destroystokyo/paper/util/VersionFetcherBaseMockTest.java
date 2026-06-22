// Auto-generated mechanical sanity test for VersionFetcherBaseMock
package org.mockmc.mockmc.generated.server.com.destroystokyo.paper.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class VersionFetcherBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		VersionFetcherBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getVersionMessage());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements VersionFetcherBaseMock
	{
	}
}
