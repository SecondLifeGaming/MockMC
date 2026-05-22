// Auto-generated mechanical sanity test for ResourcePackBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.packs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ResourcePackBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ResourcePackBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getId());
		assertSafeDefault(mock.getUrl());
		assertSafeDefault(mock.getHash());
		assertSafeDefault(mock.getPrompt());
	}

	private static class Stub implements ResourcePackBaseMock
	{
	}
}
