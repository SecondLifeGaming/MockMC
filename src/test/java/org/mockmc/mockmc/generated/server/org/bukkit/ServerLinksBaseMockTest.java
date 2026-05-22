// Auto-generated mechanical sanity test for ServerLinksBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ServerLinksBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ServerLinksBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.copy());
		assertSafeDefault(mock.getLinks());
	}

	private static class Stub implements ServerLinksBaseMock
	{
	}
}
