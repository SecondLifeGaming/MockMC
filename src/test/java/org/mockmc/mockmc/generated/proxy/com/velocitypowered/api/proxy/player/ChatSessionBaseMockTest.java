// Auto-generated mechanical sanity test for ChatSessionBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ChatSessionBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults() throws Exception
	{
		ChatSessionBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSessionId());
	}

	private static class Stub implements ChatSessionBaseMock
	{
	}
}
