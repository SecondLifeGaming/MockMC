// Auto-generated mechanical sanity test for AbstractRespawnEventBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.event.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class AbstractRespawnEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AbstractRespawnEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getRespawnLocation());
		assertSafeDefault(mock.getRespawnReason());
		assertSafeDefault(mock.getRespawnFlags());
	}

	private static class Stub implements AbstractRespawnEventBaseMock
	{
	}
}
