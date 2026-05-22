// Auto-generated mechanical sanity test for ProxiedCommandSenderBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ProxiedCommandSenderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ProxiedCommandSenderBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.audience());
		assertSafeDefault(mock.getCaller());
		assertSafeDefault(mock.getCallee());
	}

	private static class Stub implements ProxiedCommandSenderBaseMock
	{
	}
}
