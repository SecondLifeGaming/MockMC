// Auto-generated mechanical sanity test for VersionFetcherBaseMock
package org.mockmc.mockmc.generated.server.com.destroystokyo.paper.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class VersionFetcherBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		VersionFetcherBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getVersionMessage());
	}

	private static class Stub implements VersionFetcherBaseMock
	{
	}
}
