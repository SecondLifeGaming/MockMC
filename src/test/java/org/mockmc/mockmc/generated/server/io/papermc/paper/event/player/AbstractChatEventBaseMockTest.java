// Auto-generated mechanical sanity test for AbstractChatEventBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.event.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class AbstractChatEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AbstractChatEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.message());
		assertSafeDefault(mock.signedMessage());
		assertSafeDefault(mock.renderer());
		assertSafeDefault(mock.viewers());
		assertSafeDefault(mock.originalMessage());
	}

	private static class Stub implements AbstractChatEventBaseMock
	{
	}
}
