// Auto-generated mechanical sanity test for ServerBuildInfoBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ServerBuildInfoBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ServerBuildInfoBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.buildNumber());
		assertSafeDefault(mock.gitCommit());
		assertSafeDefault(mock.gitBranch());
		assertSafeDefault(mock.minecraftVersionId());
		assertSafeDefault(mock.brandName());
		assertSafeDefault(mock.minecraftVersionName());
		assertSafeDefault(mock.brandId());
		assertSafeDefault(mock.buildTime());
	}

	private static class Stub implements ServerBuildInfoBaseMock
	{
	}
}
