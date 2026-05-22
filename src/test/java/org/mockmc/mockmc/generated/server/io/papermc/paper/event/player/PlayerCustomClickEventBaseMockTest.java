// Auto-generated mechanical sanity test for PlayerCustomClickEventBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.event.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PlayerCustomClickEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PlayerCustomClickEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getHandlers());
		assertSafeDefault(mock.getTag());
		assertSafeDefault(mock.getIdentifier());
		assertSafeDefault(mock.getDialogResponseView());
		assertSafeDefault(mock.getCommonConnection());
	}

	private static class Stub implements PlayerCustomClickEventBaseMock
	{
	}
}
